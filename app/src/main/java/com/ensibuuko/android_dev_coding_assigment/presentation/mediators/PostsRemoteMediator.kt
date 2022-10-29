package com.ensibuuko.android_dev_coding_assigment.presentation.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.ensibuuko.android_dev_coding_assigment.domain.entities.PostEntity
import com.ensibuuko.android_dev_coding_assigment.domain.usecases.post.DeleteAllPostsUseCase
import com.ensibuuko.android_dev_coding_assigment.domain.usecases.post.GetPostsNetworkUseCase
import com.ensibuuko.android_dev_coding_assigment.domain.usecases.post.InsertAllPostsUseCase
import com.ensibuuko.android_dev_coding_assigment.domain.usecases.user.DeleteAllUsersUseCase
import com.ensibuuko.android_dev_coding_assigment.local.models.post.PostLocalModel
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PostsRemoteMediator @Inject constructor(
    private val getPostsNetworkUseCase: GetPostsNetworkUseCase,
    private val insertAllPostsUseCase: InsertAllPostsUseCase,
    private val deleteAllPostsUseCase: DeleteAllPostsUseCase,
    private val deleteAllUsersUseCase: DeleteAllUsersUseCase

) : RemoteMediator<Int, PostLocalModel>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PostLocalModel>
    ): MediatorResult {
        return try {

            val response = getPostsNetworkUseCase()

            when (response.status) {
                Resource.Status.SUCCESS -> {
                    response.data?.let { insertAllPostsUseCase(it) }
                }

                Resource.Status.ERROR -> {
                    throw Exception(response.message)
                }

                else -> {

                }
            }

            if (loadType == LoadType.REFRESH) {
                deleteAllPostsUseCase()
                deleteAllUsersUseCase()
            }

            MediatorResult.Success(
                endOfPaginationReached = true
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}