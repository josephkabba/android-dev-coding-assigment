package com.ensibuuko.android_dev_coding_assigment.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.ensibuuko.android_dev_coding_assigment.local.models.post.PostLocalModel

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: PostLocalModel)

    @Update
    suspend fun updatePost(post: PostLocalModel)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(post: List<PostLocalModel>)

    @Delete
    suspend fun deletePost(post: PostLocalModel)

    @Query("SELECT * FROM posts_table")
    fun getPosts(): PagingSource<Int, PostLocalModel>

    @Transaction
    @Query("DELETE FROM posts_table")
    suspend fun clear()
}