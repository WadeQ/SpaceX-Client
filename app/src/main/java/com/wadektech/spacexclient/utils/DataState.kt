package com.wadektech.spacexclient.utils

import com.wadektech.spacexclient.data.local.models.SpaceXLocalItem


sealed class DataState {
    data class Success(val launches: List<SpaceXLocalItem>): DataState()
    data class Error(val exception: Throwable): DataState()
    object Loading : DataState()
}