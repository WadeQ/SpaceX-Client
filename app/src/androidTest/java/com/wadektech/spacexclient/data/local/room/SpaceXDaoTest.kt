package com.wadektech.spacexclient.data.local.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.wadektech.spacexclient.data.local.models.Links
import com.wadektech.spacexclient.data.local.models.Rocket
import com.wadektech.spacexclient.data.local.models.SpaceXLocalItem
import com.wadektech.spacexclient.utils.SortOrder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.jvm.Throws


@RunWith(AndroidJUnit4::class)
@SmallTest
class SpaceXDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: SpaceXRoomDatabase
    private lateinit var dao: SpaceXDao

    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            SpaceXRoomDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.spaceXDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @ExperimentalCoroutinesApi
    @Test
    @Throws(Exception::class)
    fun insertLaunchesItem() = runBlockingTest {
        val launches = SpaceXLocalItem(
            1,
            "2006-03-25T10:30:00+12:00",
            "2006-03-24T22:30:00.000Z",
            false,
            "2006",
            Links(
                "https://www.space.com/2196-spacex-inaugural-falcon-1-rocket-lost-launch.html",
                "https://images2.imgbox.com/40/e3/GypSkayF_o.png",
                "https://images2.imgbox.com/3c/0e/T8iJcSN3_o.png",
                "https://www.youtube.com/watch?v=0a_00nJ_Y88",
                "https://en.wikipedia.org/wiki/DemoSat"
            ),
            "FalconSat",
            Rocket(
                "falcon1",
                "Falcon 1",
                "Merlin A"
            )
        )

        dao.saveAllSpaceXLaunches(listOf(launches))
        val allLaunches = dao.getAllSpaceLaunches("",SortOrder.FROM_DESC_TO_ASC,false).first()
        assertThat(allLaunches[0].flightNumber).isEqualTo(launches.flightNumber)
    }
}
