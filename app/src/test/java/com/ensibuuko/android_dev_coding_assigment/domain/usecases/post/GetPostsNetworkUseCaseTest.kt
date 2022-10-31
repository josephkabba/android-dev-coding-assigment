package com.ensibuuko.android_dev_coding_assigment.domain.usecases.post

import com.ensibuuko.android_dev_coding_assigment.domain.PostsRepository
import com.ensibuuko.android_dev_coding_assigment.domain.entities.PostEntity
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetPostsNetworkUseCaseTest {

    @Test
    fun test_GetPostsNetworkUseCase() = runBlocking{
        val postList: List<PostEntity> = mock()

        val result = Resource.success(postList)
        val postsRepository: PostsRepository = mock()
        whenever(postsRepository.getPostsFromNetwork()).thenReturn(result)

        val getPostsNetworkUseCase = GetPostsNetworkUseCase(postsRepository)
        val actual = getPostsNetworkUseCase()

        assertEquals(result, actual)
    }
}