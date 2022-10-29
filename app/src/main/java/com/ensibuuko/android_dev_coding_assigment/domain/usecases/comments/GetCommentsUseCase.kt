package com.ensibuuko.android_dev_coding_assigment.domain.usecases.comments

import androidx.paging.PagingSource
import com.ensibuuko.android_dev_coding_assigment.domain.CommentsRepository
import com.ensibuuko.android_dev_coding_assigment.local.models.comment.CommentLocalModel
import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(
    private val commentsRepository: CommentsRepository
) {
    operator fun invoke(postId: Int): PagingSource<Int, CommentLocalModel>{
        return commentsRepository.getComments(postId)
    }
}