package com.ensibuuko.android_dev_coding_assigment.di

import android.content.Context
import androidx.room.Room
import com.ensibuuko.android_dev_coding_assigment.local.dao.CommentDao
import com.ensibuuko.android_dev_coding_assigment.local.dao.PostDao
import com.ensibuuko.android_dev_coding_assigment.local.dao.UserDao
import com.ensibuuko.android_dev_coding_assigment.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideRoomInstance(@ApplicationContext applicationContext: Context): AppDatabase {
        val databaseName = "app_demo_database"
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, databaseName
        ).build()
    }

    @Provides
    fun provideCommentDao(appDatabase: AppDatabase): CommentDao {
        return appDatabase.commentDao()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun providePostDao(appDatabase: AppDatabase): PostDao {
        return appDatabase.postDao()
    }
}