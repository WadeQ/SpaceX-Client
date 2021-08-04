package com.wadektech.spacexclient.domain


data class SpaceXDomainItem(
    val id : Int = 0 ,
    val launchDateLocal: String = "",
    val launchDateUtc: String = "",
    val launchSuccess: Boolean = false,
    val launchYear: String = "",
    val links: Links = Links(),
    val missionName: String = "",
    val rocket: Rocket = Rocket()
)

data class Rocket(
    val rocketId: String = "",
    val rocketName: String = "",
    val rocketType: String = ""
)

data class Links(
    val articleLink: Any = Any(),
    val missionPatch: Any = Any(),
    val missionPatchSmall: Any = Any(),
    val videoLink: Any = Any(),
    val wikipedia: Any = Any()
)