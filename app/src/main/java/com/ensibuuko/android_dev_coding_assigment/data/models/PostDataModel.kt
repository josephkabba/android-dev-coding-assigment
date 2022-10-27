package com.ensibuuko.android_dev_coding_assigment.data.models

data class PostDataModel(
    var body: String,
    val id: Int,
    var title: String,
    val userId: Int,
    val createdAt: Long
)