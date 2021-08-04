package com.wadektech.spacexclient.presentation.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.wadektech.spacexclient.data.repository.SpaceXLaunchesRepository
import com.wadektech.spacexclient.utils.SortOrder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch


class SpaceXLaunchesViewModel
@ViewModelInject constructor(
    private val spaceXLaunchesRepository: SpaceXLaunchesRepository
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

    init {
        getAllSpaceXLaunches()
    }

    private fun getAllSpaceXLaunches() = viewModelScope.launch {
        spaceXLaunchesRepository.getAllSpaceLaunchesFromRemote()
    }
}