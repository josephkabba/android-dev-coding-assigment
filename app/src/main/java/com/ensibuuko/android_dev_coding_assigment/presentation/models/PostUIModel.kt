package com.ensibuuko.android_dev_coding_assigment.presentation.models

data class PostUIModel(
    var body: String,
    val id: Int,
    var title: String,
    val userId: Int,
    val createdAt: Long
)