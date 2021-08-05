package com.wadektech.spacexclient.data.remote

import com.wadektech.spacexclient.data.local.models.CompanyInfo
import com.wadektech.spacexclient.data.local.models.SpaceXLocalItem
import com.wadektech.spacexclient.data.remote.models.SpaceXRemote
import com.wadektech.spacexclient.data.remote.models.SpaceXRemoteItem
import kotlinx.coroutines.Deferred
import retrofit2.http.GET


interface SpaceXApiService {

    @GET("launches")
    fun getAllLaunchesFromRemoteAsync() : Deferred<List<SpaceXLocalItem>>

    @GET("info")
    fun getCompanyInfoAsync() : Deferred<CompanyInfo>
}