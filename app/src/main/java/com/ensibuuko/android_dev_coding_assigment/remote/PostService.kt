package com.ensibuuko.android_dev_coding_assigment.remote

import com.ensibuuko.android_dev_coding_assigment.remote.post.GetPostsResponse
import com.ensibuuko.android_dev_coding_assigment.remote.post.Post
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostService {

    @POST("/posts")
    suspend fun createPost(post: Post)

    @GET("/posts")
    suspend fun getPosts(): Result<GetPostsResponse>

    @DELETE("/posts/")
    suspend fun deletePost(@Path("postId") postId: Int)

    @DELETE("/posts/")
    suspend fun updatePost(@Path("postId") postId: Int, post: Post)
}