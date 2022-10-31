package com.ensibuuko.android_dev_coding_assigment.domain.usecases.user

import com.ensibuuko.android_dev_coding_assigment.domain.UserRepository
import com.ensibuuko.android_dev_coding_assigment.domain.entities.UserEntity
import com.ensibuuko.android_dev_coding_assigment.domain.entities.user.AddressEntity
import com.ensibuuko.android_dev_coding_assigment.domain.entities.user.CompanyEntity
import com.ensibuuko.android_dev_coding_assigment.domain.entities.user.GeoEntity
import com.ensibuuko.android_dev_coding_assigment.utils.CachePolicy
import com.ensibuuko.android_dev_coding_assigment.utils.Resource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.math.exp

class GetUserUseCaseTest {

    @Test
    fun test_getUserUseCase() = runBlocking {
        val expected: Flow<Resource<UserEntity>> = flow {
            emit(
                Resource.success(
                    UserEntity(
                        address = AddressEntity(
                            city = "kampala",
                            geo = GeoEntity("", ""),
                            street = "bukoto",
                            suite = "bukoto heights",
                            zipcode = ""
                        ),
                        company = CompanyEntity("", "", ""),
                        id = 0,
                        name = "test",
                        phone = "+2560989050",
                        username = "test",
                        website = "test.com",
                        createdAt = 0,
                        persist = false,
                        email = "test@test.com"
                    )
                )
            )
        }

        val userRepository: UserRepository = mock()
        whenever(userRepository.getUser(0, CachePolicy.Type.NEVER, false)).thenReturn(expected)

        val getUserUseCase = GetUserUseCase(userRepository)
        val actual = getUserUseCase(0, cachePolicy = CachePolicy.Type.NEVER)

        assertNotNull(actual.first())
        assertEquals(expected.first(), actual.first())
    }
}