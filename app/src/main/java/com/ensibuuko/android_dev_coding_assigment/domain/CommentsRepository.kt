package com.ensibuuko.android_dev_coding_assigment.domain

import androidx.paging.PagingSource
import com.ensibuuko.android_dev_coding_assigment.domain.entities.CommentEntity
import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import com.ensibuuko.android_dev_coding_assigment.local.models.comment.CommentLocalModel

interface CommentsRepository {

    suspend fun insertComment(comment: CommentEntity)

    suspend fun insertComments(comments: List<CommentEntity>)

    suspend fun updateComment(comment: CommentEntity)

    suspend fun deleteComment(comment: CommentEntity)

    fun getComments(postId: Int): PagingSource<Int, CommentLocalModel>

    suspend fun getCommentsFromNetwork(postId: Int): Resource<List<CommentEntity>>

    suspend fun clear()
}