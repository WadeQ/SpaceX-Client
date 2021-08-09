package com.wadektech.spacexclient.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wadektech.spacexclient.data.local.models.CompanyInfo
import com.wadektech.spacexclient.data.local.models.SpaceXLocalItem
import com.wadektech.spacexclient.data.local.typeconverters.LinkLocalTypeConverters
import com.wadektech.spacexclient.data.local.typeconverters.RocketTypeConverter
import com.wadektech.spacexclient.data.local.typeconverters.SpaceXLaunchesTypeConverters


@Database(entities = [SpaceXLocalItem::class, CompanyInfo::class], version = 8, exportSchema = false)
@TypeConverters(
    RocketTypeConverter::class,
    LinkLocalTypeConverters::class,
    SpaceXLaunchesTypeConverters::class)
abstract class SpaceXRoomDatabase : RoomDatabase() {

    abstract fun spaceXDao() : SpaceXDao
    abstract fun companyInfoDao() : ICompanyDao

    companion object{
        const val DB_NAME = "SPACEX_LAUNCHES"
    }
}