package com.wadektech.spacexclient.domain


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class Rocket(
    val rocketId: String = "",
    val rocketName: String = "",
    val rocketType: String = ""
)