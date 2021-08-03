package com.wadektech.spacexclient.di

import com.wadektech.spacexclient.data.local.SpaceXDao
import com.wadektech.spacexclient.data.local.SpaceXRoomDatabase
import com.wadektech.spacexclient.data.repository.SpaceXLaunchesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @ExperimentalCoroutinesApi
    @Singleton
    @Provides
    fun providePostsRepository(
        spaceXDao: SpaceXDao,
        spaceXRoomDatabase: SpaceXRoomDatabase
    ): SpaceXLaunchesRepository {
        return SpaceXLaunchesRepository(
            spaceXDao,
            spaceXRoomDatabase
        )
    }
}