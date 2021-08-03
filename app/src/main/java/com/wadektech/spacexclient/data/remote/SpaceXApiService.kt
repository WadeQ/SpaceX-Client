package com.wadektech.spacexclient.data.remote

import com.wadektech.spacexclient.data.local.models.SpaceXLocal
import retrofit2.http.GET


interface SpaceXApiService {

    @GET("launches")
    suspend fun getAllLaunchesFromRemote() : List<SpaceXLocal>
}