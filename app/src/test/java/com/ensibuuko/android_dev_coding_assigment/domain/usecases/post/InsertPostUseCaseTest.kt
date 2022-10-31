package com.ensibuuko.android_dev_coding_assigment.domain.usecases.post

import com.ensibuuko.android_dev_coding_assigment.domain.PostsRepository
import com.ensibuuko.android_dev_coding_assigment.domain.entities.PostEntity
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class InsertPostUseCaseTest {

    @Test
    fun test_insertTestUseCase() = runBlocking {
        val post: PostEntity = mock()
        val postsRepository: PostsRepository = mock()
        val insertPostUseCase = InsertPostUseCase(postsRepository)

        val actual = insertPostUseCase(post)

        verify(postsRepository).insertPost(post)
    }
}