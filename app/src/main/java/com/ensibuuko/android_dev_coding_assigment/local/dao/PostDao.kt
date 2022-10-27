package com.ensibuuko.android_dev_coding_assigment.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.ensibuuko.android_dev_coding_assigment.local.models.post.PostModel

@Dao
interface PostDao {

    @Insert
    suspend fun insertPost(post: PostModel)

    @Update
    suspend fun updatePost(post: PostModel)

    @Insert
    suspend fun insertPosts(post: List<PostModel>)

    @Delete
    suspend fun deletePost(post: PostModel)

    @Query("SELECT * FROM posts_table WHERE id =: id")
    suspend fun getPosts(id: Int): PagingSource<Int, List<PostModel>>

    @Query("DELETE FROM posts_table")
    suspend fun clear()
}