package com.ensibuuko.android_dev_coding_assigment.remote.user

data class User(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String,
    val createdAt: Long = System.currentTimeMillis()
)