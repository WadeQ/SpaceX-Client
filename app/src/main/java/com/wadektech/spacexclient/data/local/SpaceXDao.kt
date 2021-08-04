package com.wadektech.spacexclient.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wadektech.spacexclient.data.local.models.SpaceXLocalItem
import com.wadektech.spacexclient.utils.SortOrder
import kotlinx.coroutines.flow.Flow


@Dao
interface SpaceXDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllSpaceXLaunches(launches: List<SpaceXLocalItem>)

    fun getAllSpaceLaunches(search: String?, sort: SortOrder) :
            Flow<MutableList<SpaceXLocalItem>> = when(sort){

        SortOrder.FROM_ASC_TO_DESC -> getAllLaunchesSortedByLaunchSuccess(search)
        SortOrder.FROM_DESC_TO_ASC -> getAllLaunchesSortedByDesc(search)
    }


    @Query("SELECT * FROM space_launches WHERE launchYear LIKE '%' || :search || '%' ORDER BY launchSuccess ASC")
    fun getAllLaunchesSortedByLaunchSuccess(search: String?) : Flow<MutableList<SpaceXLocalItem>>


    @Query("SELECT * FROM space_launches WHERE launchYear LIKE '%' || :search || '%' ORDER BY launchSuccess DESC")
    fun getAllLaunchesSortedByDesc(search: String?) : Flow<MutableList<SpaceXLocalItem>>
}