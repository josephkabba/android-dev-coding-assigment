package com.ensibuuko.android_dev_coding_assigment.local.models.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GeoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val lat: String,
    val lng: String,


    val userId: Int
)