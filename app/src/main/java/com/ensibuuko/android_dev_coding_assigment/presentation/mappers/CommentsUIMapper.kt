package com.ensibuuko.android_dev_coding_assigment.presentation.mappers

import com.ensibuuko.android_dev_coding_assigment.domain.entities.CommentEntity
import com.ensibuuko.android_dev_coding_assigment.presentation.models.CommentUIModel
import javax.inject.Inject

class CommentsUIMapper @Inject constructor() : BaseUIMapper<CommentEntity, CommentUIModel> {

    override fun toUI(d: CommentEntity): CommentUIModel {
        return CommentUIModel(
            d.body,
            d.email,
            d.id,
            d.name,
            d.id,
            d.createdAt
        )
    }

    override fun toDomain(u: CommentUIModel): CommentEntity {
        return CommentEntity(
            u.body,
            u.email,
            u.id,
            u.name,
            u.id,
            u.createdAt
        )
    }
}