package com.ensibuuko.android_dev_coding_assigment.data

import com.ensibuuko.android_dev_coding_assigment.data.mappers.UserDataMapper
import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.domain.UserRepository
import com.ensibuuko.android_dev_coding_assigment.domain.entities.UserEntity
import com.ensibuuko.android_dev_coding_assigment.local.dao.UserDao
import com.ensibuuko.android_dev_coding_assigment.local.models.user.*
import com.ensibuuko.android_dev_coding_assigment.remote.UserService
import com.ensibuuko.android_dev_coding_assigment.remote.mappers.UserRemoteMapper
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class
UserRepositoryImpl @Inject constructor(
    private val userRemoteMapper: UserRemoteMapper,
    private val userDataMapper: UserDataMapper,
    private val userDao: UserDao,
    private val userService: UserService
) : UserRepository {
    override suspend fun getUser(id: Int, cachePolicy: CachePolicy): Flow<Resource<UserEntity>> {


        return flow {
            when (cachePolicy.type) {
                CachePolicy.Type.NEVER -> {
                    userService.getUser(id).onSuccess {
                        val result = userDataMapper.toDomain(userRemoteMapper.toData(it))
                        emit(Resource.success(result))
                    }.onFailure {
                        Resource.error<UserEntity>(it.message ?: "Network Error")
                    }
                }
                CachePolicy.Type.REFRESH -> {
                    userService.getUser(id).onSuccess {
                        val result = userDataMapper.toDomain(userRemoteMapper.toData(it))

                        val localUser = UserLocalModel(
                            it.email,
                            it.id,
                            it.name,
                            it.phone,
                            it.username,
                            it.website
                        )

                        val address = it.address
                        val geo = it.address.geo
                        val company = it.company

                        userDao.insertUser(localUser)

                        userDao.insertAddress(
                            AddressLocalModel(
                                city = address.city,
                                street = address.street,
                                suite = address.suite,
                                zipcode = address.zipcode,
                                userId = localUser.id
                            )
                        )

                        userDao.insertCompany(
                            CompanyLocalModel(
                                bs = company.bs,
                                name = company.name,
                                catchPhrase = company.catchPhrase,
                                userId = localUser.id
                            )
                        )

                        userDao.insertGeo(
                            GeoLocalModel(
                                lat = geo.lat,
                                lng = geo.lng,
                                userId = localUser.id
                            )
                        )

                        emit(Resource.success(result))
                    }.onFailure {
                        val user = userDao.getUser(id)
                        if(user != null) {
                            val result = userDataMapper.toDomain(
                                user.user.toDataModel(
                                    user.address.toDataModel(user.geo.toDataModel()),
                                    user.company.toDataModel()
                                )
                            )
                            emit(Resource.success(result))
                        }else {
                            emit(Resource.error<UserEntity>(msg = "Network error"))
                        }
                    }
                }
                else -> {
                    val user = userDao.getUser(id)

                    if(user != null) {
                        val result = userDataMapper.toDomain(
                            user.user.toDataModel(
                                user.address.toDataModel(user.geo.toDataModel()),
                                user.company.toDataModel()
                            )
                        )
                        emit(Resource.success(result))
                    }else {
                        emit(Resource.error<UserEntity>(msg = "Network error"))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}