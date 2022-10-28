package com.ensibuuko.android_dev_coding_assigment.data.models

data class CommentDataModel(
    var body: String,
    var email: String,
    val id: Int,
    var name: String,
    val postId: Int,
    val createdAt: Long = System.currentTimeMillis()
)