package com.wadektech.spacexclient.data.local.typeconverters

import androidx.room.TypeConverter
import com.wadektech.spacexclient.data.local.models.RocketLocal
import org.json.JSONObject

class RocketTypeConverter {

    @TypeConverter
    fun fromRocket(rocket: RocketLocal): String {
        return JSONObject().apply {
            put("id", rocket.rocketId)
            put("rocket_name", rocket.rocketName)
            put("rocket_type", rocket.rocketType)
        }.toString()
    }

    @TypeConverter
    fun toRocket(rocket: String):RocketLocal{
        val json = JSONObject(rocket)
        return RocketLocal(
            json.optString("id"),
            json.optString("rocket_name"),
            json.optString("rocket_type")
        )
    }
}