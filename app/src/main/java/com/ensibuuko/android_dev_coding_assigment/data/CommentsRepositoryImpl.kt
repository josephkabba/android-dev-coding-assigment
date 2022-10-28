package com.ensibuuko.android_dev_coding_assigment.data

import androidx.paging.PagingSource
import com.ensibuuko.android_dev_coding_assigment.data.mappers.CommentsDataMapper
import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import com.ensibuuko.android_dev_coding_assigment.local.models.comment.CommentLocalModel
import com.ensibuuko.android_dev_coding_assigment.domain.CommentsRepository
import com.ensibuuko.android_dev_coding_assigment.domain.entities.CommentEntity
import com.ensibuuko.android_dev_coding_assigment.local.dao.CommentDao
import com.ensibuuko.android_dev_coding_assigment.local.mappaers.CommentsLocalMapper
import com.ensibuuko.android_dev_coding_assigment.remote.CommentService
import com.ensibuuko.android_dev_coding_assigment.remote.mappers.CommentsRemoteMapper
import javax.inject.Inject

class CommentsRepositoryImpl @Inject constructor(
    private val commentsDataMapper: CommentsDataMapper,
    private val commentsRemoteMapper: CommentsRemoteMapper,
    private val commentsLocalMapper: CommentsLocalMapper,
    private val commentDao: CommentDao,
    private val commentService: CommentService
): CommentsRepository {
    override suspend fun insertComment(comment: CommentEntity) {
        val commentDataModel = commentsDataMapper.toData(comment)
        commentDao.insertComment(commentsLocalMapper.toLocal(commentDataModel))
        commentService.createComment(comment.postId, commentsRemoteMapper.toRemote(commentDataModel))
    }

    override suspend fun updateComment(comment: CommentEntity) {
        val commentDataModel = commentsDataMapper.toData(comment)
        commentDao.updateComment(commentsLocalMapper.toLocal(commentDataModel))
        commentService.updatePost(comment.postId, comment.postId, commentsRemoteMapper.toRemote(commentDataModel))
    }

    override suspend fun deleteComment(comment: CommentEntity) {
        val commentDataModel = commentsDataMapper.toData(comment)
        commentDao.deleteComment(commentsLocalMapper.toLocal(commentDataModel))
        commentService.deleteComment(comment.postId, comment.id)
    }

    override suspend fun getComments(
        postId: Int,
        cachePolicy: CachePolicy
    ): PagingSource<Int, CommentLocalModel> {
        return when(cachePolicy.type){
            CachePolicy.Type.REFRESH -> {
                commentService.getPostComments(postId).onSuccess {
                    val results = it.map { comment ->
                        commentsRemoteMapper.toData(comment)
                    }

                    val resultsToLocal = results.map { comment ->
                        commentsLocalMapper.toLocal(comment)
                    }

                    commentDao.insertComments(resultsToLocal)

                }
               commentDao.getComments(postId)
            }

            else -> {
                commentDao.getComments(postId)
            }
        }
    }

}