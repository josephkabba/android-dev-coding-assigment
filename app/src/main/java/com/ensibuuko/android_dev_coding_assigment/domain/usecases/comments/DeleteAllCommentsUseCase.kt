package com.ensibuuko.android_dev_coding_assigment.domain.usecases.comments

import com.ensibuuko.android_dev_coding_assigment.domain.CommentsRepository
import javax.inject.Inject

class DeleteAllCommentsUseCase @Inject constructor(
    private val commentsRepository: CommentsRepository
) {
    suspend operator fun invoke() {
        commentsRepository.clear()
    }
}