package com.ensibuuko.android_dev_coding_assigment.remote

import com.ensibuuko.android_dev_coding_assigment.remote.comment.Comment
import com.ensibuuko.android_dev_coding_assigment.remote.comment.GetPostCommentsResponse
import com.ensibuuko.android_dev_coding_assigment.remote.post.Post
import retrofit2.http.*

interface CommentService {

    @POST("/posts/{postId}/comments")
    suspend fun createComment(@Path("postId") postId: Int, @Body comment: Comment)

    @GET("/posts/{postId}/comments")
    suspend fun getPostComments(@Path("postId") postId: Int): Result<GetPostCommentsResponse>

    @DELETE("/posts/{postId}/comments/{commentId}")
    suspend fun deleteComment(@Path("postId") postId: Int, @Path("commentId") commentId: Int)

    @PUT("/posts/{postId}/comments/{commentId}")
    suspend fun updatePost(@Path("postId") postId: Int, @Path("commentId") commentId: Int, @Body comment: Comment)
}