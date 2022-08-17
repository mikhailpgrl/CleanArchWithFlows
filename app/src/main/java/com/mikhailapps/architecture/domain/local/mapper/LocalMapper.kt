package com.mikhailapps.architecture.domain.local.mapper

interface LocalMapper<Domain, Entity> {

    fun from(domain: Domain): Entity

    fun to(entity: Entity): Domain

}