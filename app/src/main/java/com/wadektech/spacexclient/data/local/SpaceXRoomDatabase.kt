package com.wadektech.spacexclient.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wadektech.spacexclient.data.local.models.SpaceXLocalItem


@Database(entities = [SpaceXLocalItem::class], version = 1, exportSchema = false)
abstract class SpaceXRoomDatabase : RoomDatabase() {

    abstract fun spaceXDao() : SpaceXDao


    companion object{
        const val DB_NAME = "SPACEX_LAUNCHES"
    }
}