package com.ensibuuko.android_dev_coding_assigment.domain.usecases.post

import androidx.paging.PagingSource
import com.ensibuuko.android_dev_coding_assigment.domain.PostsRepository
import com.ensibuuko.android_dev_coding_assigment.local.models.post.PostLocalModel
import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {
    operator fun invoke(): PagingSource<Int, PostLocalModel> {
        return postsRepository.getPosts()
    }
}