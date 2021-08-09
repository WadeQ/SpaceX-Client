package com.wadektech.spacexclient.data.local.models

import com.wadektech.spacexclient.domain.Links
import com.wadektech.spacexclient.domain.Rocket
import com.wadektech.spacexclient.domain.SpaceXDomainItem
import com.wadektech.spacexclient.utils.IEntityMapper
import javax.inject.Inject


class LocalModelMapper
@Inject constructor() : IEntityMapper<SpaceXLocalItem , SpaceXDomainItem>{
    override fun mapFromEntity(entity: SpaceXLocalItem): SpaceXDomainItem {
        return SpaceXDomainItem(
            id = entity.flightNumber!!,
            launchDateLocal = entity.launchDateLocal!!,
            launchDateUtc = entity.launchDateUtc!!,
            launchSuccess = entity.launchSuccess!!,
            launchYear = entity.launchYear!!,
            links = entity.linksLocal as Links,
            missionName = entity.missionName!!,
            rocket = entity.rocket as Rocket
        )
    }

    override fun mapToEntity(domain: SpaceXDomainItem): SpaceXLocalItem {
        return SpaceXLocalItem(
            flightNumber = domain.id,
            launchDateLocal = domain.launchDateLocal,
            launchDateUtc = domain.launchDateUtc,
            launchSuccess = domain.launchSuccess,
            launchYear = domain.launchYear,
            linksLocal = domain.links as LinksLocal,
            missionName = domain.missionName,
            rocket = domain.rocket as RocketLocal
        )
    }

    fun mapFromEntityAsList(listOfEntity: List<SpaceXLocalItem>) : List<SpaceXDomainItem>{
        return listOfEntity.map {
            mapFromEntity(it)
        }
    }

    fun mapToEntityAsList(listOfDomain: List<SpaceXDomainItem>) : List<SpaceXLocalItem>{
        return listOfDomain.map {
            mapToEntity(it)
        }
    }
}