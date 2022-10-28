package com.ensibuuko.android_dev_coding_assigment.local.mappaers

import com.ensibuuko.android_dev_coding_assigment.data.models.PostDataModel
import com.ensibuuko.android_dev_coding_assigment.domain.entities.PostEntity
import com.ensibuuko.android_dev_coding_assigment.local.BaseLocalMapper
import com.ensibuuko.android_dev_coding_assigment.local.models.post.PostLocalModel

class PostLocalMapper : BaseLocalMapper<PostDataModel, PostLocalModel> {
    override fun toData(l: PostLocalModel): PostDataModel = PostDataModel(
        l.body,
        l.id,
        l.title,
        l.userId,
        l.createdAt
    )

    override fun toLocal(d: PostDataModel): PostLocalModel = PostLocalModel(
        d.body,
        d.id,
        d.title,
        d.userId,
        d.createdAt
    )
}