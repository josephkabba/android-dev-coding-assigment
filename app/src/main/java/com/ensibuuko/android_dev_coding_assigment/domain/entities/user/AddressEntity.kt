package com.ensibuuko.android_dev_coding_assigment.domain.entities.user

data class AddressEntity(
    val city: String,
    val geo: GeoEntity,
    val street: String,
    val suite: String,
    val zipcode: String
)