package com.ensibuuko.android_dev_coding_assigment.presentation.view_models

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.ViewModel
import com.ensibuuko.android_dev_coding_assigment.domain.entities.UserEntity
import com.ensibuuko.android_dev_coding_assigment.domain.usecases.user.GetUserUseCase
import com.ensibuuko.android_dev_coding_assigment.presentation.mappers.UserUIMapper
import com.ensibuuko.android_dev_coding_assigment.presentation.models.UserUIModel
import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val dataStore: DataStore<Preferences>,
    private val userUIMapper: UserUIMapper
) : ViewModel() {

    suspend fun getUser(id: Int, cachePolicy: CachePolicy.Type, saveUser: Boolean = false): Flow<Resource<UserUIModel>?> {
        val user = getUserUseCase(id, cachePolicy, saveUser).map { when(it.status){
            Resource.Status.SUCCESS -> {
                Resource.success(it.data?.let { it1 -> userUIMapper.toUI(it1) })
            }

            Resource.Status.ERROR -> {
                it.message?.let { it1 -> Resource.error(it1) }
            }

            else -> {
                Resource.loading()
            }
        } }
        return user
    }

    private val USER_ID = intPreferencesKey("example_counter")

    val currentUserId: Flow<Int?> = dataStore.data.map { preferences ->
        preferences[USER_ID]
    }

    suspend fun saveUserId(id: Int){
        dataStore.edit { settings ->
            settings[USER_ID] = id
        }
    }
}