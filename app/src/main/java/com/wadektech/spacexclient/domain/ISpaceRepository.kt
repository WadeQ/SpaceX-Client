package com.wadektech.spacexclient.domain

import com.wadektech.spacexclient.data.local.models.CompanyInfo
import com.wadektech.spacexclient.data.local.models.SpaceXLocalItem
import com.wadektech.spacexclient.utils.SortOrder
import kotlinx.coroutines.flow.Flow


interface ISpaceRepository {

    suspend fun getAllSpaceLaunchesFromRemote()

    suspend fun getCompanyInfoFromRemote()

    fun allLaunches(search:String, sort:SortOrder, success: Boolean) : Flow<MutableList<SpaceXLocalItem>>

    fun companyInfo() : Flow<CompanyInfo>
}