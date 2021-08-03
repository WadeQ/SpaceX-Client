package com.wadektech.spacexclient.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wadektech.spacexclient.data.local.models.SpaceXLocalItem
import kotlinx.coroutines.flow.Flow


@Dao
interface SpaceXDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllSpaceXLaunches() : List<SpaceXLocalItem>

    fun getAllSpaceLaunches(search: String?, sort: OilNexusViewModel.BorderSortOrder) :
            Flow<MutableList<SpaceXLocalItem>> = when(sort){
        OilNexusViewModel.BorderSortOrder.BY_VEHICLE -> getAllBorderClearanceSummarySortedByVehicle(search)
        OilNexusViewModel.BorderSortOrder.BY_ENTRY_NO -> getAllBorderClearanceSummarySortedByEntryNumber(search)
        OilNexusViewModel.BorderSortOrder.BY_CONSIGNEE -> getAllBorderClearanceSummarySortedByConsignee(search)
    }

    @Query("SELECT * FROM space_launches WHERE launchSuccess LIKE '%' || :search || '%' OR launchYear ORDER BY launchDateLocal ASC")
    fun getAllLaunchesSortedByLaunchSuccess(search: String?) : Flow<MutableList<SpaceXLocalItem>>

    @Query("SELECT * FROM space_launches WHERE launchSuccess LIKE '%' || :search || '%' OR launchYear ORDER BY launchDateLocal DESC")
    fun getAllLaunchesSortedByDesc(search: String?) : Flow<MutableList<SpaceXLocalItem>>
}