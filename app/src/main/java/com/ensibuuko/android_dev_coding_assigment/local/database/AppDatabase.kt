package com.ensibuuko.android_dev_coding_assigment.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ensibuuko.android_dev_coding_assigment.local.dao.CommentDao
import com.ensibuuko.android_dev_coding_assigment.local.dao.PostDao
import com.ensibuuko.android_dev_coding_assigment.local.dao.UserDao
import com.ensibuuko.android_dev_coding_assigment.local.models.comment.CommentModel
import com.ensibuuko.android_dev_coding_assigment.local.models.post.PostModel
import com.ensibuuko.android_dev_coding_assigment.local.models.user.AddressModel
import com.ensibuuko.android_dev_coding_assigment.local.models.user.CompanyModel
import com.ensibuuko.android_dev_coding_assigment.local.models.user.GeoModel
import com.ensibuuko.android_dev_coding_assigment.local.models.user.UserModel

@Database(
    entities = [PostModel::class, CommentModel::class, UserModel::class, AddressModel::class, CompanyModel::class, GeoModel::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract  fun commentDao(): CommentDao
    abstract  fun userDao(): UserDao
}