package com.ensibuuko.android_dev_coding_assigment.remote.post

import com.ensibuuko.android_dev_coding_assigment.data.models.PostDataModel

data class Post(
    var body: String,
    val id: Int,
    var title: String,
    val userId: Int,
    val createdAt: Long = System.currentTimeMillis()
)