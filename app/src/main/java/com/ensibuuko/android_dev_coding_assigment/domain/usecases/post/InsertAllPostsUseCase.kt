package com.ensibuuko.android_dev_coding_assigment.domain.usecases.post

import com.ensibuuko.android_dev_coding_assigment.domain.PostsRepository
import com.ensibuuko.android_dev_coding_assigment.domain.entities.PostEntity
import javax.inject.Inject

class InsertAllPostsUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {
    suspend operator fun invoke(posts: List<PostEntity>) {
        postsRepository.insertPosts(posts)
    }
}