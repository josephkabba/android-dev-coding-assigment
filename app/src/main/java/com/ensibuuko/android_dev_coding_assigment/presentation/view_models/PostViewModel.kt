package com.ensibuuko.android_dev_coding_assigment.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.ensibuuko.android_dev_coding_assigment.data.mappers.CommentsDataMapper
import com.ensibuuko.android_dev_coding_assigment.domain.usecases.comments.*
import com.ensibuuko.android_dev_coding_assigment.local.mappaers.CommentsLocalMapper
import com.ensibuuko.android_dev_coding_assigment.presentation.mappers.CommentsUIMapper
import com.ensibuuko.android_dev_coding_assigment.presentation.mediators.CommentsRemoteMediator
import com.ensibuuko.android_dev_coding_assigment.presentation.models.CommentUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostViewModel @Inject constructor(
    private val getCommentsNetworkUseCase: GetCommentsNetworkUseCase,
    private val getCommentsUseCase: GetCommentsUseCase,
    private val insertCommentUseCase: InsertCommentUseCase,
    private val deleteCommentUseCase: DeleteCommentUseCase,
    private val commentsUIMapper: CommentsUIMapper,
    private val commentsDataMapper: CommentsDataMapper,
    private val updateCommentUseCase: UpdateCommentUseCase,
    private val commentsLocalMapper: CommentsLocalMapper,
    private val insertAllCommentsUseCase: InsertAllCommentsUseCase,
    private val deleteAllCommentsUseCase: DeleteAllCommentsUseCase
) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    fun getComments(postId: Int): Flow<PagingData<CommentUIModel>> {
        val commentsRemoteMediator = CommentsRemoteMediator(
            postId,
            insertAllCommentsUseCase,
            getCommentsNetworkUseCase,
            deleteAllCommentsUseCase
        )
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = commentsRemoteMediator
        ) {
            getCommentsUseCase(postId)
        }.flow.map { pagingData ->
            pagingData.map {
                val commentToData = commentsLocalMapper.toData(it)
                val commentToEntity = commentsDataMapper.toDomain(commentToData)
                commentsUIMapper.toUI(commentToEntity)
            }
        }.cachedIn(viewModelScope)
    }

    fun deleteComment(commentUIModel: CommentUIModel){
        viewModelScope.launch {
            deleteCommentUseCase(commentsUIMapper.toDomain(commentUIModel))
        }
    }


    fun updateComment(commentUIModel: CommentUIModel){
        viewModelScope.launch {
            updateCommentUseCase(commentsUIMapper.toDomain(commentUIModel))
        }
    }


    fun insertComment(commentUIModel: CommentUIModel){
        viewModelScope.launch {
            insertCommentUseCase(commentsUIMapper.toDomain(commentUIModel))
        }
    }

}