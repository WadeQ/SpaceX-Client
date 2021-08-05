package com.wadektech.spacexclient.data.repository

import com.wadektech.spacexclient.data.local.ICompanyDao
import com.wadektech.spacexclient.data.local.SpaceXDao
import com.wadektech.spacexclient.data.local.models.CompanyInfo
import com.wadektech.spacexclient.data.local.models.SpaceXLocalItem
import com.wadektech.spacexclient.data.remote.SpaceXApiService
import com.wadektech.spacexclient.utils.SortOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import timber.log.Timber


class SpaceXLaunchesRepository constructor(
    private val spaceXDao: SpaceXDao,
    private val spaceXApiService: SpaceXApiService,
    private val companyInfoDao: ICompanyDao
) {

    suspend fun getAllSpaceLaunchesFromRemote()  {
        return withContext(Dispatchers.IO) {
            try {
                val launches = spaceXApiService.getAllLaunchesFromRemoteAsync().await()
                spaceXDao.saveAllSpaceXLaunches(launches)
                Timber.d("getAllSpaceLaunchesFromRemote:  Success, retrieved launches are ${launches.size}")
            } catch (e : Exception){
                Timber.d("SpaceX Failure due to ${e.message}")
            }
        }
    }

    suspend fun getCompanyInfoFromRemote()  {
        return withContext(Dispatchers.IO) {
            try {
                val info = spaceXApiService.getCompanyInfoAsync().await()
                companyInfoDao.saveCompanyInfo(info)
                Timber.d("getCompanyInfoFromRemote:  Success, retrieved info name is ${info.name}")
            } catch (e : Exception){
                Timber.d("SpaceX Company info Failure due to ${e.message}")
            }
        }
    }

    fun allLaunches(search : String, sort: SortOrder):
            Flow<MutableList<SpaceXLocalItem>> = spaceXDao.getAllSpaceLaunches(search,sort)

    fun companyInfo() : Flow<CompanyInfo> = companyInfoDao.getCompanyInfo()
}