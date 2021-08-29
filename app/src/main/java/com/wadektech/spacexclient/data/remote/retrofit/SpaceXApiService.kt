package com.wadektech.spacexclient.data.remote.retrofit

import com.wadektech.spacexclient.data.local.models.SpaceXLocalItem
import com.wadektech.spacexclient.data.remote.models.CompanyInfoRemote
import kotlinx.coroutines.Deferred
import retrofit2.http.GET


interface SpaceXApiService {

    @GET("launches")
    fun getAllLaunchesFromRemoteAsync() : Deferred<List<SpaceXLocalItem>>

    @GET("info")
    fun getCompanyInfoAsync() : Deferred<CompanyInfoRemote>
}