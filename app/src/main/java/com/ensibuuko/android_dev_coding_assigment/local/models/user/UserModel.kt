package com.ensibuuko.android_dev_coding_assigment.local.models.user

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class UserModel(
    val email: String,

    @PrimaryKey
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String,

    val createdAt: Long = System.currentTimeMillis()
)