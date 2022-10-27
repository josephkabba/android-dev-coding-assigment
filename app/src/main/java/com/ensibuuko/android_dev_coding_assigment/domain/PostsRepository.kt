package com.ensibuuko.android_dev_coding_assigment.domain

import androidx.paging.PagingSource
import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import com.ensibuuko.android_dev_coding_assigment.domain.entities.PostEntity

interface PostsRepository {

    suspend fun insertPost(post: PostEntity)

    suspend fun updatePost(post: PostEntity)

    suspend fun deletePost(post: PostEntity)

    suspend fun getPosts(id: Int, cachePolicy: CachePolicy): PagingSource<Int, Resource<List<PostEntity>>>
}