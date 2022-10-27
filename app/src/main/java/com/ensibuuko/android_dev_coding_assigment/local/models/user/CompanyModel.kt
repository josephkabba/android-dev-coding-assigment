package com.ensibuuko.android_dev_coding_assigment.local.models.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CompanyModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val bs: String,
    val catchPhrase: String,
    val name: String,

    val userId: Int
)