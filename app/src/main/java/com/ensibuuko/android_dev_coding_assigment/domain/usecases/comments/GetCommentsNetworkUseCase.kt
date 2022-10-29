package com.ensibuuko.android_dev_coding_assigment.domain.usecases.comments


import com.ensibuuko.android_dev_coding_assigment.domain.CommentsRepository
import com.ensibuuko.android_dev_coding_assigment.domain.entities.CommentEntity
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import javax.inject.Inject

class GetCommentsNetworkUseCase @Inject constructor(
    private val commentsRepository: CommentsRepository
) {
    suspend operator fun invoke(postId: Int): Resource<List<CommentEntity>> {
        return commentsRepository.getCommentsFromNetwork(postId)
    }
}