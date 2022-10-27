package com.ensibuuko.android_dev_coding_assigment.local.mappaers

import com.ensibuuko.android_dev_coding_assigment.data.models.PostDataModel
import com.ensibuuko.android_dev_coding_assigment.domain.entities.PostEntity
import com.ensibuuko.android_dev_coding_assigment.local.BaseLocalMapper

class PostLocalMapper : BaseLocalMapper<PostDataModel, PostEntity> {
    override fun toData(l: PostEntity): PostDataModel = PostDataModel(
        l.body,
        l.id,
        l.title,
        l.userId,
        l.createdAt
    )

    override fun toLocal(d: PostDataModel): PostEntity = PostEntity(
        d.body,
        d.id,
        d.title,
        d.userId,
        d.createdAt
    )
}