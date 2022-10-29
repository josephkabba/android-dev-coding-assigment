package com.ensibuuko.android_dev_coding_assigment.remote.mappers

import com.ensibuuko.android_dev_coding_assigment.data.models.CommentDataModel
import com.ensibuuko.android_dev_coding_assigment.remote.BaseRemoteMapper
import com.ensibuuko.android_dev_coding_assigment.remote.comment.Comment
import javax.inject.Inject

class CommentsRemoteMapper @Inject constructor() : BaseRemoteMapper<CommentDataModel, Comment> {
    override fun toData(r: Comment): CommentDataModel = CommentDataModel(
        r.body,
        r.email,
        r.id,
        r.name,
        r.postId,
    )

    override fun toRemote(d: CommentDataModel): Comment = Comment(
        d.body,
        d.email,
        d.id,
        d.name,
        d.postId,
    )
}