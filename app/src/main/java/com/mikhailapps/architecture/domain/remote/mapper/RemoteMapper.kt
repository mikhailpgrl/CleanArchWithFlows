package com.mikhailapps.architecture.domain.remote.mapper

interface RemoteMapper<Dto, Domain> {

    fun from(dto: Dto): Domain

    fun to(domain: Domain): Dto
}