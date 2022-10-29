package com.ensibuuko.android_dev_coding_assigment.domain.usecases.comments

import com.ensibuuko.android_dev_coding_assigment.domain.CommentsRepository
import com.ensibuuko.android_dev_coding_assigment.domain.entities.CommentEntity
import javax.inject.Inject

class InsertAllCommentsUseCase @Inject constructor(
    private val commentsRepository: CommentsRepository
) {
    suspend operator fun invoke(comments: List<CommentEntity>) {
        commentsRepository.insertComments(comments)
    }
}