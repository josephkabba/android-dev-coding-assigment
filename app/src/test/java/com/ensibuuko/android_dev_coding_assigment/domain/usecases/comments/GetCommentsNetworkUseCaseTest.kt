package com.ensibuuko.android_dev_coding_assigment.domain.usecases.comments

import com.ensibuuko.android_dev_coding_assigment.domain.CommentsRepository
import com.ensibuuko.android_dev_coding_assigment.domain.entities.CommentEntity
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetCommentsNetworkUseCaseTest {

    @Test
    fun test_getCommentsNetworkUseCase() = runBlocking {

        //arrange

        val commentList: List<CommentEntity> = mock()

        val postId = 0

        val expected = Resource.success(commentList)
        val commentsRepository: CommentsRepository = mock()
        whenever(commentsRepository.getCommentsFromNetwork(postId)).thenReturn(expected)

        val getCommentsNetworkUseCase = GetCommentsNetworkUseCase(commentsRepository)

        //act
        val actual = getCommentsNetworkUseCase(postId)

        //assert
        assertEquals(expected, actual)
    }
}