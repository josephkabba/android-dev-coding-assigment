package com.ensibuuko.android_dev_coding_assigment.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ensibuuko.android_dev_coding_assigment.local.dao.CommentDao
import com.ensibuuko.android_dev_coding_assigment.local.dao.PostDao
import com.ensibuuko.android_dev_coding_assigment.local.dao.UserDao
import com.ensibuuko.android_dev_coding_assigment.local.models.comment.CommentLocalModel
import com.ensibuuko.android_dev_coding_assigment.local.models.post.PostLocalModel
import com.ensibuuko.android_dev_coding_assigment.local.models.user.AddressLocalModel
import com.ensibuuko.android_dev_coding_assigment.local.models.user.CompanyLocalModel
import com.ensibuuko.android_dev_coding_assigment.local.models.user.GeoLocalModel
import com.ensibuuko.android_dev_coding_assigment.local.models.user.UserLocalModel

@Database(
    entities = [PostLocalModel::class, CommentLocalModel::class, UserLocalModel::class, AddressLocalModel::class, CompanyLocalModel::class, GeoLocalModel::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract  fun commentDao(): CommentDao
    abstract  fun userDao(): UserDao
}