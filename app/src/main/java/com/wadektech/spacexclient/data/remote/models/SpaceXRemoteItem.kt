package com.wadektech.spacexclient.data.remote.models


import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.wadektech.spacexclient.data.local.models.LinksLocal
import com.wadektech.spacexclient.data.local.models.RocketLocal
import com.wadektech.spacexclient.utils.DefaultIfNull


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


data class Rocket(
    @Json(name = "rocket_id")
    val rocketId: String?,
    @Json(name = "rocket_name")
    val rocketName: String?,
    @Json(name = "rocket_type")
    val rocketType: String?
)


@DefaultIfNull
data class Links(
    @Json(name = "article_link")
    val articleLink: String?,
    @Json(name = "mission_patch")
    val missionPatch: String?,
    @Json(name = "mission_patch_small")
    val missionPatchSmall: String?,
    @Json(name = "video_link")
    val videoLink: String?,
    @Json(name = "wikipedia")
    val wikipedia: String?
)