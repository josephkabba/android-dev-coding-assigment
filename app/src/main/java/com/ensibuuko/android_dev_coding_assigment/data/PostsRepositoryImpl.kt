package com.ensibuuko.android_dev_coding_assigment.data

import androidx.paging.PagingSource
import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import com.ensibuuko.android_dev_coding_assigment.domain.PostsRepository
import com.ensibuuko.android_dev_coding_assigment.domain.entities.PostEntity

class PostsRepositoryImpl: PostsRepository {
    override suspend fun insertPost(post: PostEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun updatePost(post: PostEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePost(post: PostEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getPosts(
        id: Int,
        cachePolicy: CachePolicy
    ): PagingSource<Int, Resource<List<PostEntity>>> {
        TODO("Not yet implemented")
    }
}