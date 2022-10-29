package com.ensibuuko.android_dev_coding_assigment.di

import com.ensibuuko.android_dev_coding_assigment.utils.ResultCallAdapterFactory
import com.ensibuuko.android_dev_coding_assigment.remote.CommentService
import com.ensibuuko.android_dev_coding_assigment.remote.PostService
import com.ensibuuko.android_dev_coding_assigment.remote.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        val baseUrl = "https://jsonplaceholder.typicode.com"
        return Retrofit.Builder()
            .client(OkHttpClient.Builder().build())
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl).build()
    }

    @Provides
    fun providePostService(
        retrofit: Retrofit
    ): PostService {
        return retrofit.create(PostService::class.java)
    }

    @Provides
    fun provideUserService(
        retrofit: Retrofit
    ): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    fun provideCommentService(
        retrofit: Retrofit
    ): CommentService {
        return retrofit.create(CommentService::class.java)
    }
}