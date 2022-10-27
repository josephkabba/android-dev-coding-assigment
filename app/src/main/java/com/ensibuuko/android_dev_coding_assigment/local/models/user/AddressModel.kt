package com.ensibuuko.android_dev_coding_assigment.local.models.user

import androidx.room.PrimaryKey

data class AddressModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val city: String,
    val street: String,
    val suite: String,
    val zipcode: String,

    val userId: Int
)