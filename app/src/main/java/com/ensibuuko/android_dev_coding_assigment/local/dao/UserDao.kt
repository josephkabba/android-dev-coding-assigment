package com.ensibuuko.android_dev_coding_assigment.local.dao

import androidx.room.*
import com.ensibuuko.android_dev_coding_assigment.local.models.user.AddressModel
import com.ensibuuko.android_dev_coding_assigment.local.models.user.CompanyModel
import com.ensibuuko.android_dev_coding_assigment.local.models.user.GeoModel
import com.ensibuuko.android_dev_coding_assigment.local.models.user.UserModel
import com.ensibuuko.android_dev_coding_assigment.local.relations.user.UserRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: UserModel)

    @Transaction
    @Query("SELECT * FROM user_table WHERE id=:id LIMIT 1")
    suspend fun getUser(id: String): Flow<UserRelation>

    @Insert
    suspend fun insertAddress(address: AddressModel)

    @Insert
    suspend fun insertCompany(company: CompanyModel)

    @Insert
    suspend fun insertGeo(geo: GeoModel)

    @Query("DELETE FROM user_table")
    suspend fun clear()
}