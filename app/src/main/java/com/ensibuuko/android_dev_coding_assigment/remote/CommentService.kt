package com.ensibuuko.android_dev_coding_assigment.remote

import com.ensibuuko.android_dev_coding_assigment.remote.comment.Comment
import com.ensibuuko.android_dev_coding_assigment.remote.comment.GetPostCommentsResponse
import com.ensibuuko.android_dev_coding_assigment.remote.post.Post
import retrofit2.http.*

interface CommentService {

    @POST("/posts/{postId}/comments")
    suspend fun createComment(@Path("postId") postId: Int, post: Post)

    @GET("/posts/{postId}/comments")
    suspend fun getPostComments(@Path("postId") postId: Int): Result<GetPostCommentsResponse>

    @DELETE("/posts/{postId}/comments/{commentId}")
    suspend fun deletePost(@Path("postId") postId: Int, @Path("commentId") commentId: Int)

    @PUT("/posts/{postId}/comments/{commentId}")
    suspend fun updatePost(@Path("postId") postId: Int, @Path("commentId") commentId: Int, comment: Comment)
}