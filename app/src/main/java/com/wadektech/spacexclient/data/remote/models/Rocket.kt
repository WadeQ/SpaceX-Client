package com.wadektech.spacexclient.data.remote.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rocket(
    @Json(name = "rocket_id")
    val rocketId: String = "",
    @Json(name = "rocket_name")
    val rocketName: String = "",
    @Json(name = "rocket_type")
    val rocketType: String = ""
)