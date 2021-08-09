package com.wadektech.spacexclient.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wadektech.spacexclient.data.local.models.CompanyInfo
import kotlinx.coroutines.flow.Flow


@Dao
interface ICompanyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCompanyInfo(info : CompanyInfo)


    @Query("SELECT * FROM company_info")
    fun getCompanyInfo() : Flow<CompanyInfo>
}