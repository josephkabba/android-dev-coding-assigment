package com.ensibuuko.android_dev_coding_assigment.local.models.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ensibuuko.android_dev_coding_assigment.data.models.UserDataModel
import com.ensibuuko.android_dev_coding_assigment.data.models.user.AddressDataModel
import com.ensibuuko.android_dev_coding_assigment.data.models.user.CompanyDataModel


@Entity(tableName = "user_table")
data class UserLocalModel(
    val email: String,

    @PrimaryKey
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String,
    val persist: Boolean = false,

    val createdAt: Long = System.currentTimeMillis()
)

fun UserLocalModel.toDataModel(addressDataModel: AddressDataModel, companyDataModel: CompanyDataModel): UserDataModel = UserDataModel(
    address = addressDataModel,
    company = companyDataModel,
    email = this.email,
    id = this.id,
    name = this.name,
    phone = this.phone,
    username = this.username,
    website = this.website,
    persist = this.persist
)