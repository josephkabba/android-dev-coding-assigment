package com.ensibuuko.android_dev_coding_assigment.local.models.post

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts_table")
data class PostModel(
    var body: String,

    @PrimaryKey
    val id: Int,
    var title: String,
    val userId: Int,

    val createdAt: Long = System.currentTimeMillis()
)