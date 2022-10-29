package com.ensibuuko.android_dev_coding_assigment.domain.entities

import com.ensibuuko.android_dev_coding_assigment.domain.entities.user.AddressEntity
import com.ensibuuko.android_dev_coding_assigment.domain.entities.user.CompanyEntity

data class UserEntity(
    val address: AddressEntity,
    val company: CompanyEntity,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String,
    val createdAt: Long,
    val persist: Boolean = false
)