package com.ensibuuko.android_dev_coding_assigment.local.models.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ensibuuko.android_dev_coding_assigment.data.models.user.CompanyDataModel

@Entity
data class CompanyLocalModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val bs: String,
    val catchPhrase: String,
    val name: String,

    val userId: Int
)

fun CompanyLocalModel.toDataModel(): CompanyDataModel{
    return CompanyDataModel(
        this.bs,
        this.catchPhrase,
        this.name,
    )
}