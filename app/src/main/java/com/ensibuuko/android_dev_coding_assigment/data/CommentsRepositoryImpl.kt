package com.ensibuuko.android_dev_coding_assigment.data

import androidx.paging.PagingSource
import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import com.ensibuuko.android_dev_coding_assigment.local.models.comment.CommentModel
import com.ensibuuko.android_dev_coding_assigment.domain.CommentsRepository

class CommentsRepositoryImpl: CommentsRepository {
    override suspend fun insertComment(comment: CommentModel) {
        TODO("Not yet implemented")
    }

    override suspend fun updateComment(comment: CommentModel) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteComment(comment: CommentModel) {
        TODO("Not yet implemented")
    }

    override suspend fun getComments(
        postId: Int,
        cachePolicy: CachePolicy
    ): PagingSource<Int, Resource<List<CommentModel>>> {
        TODO("Not yet implemented")
    }
}