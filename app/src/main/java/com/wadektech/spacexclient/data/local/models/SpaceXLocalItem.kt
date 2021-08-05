package com.wadektech.spacexclient.data.local.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.wadektech.spacexclient.utils.DefaultIfNull


@DefaultIfNull
@Entity(tableName = "space_launches")
data class SpaceXLocalItem(
    @PrimaryKey(autoGenerate = false)
    @Json(name = "flight_number")
    val flightNumber: Int?,
    @Json(name = "launch_date_local")
    val launchDateLocal: String?,
    @Json(name = "launch_date_utc")
    val launchDateUtc: String?,
    @Json(name = "launch_success")
    val launchSuccess: Boolean?,
    @Json(name = "launch_year")
    val launchYear: String?,
    @Json(name = "links")
    val linksLocal: LinksLocal?,
    @Json(name = "mission_name")
    val missionName: String?,
    @Json(name = "rocket")
    val rocket: RocketLocal?
)


@DefaultIfNull
data class RocketLocal(
    @PrimaryKey(autoGenerate = false)
    @Json(name = "rocket_id")
    val rocketId: String?,
    @Json(name = "rocket_name")
    val rocketName: String?,
    @Json(name = "rocket_type")
    val rocketType: String?
)


@DefaultIfNull
data class LinksLocal(
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