package com.wadektech.spacexclient.data.local.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@Entity(tableName = "space_launches")
@JsonClass(generateAdapter = true)
data class SpaceXLocalItem(
    @PrimaryKey(autoGenerate = false)
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
    val linksLocal: LinksLocal = LinksLocal(),
    @Json(name = "mission_name")
    val missionName: String = "",
    @Json(name = "rocket")
    val rocket: RocketLocal = RocketLocal()
)