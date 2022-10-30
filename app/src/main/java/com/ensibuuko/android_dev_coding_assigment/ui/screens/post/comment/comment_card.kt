package com.ensibuuko.android_dev_coding_assigment.ui.screens.post.comment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.ensibuuko.android_dev_coding_assigment.presentation.models.CommentUIModel
import com.ensibuuko.android_dev_coding_assigment.presentation.view_models.PostViewModel
import com.ensibuuko.android_dev_coding_assigment.ui.theme.customGreen
import kotlinx.coroutines.launch
import java.util.*


@Composable
fun CommentCard(
    commentUIModel: CommentUIModel,
    userId: Int,
    viewModel: PostViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    var showDialog by remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.padding(horizontal = 10.dp)) {

        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                shape = RoundedCornerShape(10.dp),
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        scope.launch {
                            viewModel.deleteComment(commentUIModel)
                        }
                    }) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDialog = false
                    }) {
                        Text(text = "Dismiss")
                    }
                },
                title = {
                    Text(text = "Delete Post")
                },
                text = {
                    Text("Do you want to delete the comment")
                },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true
                )
            )
        }

        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(10.dp),
            backgroundColor = MaterialTheme.colors.background,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .width(306.dp)
                    .background(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colors.background
                    )
                    .padding(10.dp)
            ) {
                Text(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    color = customGreen,
                    text = commentUIModel.name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    })

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle.Normal,
                    text = commentUIModel.body.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    })

                Spacer(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(
                        text = commentUIModel.email,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Light
                    )



                    IconButton(onClick = { showDialog = !showDialog }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                    }

                }
            }

        }
    }
}
