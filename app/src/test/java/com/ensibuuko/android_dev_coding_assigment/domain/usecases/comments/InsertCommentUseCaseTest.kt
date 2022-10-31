package com.ensibuuko.android_dev_coding_assigment.domain.usecases.comments

import com.ensibuuko.android_dev_coding_assigment.domain.CommentsRepository
import com.ensibuuko.android_dev_coding_assigment.domain.entities.CommentEntity
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.Test

class InsertCommentUseCaseTest {
    @Test
    fun test_inertCommentUseCase() = runBlocking {
        val comment: CommentEntity = mock()
        val commentsRepository: CommentsRepository = mock()
        val insertCommentsUseCase = InsertCommentUseCase(commentsRepository)

        val actual = insertCommentsUseCase(comment)

        verify(commentsRepository).insertComment(any())
    }
}