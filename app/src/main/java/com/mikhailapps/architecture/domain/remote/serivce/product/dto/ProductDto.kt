package com.mikhailapps.architecture.domain.remote.serivce.product.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductDto(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String
)
