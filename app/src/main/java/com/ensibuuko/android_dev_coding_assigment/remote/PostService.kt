package com.ensibuuko.android_dev_coding_assigment.remote

import com.ensibuuko.android_dev_coding_assigment.remote.post.GetPostsResponse
import com.ensibuuko.android_dev_coding_assigment.remote.post.Post
import retrofit2.Response
import retrofit2.http.*

interface PostService {

    @POST("/posts")
    suspend fun createPost(@Body post: Post)

    @GET("/posts")
    suspend fun getPosts(): Result<GetPostsResponse>

    @DELETE("/posts/")
    suspend fun deletePost(@Path("postId") postId: Int)

    @DELETE("/posts/")
    suspend fun updatePost(@Path("postId") postId: Int, @Body post: Post)
}