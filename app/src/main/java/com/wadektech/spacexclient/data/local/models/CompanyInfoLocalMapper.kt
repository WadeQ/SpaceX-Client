package com.wadektech.spacexclient.data.local.models

import com.wadektech.spacexclient.domain.CompanyInfoDomain
import com.wadektech.spacexclient.utils.IEntityMapper
import javax.inject.Inject


class CompanyInfoLocalMapper @Inject constructor(): IEntityMapper<CompanyInfo,CompanyInfoDomain>{

    override fun mapFromEntity(entity: CompanyInfo): CompanyInfoDomain {
        return CompanyInfoDomain(
            employees = entity.employees,
            founded = entity.founded,
            founder = entity.founder,
            launchSites = entity.launchSites,
            name = entity.name,
            valuation = entity.valuation
        )
    }

    override fun mapToEntity(domain: CompanyInfoDomain): CompanyInfo {
       return CompanyInfo(
           employees = domain.employees,
           founded = domain.founded,
           founder = domain.founder,
           launchSites = domain.launchSites,
           name = domain.name,
           valuation = domain.valuation
       )
    }
}