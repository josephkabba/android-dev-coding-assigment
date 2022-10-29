package com.ensibuuko.android_dev_coding_assigment.presentation.models.user

data class AddressUIModel(
    val city: String,
    val geo: GeoUIModel,
    val street: String,
    val suite: String,
    val zipcode: String
)