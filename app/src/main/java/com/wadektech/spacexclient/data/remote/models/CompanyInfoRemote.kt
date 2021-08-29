package com.wadektech.spacexclient.data.remote.models

import com.squareup.moshi.Json


data class CompanyInfoRemote(
    @Json(name = "employees")
    val employees: Int?,
    @Json(name = "founded")
    val founded: Int?,
    @Json(name = "founder")
    val founder: String?,
    @Json(name = "launch_sites")
    val launchSites: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "valuation")
    val valuation: Long?
)