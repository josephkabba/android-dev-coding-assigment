package com.ensibuuko.android_dev_coding_assigment.local.mappaers

import com.ensibuuko.android_dev_coding_assigment.data.models.CommentDataModel
import com.ensibuuko.android_dev_coding_assigment.domain.entities.CommentEntity
import com.ensibuuko.android_dev_coding_assigment.local.BaseLocalMapper

class CommentsLocalMapper : BaseLocalMapper<CommentDataModel, CommentEntity> {
    override fun toData(l: CommentEntity): CommentDataModel = CommentDataModel(
        l.body,
        l.email,
        l.id,
        l.name,
        l.postId,
        l.createdAt
    )

    override fun toLocal(d: CommentDataModel): CommentEntity = CommentEntity(
        d.body,
        d.email,
        d.id,
        d.name,
        d.postId,
        d.createdAt
    )
}