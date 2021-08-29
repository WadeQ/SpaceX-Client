package com.wadektech.spacexclient.data.remote.models

import com.wadektech.spacexclient.domain.CompanyInfoDomain
import com.wadektech.spacexclient.utils.IEntityMapper
import javax.inject.Inject


class CompanyInfoRemoteMapper @Inject constructor()
    : IEntityMapper<CompanyInfoRemote,CompanyInfoDomain>{

    override fun mapFromEntity(entity: CompanyInfoRemote): CompanyInfoDomain {
       return CompanyInfoDomain(
           employees = entity.employees,
           founded = entity.founded,
           founder = entity.founder,
           launchSites = entity.launchSites,
           name = entity.name,
           valuation = entity.valuation

       )
    }

    override fun mapToEntity(domain: CompanyInfoDomain): CompanyInfoRemote {
        return CompanyInfoRemote(
            employees = domain.employees,
            founded = domain.founded,
            founder = domain.founder,
            launchSites = domain.launchSites,
            name = domain.name,
            valuation = domain.valuation
        )
    }
}
