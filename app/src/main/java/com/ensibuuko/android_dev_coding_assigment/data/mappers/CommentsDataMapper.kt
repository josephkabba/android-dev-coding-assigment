package com.ensibuuko.android_dev_coding_assigment.data.mappers

import com.ensibuuko.android_dev_coding_assigment.data.BaseDataMapper
import com.ensibuuko.android_dev_coding_assigment.data.models.CommentDataModel
import com.ensibuuko.android_dev_coding_assigment.domain.entities.CommentEntity
import com.ensibuuko.android_dev_coding_assigment.remote.comment.Comment

class CommentsDataMapper : BaseDataMapper<CommentEntity, CommentDataModel> {

    override fun toData(t: CommentEntity): CommentDataModel {
        return CommentDataModel(
            t.body,
            t.email,
            t.id,
            t.name,
            t.id,
            t.createdAt
        )
    }

    override fun toDomain(f: CommentDataModel): CommentEntity {
        return CommentEntity(
            f.body,
            f.email,
            f.id,
            f.name,
            f.id,
            f.createdAt
        )
    }
}