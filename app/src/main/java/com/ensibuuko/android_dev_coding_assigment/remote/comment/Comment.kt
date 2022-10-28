package com.ensibuuko.android_dev_coding_assigment.remote.comment

data class Comment(
    var body: String,
    var email: String,
    val id: Int,
    var name: String,
    val postId: Int,
)