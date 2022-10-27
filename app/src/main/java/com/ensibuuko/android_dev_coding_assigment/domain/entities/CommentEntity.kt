package com.ensibuuko.android_dev_coding_assigment.domain.entities

data class CommentEntity(
    var body: String,
    var email: String,
    val id: Int,
    var name: String,
    val postId: Int,
    val createdAt: Long
)