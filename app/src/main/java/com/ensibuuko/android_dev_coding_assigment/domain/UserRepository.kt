package com.ensibuuko.android_dev_coding_assigment.domain


import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.domain.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUser(id: String, cachePolicy: CachePolicy): Flow<Result<UserEntity>>
}