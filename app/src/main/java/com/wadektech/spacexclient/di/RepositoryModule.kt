package com.wadektech.spacexclient.di

import com.wadektech.spacexclient.data.local.room.ICompanyDao
import com.wadektech.spacexclient.data.local.models.LocalModelMapper
import com.wadektech.spacexclient.data.local.room.SpaceXDao
import com.wadektech.spacexclient.data.remote.models.RemoteModelMapper
import com.wadektech.spacexclient.data.remote.retrofit.SpaceXApiService
import com.wadektech.spacexclient.data.repository.SpaceXLaunchesRepositoryImpl
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
        spaceXApiService: SpaceXApiService,
        companyInfoDao: ICompanyDao,
        localModelMapper: LocalModelMapper,
        remoteModelMapper: RemoteModelMapper
    ): SpaceXLaunchesRepositoryImpl {
        return SpaceXLaunchesRepositoryImpl(
            spaceXDao,
            spaceXApiService,
            companyInfoDao,
            localModelMapper,
            remoteModelMapper
        )
    }
}