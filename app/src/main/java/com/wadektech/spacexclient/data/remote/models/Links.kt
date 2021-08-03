package com.wadektech.spacexclient.data.remote.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Links(
    @Json(name = "article_link")
    val articleLink: Any = Any(),
    @Json(name = "mission_patch")
    val missionPatch: Any = Any(),
    @Json(name = "mission_patch_small")
    val missionPatchSmall: Any = Any(),
    @Json(name = "video_link")
    val videoLink: Any = Any(),
    @Json(name = "wikipedia")
    val wikipedia: Any = Any()
)