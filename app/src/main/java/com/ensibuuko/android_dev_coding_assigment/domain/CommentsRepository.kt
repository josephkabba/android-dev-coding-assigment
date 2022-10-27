package com.ensibuuko.android_dev_coding_assigment.domain

import androidx.paging.PagingSource
import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import com.ensibuuko.android_dev_coding_assigment.local.models.comment.CommentModel

interface CommentsRepository {

    suspend fun insertComment(comment: CommentModel)

    suspend fun updateComment(comment: CommentModel)

    suspend fun deleteComment(comment: CommentModel)

    suspend fun getComments(postId: Int, cachePolicy: CachePolicy): PagingSource<Int, Resource<List<CommentModel>>>
}