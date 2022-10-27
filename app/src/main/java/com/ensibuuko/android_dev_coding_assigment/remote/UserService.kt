package com.ensibuuko.android_dev_coding_assigment.remote

import com.ensibuuko.android_dev_coding_assigment.remote.user.User
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("/users/{id}")
    suspend fun getUser(@Path("id") id: Int): Result<User>

}