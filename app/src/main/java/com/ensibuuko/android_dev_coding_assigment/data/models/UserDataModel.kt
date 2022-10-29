package com.ensibuuko.android_dev_coding_assigment.data.models

import com.ensibuuko.android_dev_coding_assigment.data.models.user.AddressDataModel
import com.ensibuuko.android_dev_coding_assigment.data.models.user.CompanyDataModel
import com.ensibuuko.android_dev_coding_assigment.domain.entities.user.AddressEntity
import com.ensibuuko.android_dev_coding_assigment.domain.entities.user.CompanyEntity

data class UserDataModel(
    val address: AddressDataModel,
    val company: CompanyDataModel,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String,
    val createdAt: Long = System.currentTimeMillis(),
    val persist: Boolean = false
)