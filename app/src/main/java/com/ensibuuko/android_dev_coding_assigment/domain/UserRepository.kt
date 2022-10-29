package com.ensibuuko.android_dev_coding_assigment.domain


import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.domain.entities.UserEntity
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(id: Int, cachePolicy: CachePolicy.Type, saveUser: Boolean = false): Flow<Resource<UserEntity>>

    suspend fun clear()
}