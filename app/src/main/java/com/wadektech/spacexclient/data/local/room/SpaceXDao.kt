package com.wadektech.spacexclient.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wadektech.spacexclient.data.local.models.SpaceXLocalItem
import com.wadektech.spacexclient.utils.SortOrder
import kotlinx.coroutines.flow.Flow


@Dao
interface SpaceXDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllSpaceXLaunches(launches: List<SpaceXLocalItem>)

    fun getAllSpaceLaunches(search: String?, sort: SortOrder, success: Boolean) :
            Flow<MutableList<SpaceXLocalItem>> = when(sort){
        SortOrder.FROM_ASC_TO_DESC -> getAllLaunchesSortedByLaunchSuccess(search,success)
        SortOrder.FROM_DESC_TO_ASC -> getAllLaunchesSortedByDesc(search,success)
    }

    @Query("SELECT * FROM space_launches WHERE launchSuccess = :success AND launchYear LIKE '%' || :search || '%' ORDER BY launchYear ASC")
    fun getAllLaunchesSortedByLaunchSuccess(search: String?, success: Boolean) : Flow<MutableList<SpaceXLocalItem>>

    @Query("SELECT * FROM space_launches WHERE  launchSuccess = :success AND launchYear LIKE '%' || :search || '%' ORDER BY launchYear DESC")
    fun getAllLaunchesSortedByDesc(search: String?, success: Boolean) : Flow<MutableList<SpaceXLocalItem>>
}