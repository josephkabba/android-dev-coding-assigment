package com.ensibuuko.android_dev_coding_assigment.domain.usecases.post

import com.ensibuuko.android_dev_coding_assigment.domain.PostsRepository
import com.ensibuuko.android_dev_coding_assigment.domain.entities.PostEntity
import javax.inject.Inject


class UpdatePostUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {
    suspend operator fun invoke(post: PostEntity) {
        postsRepository.updatePost(post)
    }
}