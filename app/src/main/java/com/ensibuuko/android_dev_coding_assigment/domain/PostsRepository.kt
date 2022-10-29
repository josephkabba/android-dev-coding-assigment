package com.ensibuuko.android_dev_coding_assigment.domain

import androidx.paging.PagingSource
import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import com.ensibuuko.android_dev_coding_assigment.domain.entities.PostEntity
import com.ensibuuko.android_dev_coding_assigment.local.models.post.PostLocalModel

interface PostsRepository {

    suspend fun insertPost(post: PostEntity)

    suspend fun insertPosts(posts: List<PostEntity>)

    suspend fun updatePost(post: PostEntity)

    suspend fun deletePost(post: PostEntity)

    fun getPosts(): PagingSource<Int, PostLocalModel>

    suspend fun getPostsFromNetwork(): Resource<List<PostEntity>>

    suspend fun clear()
}