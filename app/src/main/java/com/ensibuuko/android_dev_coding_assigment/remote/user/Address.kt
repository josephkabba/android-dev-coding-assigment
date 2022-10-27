package com.ensibuuko.android_dev_coding_assigment.remote.user

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)