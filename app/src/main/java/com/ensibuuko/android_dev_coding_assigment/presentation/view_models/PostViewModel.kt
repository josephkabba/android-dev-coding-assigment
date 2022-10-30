package com.ensibuuko.android_dev_coding_assigment.presentation.view_models

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.ensibuuko.android_dev_coding_assigment.data.mappers.CommentsDataMapper
import com.ensibuuko.android_dev_coding_assigment.domain.usecases.comments.*
import com.ensibuuko.android_dev_coding_assigment.domain.usecases.user.GetUserUseCase
import com.ensibuuko.android_dev_coding_assigment.local.mappaers.CommentsLocalMapper
import com.ensibuuko.android_dev_coding_assigment.presentation.mappers.CommentsUIMapper
import com.ensibuuko.android_dev_coding_assigment.presentation.mappers.UserUIMapper
import com.ensibuuko.android_dev_coding_assigment.presentation.mediators.CommentsRemoteMediator
import com.ensibuuko.android_dev_coding_assigment.presentation.models.CommentUIModel
import com.ensibuuko.android_dev_coding_assigment.presentation.models.UserUIModel
import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
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
    private val deleteAllCommentsUseCase: DeleteAllCommentsUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val userUIMapper: UserUIMapper,
    private val dataStore: DataStore<Preferences>
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

    suspend fun getUser(id: Int, cachePolicy: CachePolicy.Type, saveUser: Boolean = false): Flow<Resource<UserUIModel>?> {
        val user = getUserUseCase(id, cachePolicy, saveUser).map { when(it.status){
            Resource.Status.SUCCESS -> {
                Resource.success(it.data?.let { it1 -> userUIMapper.toUI(it1) })
            }

            Resource.Status.ERROR -> {
                it.message?.let { it1 -> Resource.error(it1) }
            }

            else -> {
                Resource.loading()
            }
        } }
        return user
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

    private val USER_ID = intPreferencesKey("example_counter")

    val currentUserId: Flow<Int?> = dataStore.data.map { preferences ->
        preferences[USER_ID]
    }

}