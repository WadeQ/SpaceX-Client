package com.wadektech.spacexclient.presentation.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.wadektech.spacexclient.data.local.models.SpaceXLocalItem
import com.wadektech.spacexclient.data.repository.SpaceXLaunchesRepositoryImpl
import com.wadektech.spacexclient.utils.DataState
import com.wadektech.spacexclient.utils.SortOrder
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class SpaceXLaunchesViewModel
@ViewModelInject constructor(
    private val spaceXLaunchesRepository: SpaceXLaunchesRepositoryImpl
) : ViewModel(){

    private val _launches : MutableStateFlow<DataState> = MutableStateFlow(DataState.Success(emptyList()))
    val launches = _launches.asStateFlow()

    val companyInfo = spaceXLaunchesRepository.companyInfo().asLiveData()

    init {
        getAllSpaceXLaunches()
        getAllCompanyInfo()
        getAllLaunches()
    }

    private fun getAllSpaceXLaunches() = viewModelScope.launch {
        spaceXLaunchesRepository.getAllSpaceLaunchesFromRemote()
    }

    private fun getAllCompanyInfo() = viewModelScope.launch {
        spaceXLaunchesRepository.getCompanyInfoFromRemote()
    }

    private fun getAllLaunches() = viewModelScope.launch {
       spaceXLaunchesRepository.allLaunches(
           "",
            SortOrder.FROM_DESC_TO_ASC,
           true
       ).collect {
           _launches.value = DataState.Success(it)
       }
    }

    fun getAllFilteredLaunches(search: String, sortOrder: SortOrder, launchSuccess : Boolean) =
        viewModelScope.launch {
            spaceXLaunchesRepository.allLaunches(
                search,
                sortOrder,
                launchSuccess
            ).collect {
                _launches.value = DataState.Success(it)
            }
        }

}