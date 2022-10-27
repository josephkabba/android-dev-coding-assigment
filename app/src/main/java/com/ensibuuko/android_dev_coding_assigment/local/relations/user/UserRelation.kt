package com.ensibuuko.android_dev_coding_assigment.local.relations.user

import androidx.room.Embedded
import androidx.room.Relation
import com.ensibuuko.android_dev_coding_assigment.local.models.user.AddressModel
import com.ensibuuko.android_dev_coding_assigment.local.models.user.CompanyModel
import com.ensibuuko.android_dev_coding_assigment.local.models.user.GeoModel
import com.ensibuuko.android_dev_coding_assigment.local.models.user.UserModel


data class UserRelation (
    @Embedded val user: UserModel,

    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val geo: GeoModel,

    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val address: AddressModel,

    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val company: CompanyModel,
)
