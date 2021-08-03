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