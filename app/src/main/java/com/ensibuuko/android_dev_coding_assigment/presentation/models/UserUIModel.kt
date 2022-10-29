package com.ensibuuko.android_dev_coding_assigment.presentation.models

import com.ensibuuko.android_dev_coding_assigment.data.models.user.AddressDataModel
import com.ensibuuko.android_dev_coding_assigment.data.models.user.CompanyDataModel
import com.ensibuuko.android_dev_coding_assigment.presentation.models.user.AddressUIModel
import com.ensibuuko.android_dev_coding_assigment.presentation.models.user.CompanyUIModel

data class UserUIModel(
    val address: AddressUIModel,
    val company: CompanyUIModel,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String,
    val createdAt: Long
)