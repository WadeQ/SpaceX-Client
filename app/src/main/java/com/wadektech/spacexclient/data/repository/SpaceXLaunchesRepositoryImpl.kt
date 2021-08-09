package com.wadektech.spacexclient.data.repository

import com.wadektech.spacexclient.data.local.room.ICompanyDao
import com.wadektech.spacexclient.data.local.models.LocalModelMapper
import com.wadektech.spacexclient.data.local.room.SpaceXDao
import com.wadektech.spacexclient.data.local.models.CompanyInfo
import com.wadektech.spacexclient.data.local.models.SpaceXLocalItem
import com.wadektech.spacexclient.data.remote.models.RemoteModelMapper
import com.wadektech.spacexclient.data.remote.retrofit.SpaceXApiService
import com.wadektech.spacexclient.domain.ISpaceRepository
import com.wadektech.spacexclient.utils.SortOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject


class SpaceXLaunchesRepositoryImpl @Inject constructor(
    private val spaceXDao: SpaceXDao,
    private val spaceXApiService: SpaceXApiService,
    private val companyInfoDao: ICompanyDao,
    private val localModelMapper: LocalModelMapper,
    private val remoteModelMapper: RemoteModelMapper
) : ISpaceRepository {

    override suspend fun getAllSpaceLaunchesFromRemote()  {
        return withContext(Dispatchers.IO) {
            try {
                val launches = spaceXApiService.getAllLaunchesFromRemoteAsync().await()
                val launchesList = remoteModelMapper.mapFromEntityAsList(launches)
                val cachedLaunches = localModelMapper.mapToEntityAsList(launchesList)
                spaceXDao.saveAllSpaceXLaunches(cachedLaunches)
                Timber.d("getAllSpaceLaunchesFromRemote:  Success, retrieved launches " +
                        "are ${launches.size}")
            } catch (e : Exception){
                Timber.d("SpaceX Failure due to ${e.message}")
            }
        }
    }

    override suspend fun getCompanyInfoFromRemote()  {
        return withContext(Dispatchers.IO) {
            try {
                val info = spaceXApiService.getCompanyInfoAsync().await()
                companyInfoDao.saveCompanyInfo(info)
                Timber.d("getCompanyInfoFromRemote:  Success, retrieved info name " +
                        "is ${info.name}")
            } catch (e : Exception){
                Timber.d("SpaceX Company info Failure due to ${e.message}")
            }
        }
    }

    override fun allLaunches(search : String, sort: SortOrder):
            Flow<MutableList<SpaceXLocalItem>> = spaceXDao.getAllSpaceLaunches(search,sort)


    override fun companyInfo() : Flow<CompanyInfo> = companyInfoDao.getCompanyInfo()
}