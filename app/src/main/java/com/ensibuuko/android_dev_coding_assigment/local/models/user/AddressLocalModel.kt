package com.ensibuuko.android_dev_coding_assigment.local.models.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ensibuuko.android_dev_coding_assigment.data.models.user.AddressDataModel
import com.ensibuuko.android_dev_coding_assigment.data.models.user.GeoDataModel

@Entity
data class AddressLocalModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val city: String,
    val street: String,
    val suite: String,
    val zipcode: String,

    val userId: Int
)

fun AddressLocalModel.toDataModel(geoDataModel: GeoDataModel): AddressDataModel = AddressDataModel(
    geo = geoDataModel,
    city = this.city,
    street = this.street,
    suite = this.suite,
    zipcode = this.zipcode
)