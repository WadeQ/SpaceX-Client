package com.wadektech.spacexclient.data.remote


import com.wadektech.spacexclient.data.remote.models.SpaceXRemoteItem
import com.wadektech.spacexclient.domain.Links
import com.wadektech.spacexclient.domain.Rocket
import com.wadektech.spacexclient.domain.SpaceXDomainItem
import com.wadektech.spacexclient.utils.IEntityMapper
import javax.inject.Inject


class RemoteModelMapper
@Inject constructor() : IEntityMapper<SpaceXRemoteItem, SpaceXDomainItem>{

    override fun mapFromEntity(entity: SpaceXRemoteItem): SpaceXDomainItem {
        return SpaceXDomainItem(
            id = entity.flightNumber,
            launchDateLocal = entity.launchDateLocal,
            launchDateUtc = entity.launchDateUtc,
            launchSuccess = entity.launchSuccess,
            launchYear = entity.launchYear,
            links = entity.linksLocal as Links,
            missionName = entity.missionName,
            rocket = entity.rocketLocal as Rocket
        )
    }

    override fun mapToEntity(domain: SpaceXDomainItem): SpaceXRemoteItem {
       return SpaceXRemoteItem(
           flightNumber = domain.id,
           launchDateLocal = domain.launchDateLocal,
           launchDateUtc = domain.launchDateUtc,
           launchSuccess = domain.launchSuccess,
           launchYear = domain.launchYear,
           linksLocal = domain.links as com.wadektech.spacexclient.data.remote.models.Links,
           missionName = domain.missionName,
           rocketLocal = domain.rocket as com.wadektech.spacexclient.data.remote.models.Rocket
       )
    }

    fun mapFromEntityAsList(listOfEntity: List<SpaceXRemoteItem>) : List<SpaceXDomainItem>{
        return listOfEntity.map {
            mapFromEntity(it)
        }
    }

    fun mapToEntityAsList(listOfDomain: List<SpaceXDomainItem>) : List<SpaceXRemoteItem>{
        return listOfDomain.map {
            mapToEntity(it)
        }
    }
}