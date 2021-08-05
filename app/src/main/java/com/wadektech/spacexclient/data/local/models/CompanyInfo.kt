package com.wadektech.spacexclient.data.local.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "company_info")
@JsonClass(generateAdapter = true)
data class CompanyInfo(
    @Json(name = "employees")
    val employees: Int = 0,
    @Json(name = "founded")
    val founded: Int = 0,
    @Json(name = "founder")
    val founder: String = "",
    @Json(name = "launch_sites")
    val launchSites: Int = 0,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "valuation")
    val valuation: Long = 0
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}