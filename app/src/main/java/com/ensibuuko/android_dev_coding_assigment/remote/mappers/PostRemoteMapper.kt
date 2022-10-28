package com.ensibuuko.android_dev_coding_assigment.remote.mappers

import com.ensibuuko.android_dev_coding_assigment.data.models.PostDataModel
import com.ensibuuko.android_dev_coding_assigment.remote.BaseRemoteMapper
import com.ensibuuko.android_dev_coding_assigment.remote.post.Post

class PostRemoteMapper : BaseRemoteMapper<PostDataModel, Post> {
    override fun toData(r: Post): PostDataModel = PostDataModel(
        r.body,
        r.id,
        r.title,
        r.userId,
    )

    override fun toRemote(d: PostDataModel): Post = Post(
        d.body,
        d.id,
        d.title,
        d.userId,
    )
}