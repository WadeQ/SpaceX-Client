package com.wadektech.spacexclient.data.local.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "company_info")
@JsonClass(generateAdapter = true)
data class CompanyInfo(
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
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}