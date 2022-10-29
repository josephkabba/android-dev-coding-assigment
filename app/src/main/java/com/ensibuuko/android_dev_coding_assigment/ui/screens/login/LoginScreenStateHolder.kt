package com.ensibuuko.android_dev_coding_assigment.ui.screens.login

import android.content.res.Resources
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

class LoginScreenStateHolder(val resources: Resources, val navController: NavController) {
    var id by mutableStateOf("")

    var error by mutableStateOf("")
    var showError by mutableStateOf(false)
    var idError by mutableStateOf(false)

    fun signInUser(callBack: (id: String) -> Unit) {

        if (idError or showError) {
            idError = false
            showError = false
        }

        if (id.isBlank() or id.isEmpty()) {
            idError = true
            error = "Enter email"
        } else {
            callBack(id)
        }
    }
}