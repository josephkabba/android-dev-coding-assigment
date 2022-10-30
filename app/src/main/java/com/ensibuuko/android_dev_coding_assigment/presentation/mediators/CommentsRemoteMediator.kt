package com.ensibuuko.android_dev_coding_assigment.presentation.mediators

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.ensibuuko.android_dev_coding_assigment.domain.usecases.comments.*
import com.ensibuuko.android_dev_coding_assigment.local.models.comment.CommentLocalModel
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
class CommentsRemoteMediator(
    private val postId: Int,
    private val insertAllCommentsUseCase: InsertAllCommentsUseCase,
    private val getCommentsNetworkUseCase: GetCommentsNetworkUseCase,
    private val deleteAllCommentsUseCase: DeleteAllCommentsUseCase
) : RemoteMediator<Int, CommentLocalModel>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CommentLocalModel>
    ): MediatorResult {
        return try {

            val response = getCommentsNetworkUseCase(postId)

            when(response.status) {
                Resource.Status.SUCCESS -> {

                    if (loadType == LoadType.REFRESH) {
                        deleteAllCommentsUseCase()
                    }

                    response.data?.let { insertAllCommentsUseCase(it) }
                }

                Resource.Status.ERROR -> {
                    throw Exception(response.message)
                }

                else -> {

                }
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