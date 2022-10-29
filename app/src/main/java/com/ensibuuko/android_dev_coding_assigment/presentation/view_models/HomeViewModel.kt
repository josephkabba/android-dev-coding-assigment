package com.ensibuuko.android_dev_coding_assigment.presentation.view_models

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.ensibuuko.android_dev_coding_assigment.data.mappers.PostDataMapper
import com.ensibuuko.android_dev_coding_assigment.domain.entities.UserEntity
import com.ensibuuko.android_dev_coding_assigment.domain.usecases.post.*
import com.ensibuuko.android_dev_coding_assigment.domain.usecases.user.DeleteAllUsersUseCase
import com.ensibuuko.android_dev_coding_assigment.domain.usecases.user.GetUserUseCase
import com.ensibuuko.android_dev_coding_assigment.local.mappaers.PostLocalMapper
import com.ensibuuko.android_dev_coding_assigment.presentation.mappers.PostUIMapper
import com.ensibuuko.android_dev_coding_assigment.presentation.mediators.PostsRemoteMediator
import com.ensibuuko.android_dev_coding_assigment.presentation.models.PostUIModel
import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val postsRemoteMediator: PostsRemoteMediator,
    private val postUIMapper: PostUIMapper,
    private val postLocalMapper: PostLocalMapper,
    private val postDataMapper: PostDataMapper,
    private val insertPostUseCase: InsertPostUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val updatePostUseCase: UpdatePostUseCase,
    private val deletePostUseCase: DeletePostUseCase,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val posts = Pager(
        config = PagingConfig(pageSize = 10),
        remoteMediator = postsRemoteMediator
    ) {
        getPostsUseCase()
    }.flow.map { pagingData ->
        pagingData.map {
            val postToData = postLocalMapper.toData(it)
            val postToEntity = postDataMapper.toDomain(postToData)
            postUIMapper.toUI(postToEntity)
        }
    }.cachedIn(viewModelScope)

    suspend fun getUser(id: Int, cachePolicy: CachePolicy.Type): Flow<Resource<UserEntity>> =
        getUserUseCase(id, cachePolicy)


    fun createPost(post: PostUIModel) {
        viewModelScope.launch {
            val postEntity = postUIMapper.toDomain(post)
            insertPostUseCase(postEntity)
        }
    }

    fun updatePost(post: PostUIModel) {
        viewModelScope.launch {
            val postEntity = postUIMapper.toDomain(post)
            updatePostUseCase(postEntity)
        }
    }

    fun deletePost(post: PostUIModel) {
        viewModelScope.launch {
            val postEntity = postUIMapper.toDomain(post)
            deletePostUseCase(postEntity)
        }
    }

    private val USER_ID = intPreferencesKey("example_counter")

    val currentUserId: Flow<Int?> = dataStore.data.map { preferences ->
        preferences[USER_ID]
    }
}