package com.ensibuuko.android_dev_coding_assigment.local.relations.user

import androidx.room.Embedded
import androidx.room.Relation
import com.ensibuuko.android_dev_coding_assigment.local.models.user.AddressLocalModel
import com.ensibuuko.android_dev_coding_assigment.local.models.user.CompanyLocalModel
import com.ensibuuko.android_dev_coding_assigment.local.models.user.GeoLocalModel
import com.ensibuuko.android_dev_coding_assigment.local.models.user.UserLocalModel


data class UserRelation (
    @Embedded val user: UserLocalModel,

    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val geo: GeoLocalModel,

    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val address: AddressLocalModel,

    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val company: CompanyLocalModel,
)
