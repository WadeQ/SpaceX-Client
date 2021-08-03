package com.wadektech.spacexclient.utils



interface IEntityMapper<Entity, Domain> {

    fun mapFromEntity(entity: Entity) : Domain

    fun mapToEntity(domain: Domain) : Entity
}