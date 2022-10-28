package com.ensibuuko.android_dev_coding_assigment.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.ensibuuko.android_dev_coding_assigment.local.models.comment.CommentLocalModel

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: CommentLocalModel)

    @Update
    suspend fun updateComment(comment: CommentLocalModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(comment: List<CommentLocalModel>)

    @Delete
    suspend fun deleteComment(comment: CommentLocalModel)

    @Query("SELECT * FROM comments_table WHERE postId =: postId")
    suspend fun getComments(postId: Int): PagingSource<Int, CommentLocalModel>

    @Query("DELETE FROM comments_table")
    suspend fun clear()
}