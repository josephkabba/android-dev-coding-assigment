package com.ensibuuko.android_dev_coding_assigment.domain.usecases.user

import com.ensibuuko.android_dev_coding_assigment.domain.UserRepository
import com.ensibuuko.android_dev_coding_assigment.domain.entities.UserEntity
import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteAllUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() {
        return userRepository.clear()
    }
}