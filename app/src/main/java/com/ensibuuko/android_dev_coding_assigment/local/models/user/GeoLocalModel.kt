package com.ensibuuko.android_dev_coding_assigment.local.models.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ensibuuko.android_dev_coding_assigment.data.models.user.GeoDataModel

@Entity
data class GeoLocalModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val lat: String,
    val lng: String,
    val userId: Int
)

fun GeoLocalModel.toDataModel() = GeoDataModel(this.lat, this.lng)