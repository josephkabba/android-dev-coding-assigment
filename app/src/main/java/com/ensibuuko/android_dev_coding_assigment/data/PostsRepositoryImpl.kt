package com.ensibuuko.android_dev_coding_assigment.data

import androidx.paging.PagingSource
import com.ensibuuko.android_dev_coding_assigment.data.mappers.PostDataMapper
import com.ensibuuko.android_dev_coding_assigment.data.mappers.UserDataMapper
import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import com.ensibuuko.android_dev_coding_assigment.domain.PostsRepository
import com.ensibuuko.android_dev_coding_assigment.domain.entities.PostEntity
import com.ensibuuko.android_dev_coding_assigment.local.dao.PostDao
import com.ensibuuko.android_dev_coding_assigment.local.mappaers.PostLocalMapper
import com.ensibuuko.android_dev_coding_assigment.local.models.post.PostLocalModel
import com.ensibuuko.android_dev_coding_assigment.remote.PostService
import com.ensibuuko.android_dev_coding_assigment.remote.mappers.PostRemoteMapper
import com.ensibuuko.android_dev_coding_assigment.remote.mappers.UserRemoteMapper
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postLocalMapper: PostLocalMapper,
    private val postRemoteMapper: PostRemoteMapper,
    private val postDataMapper: PostDataMapper,
    private val postService: PostService,
    private val postDao: PostDao
): PostsRepository {
    override suspend fun insertPost(post: PostEntity) {
        val postDataModel = postDataMapper.toData(post)
        postDao.insertPost(postLocalMapper.toLocal(postDataModel))
        postService.createPost(postRemoteMapper.toRemote(postDataModel))
    }

    override suspend fun updatePost(post: PostEntity) {
        val postDataModel = postDataMapper.toData(post)
        postDao.updatePost(postLocalMapper.toLocal(postDataModel))
        postService.updatePost(post.id, postRemoteMapper.toRemote(postDataModel))
    }

    override suspend fun deletePost(post: PostEntity) {
        val postDataModel = postDataMapper.toData(post)
        postDao.deletePost(postLocalMapper.toLocal(postDataModel))
        postService.deletePost(post.id)
    }

    override suspend fun getPosts(
        id: Int,
        cachePolicy: CachePolicy
    ): PagingSource<Int, PostLocalModel> {
        return when(cachePolicy.type){
            CachePolicy.Type.REFRESH -> {
               postService.getPosts().onSuccess {
                   val results = it.map { post ->
                       postRemoteMapper.toData(post)
                   }

                   val resultsToLocal = results.map { post ->
                       postLocalMapper.toLocal(post)
                   }

                   postDao.insertPosts(resultsToLocal)

               }
                postDao.getPosts()
            }

            else -> {
                postDao.getPosts()
            }
        }
    }
}