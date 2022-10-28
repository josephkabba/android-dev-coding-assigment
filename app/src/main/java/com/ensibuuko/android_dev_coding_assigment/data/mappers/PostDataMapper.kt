package com.ensibuuko.android_dev_coding_assigment.data.mappers

import com.ensibuuko.android_dev_coding_assigment.data.BaseDataMapper
import com.ensibuuko.android_dev_coding_assigment.data.models.PostDataModel
import com.ensibuuko.android_dev_coding_assigment.domain.entities.PostEntity

class PostDataMapper : BaseDataMapper<PostEntity ,PostDataModel> {

    override fun toDomain(f: PostDataModel): PostEntity {
        return PostEntity(
            f.body,
            f.id,
            f.title,
            f.userId,
            f.createdAt
        )
    }

    override fun toData(t: PostEntity): PostDataModel {
        return PostDataModel(
            t.body,
            t.id,
            t.title,
            t.userId,
            t.createdAt
        )
    }
}