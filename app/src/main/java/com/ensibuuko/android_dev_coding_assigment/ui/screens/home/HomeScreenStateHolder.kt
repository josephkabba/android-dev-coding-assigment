package com.ensibuuko.android_dev_coding_assigment.ui.screens.home

import android.content.res.Resources
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class HomeScreenStateHolder (val resources: Resources) {
    var title by mutableStateOf("")
    var body by mutableStateOf("")
    var expand by mutableStateOf(false)

    var error by mutableStateOf("")
    var showError by mutableStateOf(false)
    var titleError by mutableStateOf(false)
    var bodyError by mutableStateOf(false)

    fun createPost(callBack: (title: String, body: String) -> Unit) {

        if (titleError or bodyError or showError) {
            titleError = false
            showError = false
            bodyError = false
        }

        if (title.isBlank() or title.isEmpty()) {
            titleError = true
            error = "Enter email"
        } else if (body.isBlank() or body.isEmpty()) {
            titleError = true
            error = "Enter email"
        } else {
            callBack(title, body)
        }
    }
}