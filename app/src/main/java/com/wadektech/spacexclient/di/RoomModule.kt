package com.wadektech.spacexclient.di

import android.content.Context
import androidx.room.Room
import com.wadektech.spacexclient.data.local.room.ICompanyDao
import com.wadektech.spacexclient.data.local.room.SpaceXDao
import com.wadektech.spacexclient.data.local.room.SpaceXRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): SpaceXRoomDatabase {
        return Room.databaseBuilder(
            context,
            SpaceXRoomDatabase::class.java,
            SpaceXRoomDatabase.DB_NAME).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideBorderDao(spaceXRoomDatabase: SpaceXRoomDatabase): SpaceXDao {
        return spaceXRoomDatabase.spaceXDao()
    }

    @Singleton
    @Provides
    fun provideCompanyDao(spaceXRoomDatabase: SpaceXRoomDatabase): ICompanyDao {
        return spaceXRoomDatabase.companyInfoDao()
    }


}