package com.ensibuuko.android_dev_coding_assigment.local.models.comment

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "comments_table")
data class CommentModel(
    var body: String,
    var email: String,

    @PrimaryKey
    val id: Int,
    var name: String,
    val postId: Int,

    val createdAt: Long = System.currentTimeMillis()
)