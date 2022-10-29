package com.ensibuuko.android_dev_coding_assigment.di

import com.ensibuuko.android_dev_coding_assigment.data.CommentsRepositoryImpl
import com.ensibuuko.android_dev_coding_assigment.data.PostsRepositoryImpl
import com.ensibuuko.android_dev_coding_assigment.data.UserRepositoryImpl
import com.ensibuuko.android_dev_coding_assigment.data.mappers.CommentsDataMapper
import com.ensibuuko.android_dev_coding_assigment.data.mappers.PostDataMapper
import com.ensibuuko.android_dev_coding_assigment.data.mappers.UserDataMapper
import com.ensibuuko.android_dev_coding_assigment.domain.CommentsRepository
import com.ensibuuko.android_dev_coding_assigment.domain.PostsRepository
import com.ensibuuko.android_dev_coding_assigment.domain.UserRepository
import com.ensibuuko.android_dev_coding_assigment.local.dao.CommentDao
import com.ensibuuko.android_dev_coding_assigment.local.dao.PostDao
import com.ensibuuko.android_dev_coding_assigment.local.dao.UserDao
import com.ensibuuko.android_dev_coding_assigment.local.mappaers.CommentsLocalMapper
import com.ensibuuko.android_dev_coding_assigment.local.mappaers.PostLocalMapper
import com.ensibuuko.android_dev_coding_assigment.remote.CommentService
import com.ensibuuko.android_dev_coding_assigment.remote.PostService
import com.ensibuuko.android_dev_coding_assigment.remote.UserService
import com.ensibuuko.android_dev_coding_assigment.remote.mappers.CommentsRemoteMapper
import com.ensibuuko.android_dev_coding_assigment.remote.mappers.PostRemoteMapper
import com.ensibuuko.android_dev_coding_assigment.remote.mappers.UserRemoteMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun providePostsRepository(
        postLocalMapper: PostLocalMapper,
        postRemoteMapper: PostRemoteMapper,
        postDataMapper: PostDataMapper,
        postService: PostService,
        postDao: PostDao
    ): PostsRepository {
        return PostsRepositoryImpl(
            postLocalMapper,
            postRemoteMapper,
            postDataMapper,
            postService,
            postDao
        )
    }

    @Provides
    fun provideCommentRepository(
        commentsDataMapper: CommentsDataMapper,
        commentsRemoteMapper: CommentsRemoteMapper,
        commentsLocalMapper: CommentsLocalMapper,
        commentDao: CommentDao,
        commentService: CommentService
    ): CommentsRepository {
        return CommentsRepositoryImpl(
            commentsDataMapper,
            commentsRemoteMapper,
            commentsLocalMapper,
            commentDao,
            commentService
        )
    }

    @Provides
    fun provideUserRepository(
        userRemoteMapper: UserRemoteMapper,
        userDataMapper: UserDataMapper,
        userDao: UserDao,
        userService: UserService
    ): UserRepository {
        return UserRepositoryImpl(
            userRemoteMapper,
            userDataMapper,
            userDao,
            userService
        )
    }
}