package com.wadektech.spacexclient.data.repository

import com.wadektech.spacexclient.data.local.LocalModelMapper
import com.wadektech.spacexclient.data.local.SpaceXDao
import com.wadektech.spacexclient.data.local.models.SpaceXLocalItem
import com.wadektech.spacexclient.data.remote.RemoteModelMapper
import com.wadektech.spacexclient.data.remote.SpaceXApiService
import com.wadektech.spacexclient.utils.SortOrder
import com.wadektech.spacexclient.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject



class SpaceXLaunchesRepository @Inject constructor(
    private val spaceXDao: SpaceXDao,
    private val spaceXApiService: SpaceXApiService,
    private val localModelMapper: LocalModelMapper,
    private val remoteModelMapper: RemoteModelMapper
) {


    suspend fun getAllSpaceLaunchesFromRemote() = apiCalls {
        val launches = spaceXApiService.getAllLaunchesFromRemoteAsync().await()
        val listOfLaunches = launches.toList()
        val retrievedLaunches = remoteModelMapper.mapFromEntityAsList(listOfLaunches)
        val cacheLaunches = localModelMapper.mapToEntityAsList(retrievedLaunches)
        spaceXDao.saveAllSpaceXLaunches(cacheLaunches)
    }


    private suspend fun <T> apiCalls(apiInvoke: suspend () -> T): State<T> {
        return withContext(Dispatchers.IO) {
            try {
                State.Success(apiInvoke.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        State.Failure(false, throwable.response()?.errorBody())
                    }

                    else -> {
                        State.Failure(true, null)
                    }
                }
            }
        }
    }


    fun allLaunches(search : String, sort: SortOrder):
            Flow<MutableList<SpaceXLocalItem>> = spaceXDao.getAllSpaceLaunches(search,sort)
}