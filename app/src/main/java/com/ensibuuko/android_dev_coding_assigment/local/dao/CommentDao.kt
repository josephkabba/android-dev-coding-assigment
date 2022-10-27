package com.ensibuuko.android_dev_coding_assigment.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.ensibuuko.android_dev_coding_assigment.local.models.comment.CommentModel

@Dao
interface CommentDao {
    @Insert
    suspend fun insertComment(comment: CommentModel)

    @Update
    suspend fun updateComment(comment: CommentModel)

    @Insert
    suspend fun insertComments(comment: List<CommentModel>)

    @Delete
    suspend fun deleteComment(comment: CommentModel)

    @Query("SELECT * FROM comments_table WHERE postId =: postId")
    suspend fun getComments(postId: Int): PagingSource<Int, CommentModel>

    @Query("DELETE FROM comments_table")
    suspend fun clear()
}