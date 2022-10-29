package com.ensibuuko.android_dev_coding_assigment.domain.usecases.user

import com.ensibuuko.android_dev_coding_assigment.domain.UserRepository
import com.ensibuuko.android_dev_coding_assigment.domain.entities.UserEntity
import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(id: Int, cachePolicy: CachePolicy.Type, saveUser: Boolean = false): Flow<Resource<UserEntity>> {
        return userRepository.getUser(id, cachePolicy, saveUser)
    }
}