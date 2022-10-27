package com.ensibuuko.android_dev_coding_assigment.data

import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.domain.UserRepository
import com.ensibuuko.android_dev_coding_assigment.domain.entities.UserEntity
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl: UserRepository {
    override suspend fun getUser(id: String, cachePolicy: CachePolicy): Flow<Result<UserEntity>> {
        TODO("Not yet implemented")
    }
}