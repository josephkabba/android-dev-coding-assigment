package com.ensibuuko.android_dev_coding_assigment.local.mappaers

import com.ensibuuko.android_dev_coding_assigment.data.models.CommentDataModel
import com.ensibuuko.android_dev_coding_assigment.domain.entities.CommentEntity
import com.ensibuuko.android_dev_coding_assigment.local.BaseLocalMapper
import com.ensibuuko.android_dev_coding_assigment.local.models.comment.CommentLocalModel

class CommentsLocalMapper : BaseLocalMapper<CommentDataModel, CommentLocalModel> {
    override fun toData(l: CommentLocalModel): CommentDataModel = CommentDataModel(
        l.body,
        l.email,
        l.id,
        l.name,
        l.postId,
        l.createdAt
    )

    override fun toLocal(d: CommentDataModel): CommentLocalModel = CommentLocalModel(
        d.body,
        d.email,
        d.id,
        d.name,
        d.postId,
        d.createdAt
    )
}