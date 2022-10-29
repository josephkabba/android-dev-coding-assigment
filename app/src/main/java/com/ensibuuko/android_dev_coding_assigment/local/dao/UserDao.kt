package com.ensibuuko.android_dev_coding_assigment.local.dao

import androidx.room.*
import com.ensibuuko.android_dev_coding_assigment.local.models.user.AddressLocalModel
import com.ensibuuko.android_dev_coding_assigment.local.models.user.CompanyLocalModel
import com.ensibuuko.android_dev_coding_assigment.local.models.user.GeoLocalModel
import com.ensibuuko.android_dev_coding_assigment.local.models.user.UserLocalModel
import com.ensibuuko.android_dev_coding_assigment.local.relations.user.UserRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserLocalModel)

    @Transaction
    @Query("SELECT * FROM user_table WHERE id=:id LIMIT 1")
    suspend fun getUser(id: Int): UserRelation?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddress(address: AddressLocalModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompany(company: CompanyLocalModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGeo(geo: GeoLocalModel)

    @Query("DELETE FROM user_table WHERE persist=0")
    suspend fun clear()
}