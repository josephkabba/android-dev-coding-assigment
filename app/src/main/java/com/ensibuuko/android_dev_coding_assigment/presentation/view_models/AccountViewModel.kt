package com.ensibuuko.android_dev_coding_assigment.presentation.view_models

import androidx.lifecycle.ViewModel
import com.ensibuuko.android_dev_coding_assigment.domain.entities.UserEntity
import com.ensibuuko.android_dev_coding_assigment.domain.usecases.user.GetUserUseCase
import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {
    suspend fun getUser(id: Int, cachePolicy: CachePolicy.Type): Flow<Resource<UserEntity>> =
        getUserUseCase(id, cachePolicy)


}