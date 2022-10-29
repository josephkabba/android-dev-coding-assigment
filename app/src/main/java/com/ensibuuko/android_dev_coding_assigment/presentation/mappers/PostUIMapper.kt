package com.ensibuuko.android_dev_coding_assigment.presentation.mappers

import com.ensibuuko.android_dev_coding_assigment.domain.entities.PostEntity
import com.ensibuuko.android_dev_coding_assigment.presentation.models.PostUIModel
import javax.inject.Inject

class PostUIMapper @Inject constructor() : BaseUIMapper<PostEntity , PostUIModel> {

    override fun toDomain(u: PostUIModel): PostEntity {
        return PostEntity(
            u.body,
            u.id,
            u.title,
            u.userId,
            u.createdAt
        )
    }

    override fun toUI(d: PostEntity): PostUIModel {
        return PostUIModel(
            d.body,
            d.id,
            d.title,
            d.userId,
            d.createdAt
        )
    }
}