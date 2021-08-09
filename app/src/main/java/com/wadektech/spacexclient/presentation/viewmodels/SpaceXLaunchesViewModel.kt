package com.wadektech.spacexclient.presentation.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.wadektech.spacexclient.data.repository.SpaceXLaunchesRepositoryImpl
import com.wadektech.spacexclient.utils.SortOrder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch


class SpaceXLaunchesViewModel
@ViewModelInject constructor(
    private val spaceXLaunchesRepository: SpaceXLaunchesRepositoryImpl
) : ViewModel(){

    val filter = MutableStateFlow("")
    val sortOrder = MutableStateFlow(SortOrder.FROM_DESC_TO_ASC)

    private val getAllLaunchesByFilterAndSortOrder = combine(
        filter,
        sortOrder
    ){ search, sort ->
        Pair(search,sort)
    }.flatMapLatest { (search,sort) ->
        spaceXLaunchesRepository.allLaunches(search,sort)
    }

    val fetchAllLaunches = getAllLaunchesByFilterAndSortOrder.asLiveData()

    val companyInfo = spaceXLaunchesRepository.companyInfo().asLiveData()

    init {
        getAllSpaceXLaunches()
        getAllCompanyInfo()
    }

    private fun getAllSpaceXLaunches() = viewModelScope.launch {
        spaceXLaunchesRepository.getAllSpaceLaunchesFromRemote()
    }

    private fun getAllCompanyInfo() = viewModelScope.launch {
        spaceXLaunchesRepository.getCompanyInfoFromRemote()
    }
}