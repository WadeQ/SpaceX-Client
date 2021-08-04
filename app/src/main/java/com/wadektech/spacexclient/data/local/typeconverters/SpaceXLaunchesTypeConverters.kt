package com.wadektech.spacexclient.data.local.typeconverters

import androidx.room.TypeConverter
import com.wadektech.spacexclient.data.local.models.LinksLocal
import com.wadektech.spacexclient.data.local.models.RocketLocal
import com.wadektech.spacexclient.data.local.models.SpaceXLocalItem
import org.json.JSONObject


class SpaceXLaunchesTypeConverters {

    @TypeConverter
    fun fromSpaceXLaunches(launches: SpaceXLocalItem): String {
        return JSONObject().apply {
            put("flight_number", launches.flightNumber)
            put("launch_date_local", launches.launchDateLocal)
            put("launch_date_utc", launches.launchDateUtc)
            put("launch_success", launches.launchSuccess)
            put("launch_year", launches.launchYear)
            put("links", launches.linksLocal)
            put("mission_name", launches.missionName)
            put("rocket", launches.rocket)
        }.toString()
    }


    @TypeConverter
    fun toSpaceXLaunches(launches: String): SpaceXLocalItem {
        val json = JSONObject(launches)
        return SpaceXLocalItem(
            json.optInt("flight_number"),
            json.optString("launch_date_local"),
            json.optString("launch_date_utc"),
            json.optBoolean("launch_success"),
            json.optString("launch_year"),
            json.opt("links") as? LinksLocal,
            json.optString("mission_name"),
            json.opt("rocket") as? RocketLocal

        )
    }
}