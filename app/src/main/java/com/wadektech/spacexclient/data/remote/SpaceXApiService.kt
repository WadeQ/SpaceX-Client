package com.wadektech.spacexclient.data.remote

import com.wadektech.spacexclient.data.remote.models.SpaceXRemote
import kotlinx.coroutines.Deferred
import retrofit2.http.GET


interface SpaceXApiService {

    @GET("launches")
    suspend fun getAllLaunchesFromRemoteAsync() : Deferred<SpaceXRemote>
}