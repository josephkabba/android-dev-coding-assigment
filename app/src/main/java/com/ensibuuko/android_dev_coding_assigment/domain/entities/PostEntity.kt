package com.ensibuuko.android_dev_coding_assigment.domain.entities

data class PostEntity(
    var body: String,
    val id: Int,
    var title: String,
    val userId: Int,
    val createdAt: Long
)