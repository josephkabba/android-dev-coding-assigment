package com.ensibuuko.android_dev_coding_assigment.presentation.models

data class CommentUIModel(
    var body: String,
    var email: String,
    val id: Int,
    var name: String,
    val postId: Int,
    val createdAt: Long
)