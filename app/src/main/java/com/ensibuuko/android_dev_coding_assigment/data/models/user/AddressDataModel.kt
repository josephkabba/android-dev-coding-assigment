package com.ensibuuko.android_dev_coding_assigment.data.models.user

data class AddressDataModel(
    val city: String,
    val geo: GeoDataModel,
    val street: String,
    val suite: String,
    val zipcode: String
)