package com.wadektech.spacexclient.data.local.typeconverters

import androidx.room.TypeConverter
import com.wadektech.spacexclient.data.local.models.LinksLocal
import org.json.JSONObject


class LinkLocalTypeConverters {

    @TypeConverter
    fun fromLink(links: LinksLocal): String {
        return JSONObject().apply {
            put("article_link", links.articleLink)
            put("mission_patch", links.missionPatch)
            put("mission_patch_small", links.missionPatchSmall)
            put("video_link", links.videoLink)
            put("wikipedia", links.wikipedia)
        }.toString()
    }

    @TypeConverter
    fun toLink(links: String): LinksLocal {
        val json = JSONObject(links)
        return LinksLocal(
            json.optString("article_link"),
            json.optString("mission_patch"),
            json.optString("mission_patch_small"),
            json.optString("video_link"),
            json.optString("wikipedia")
        )
    }
}