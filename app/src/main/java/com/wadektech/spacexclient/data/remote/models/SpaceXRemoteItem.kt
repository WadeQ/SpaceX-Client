package com.wadektech.spacexclient.data.remote.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.wadektech.spacexclient.data.local.models.LinksLocal
import com.wadektech.spacexclient.data.local.models.RocketLocal


@JsonClass(generateAdapter = true)
data class SpaceXRemoteItem(
    @Json(name = "flight_number")
    val flightNumber: Int = 0,
    @Json(name = "launch_date_local")
    val launchDateLocal: String = "",
    @Json(name = "launch_date_utc")
    val launchDateUtc: String = "",
    @Json(name = "launch_success")
    val launchSuccess: Boolean = false,
    @Json(name = "launch_year")
    val launchYear: String = "",
    @Json(name = "links")
    val linksLocal: Links?,
    @Json(name = "mission_name")
    val missionName: String = "",
    @Json(name = "rocket")
    val rocketLocal: Rocket?
)