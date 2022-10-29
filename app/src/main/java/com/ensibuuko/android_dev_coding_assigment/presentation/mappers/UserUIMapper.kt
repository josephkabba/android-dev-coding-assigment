package com.ensibuuko.android_dev_coding_assigment.presentation.mappers


import com.ensibuuko.android_dev_coding_assigment.domain.entities.UserEntity
import com.ensibuuko.android_dev_coding_assigment.domain.entities.user.AddressEntity
import com.ensibuuko.android_dev_coding_assigment.domain.entities.user.CompanyEntity
import com.ensibuuko.android_dev_coding_assigment.domain.entities.user.GeoEntity
import com.ensibuuko.android_dev_coding_assigment.presentation.models.UserUIModel
import com.ensibuuko.android_dev_coding_assigment.presentation.models.user.AddressUIModel
import com.ensibuuko.android_dev_coding_assigment.presentation.models.user.CompanyUIModel
import com.ensibuuko.android_dev_coding_assigment.presentation.models.user.GeoUIModel
import javax.inject.Inject

class UserUIMapper @Inject constructor() : BaseUIMapper<UserEntity, UserUIModel> {

    override fun toDomain(u: UserUIModel): UserEntity {
        return UserEntity(
            address = addressToDomain(u.address),
            company = companyToDomain(u.company),
            email = u.email,
            id = u.id,
            name = u.name,
            phone = u.phone,
            username = u.username,
            website = u.website,
            createdAt = u.createdAt
        )
    }

    override fun toUI(d: UserEntity): UserUIModel {
        return UserUIModel(
            address = addressToUI(d.address),
            company = companyToUI(d.company),
            email = d.email,
            id = d.id,
            name = d.name,
            phone = d.phone,
            username = d.username,
            website = d.website,
            createdAt = d.createdAt
        )
    }

    private fun companyToUI(c: CompanyEntity): CompanyUIModel {
        return CompanyUIModel(
            c.bs,
            c.catchPhrase,
            c.name
        )
    }

    private fun companyToDomain(c: CompanyUIModel): CompanyEntity {
        return CompanyEntity(
            c.bs,
            c.catchPhrase,
            c.name
        )
    }

    private fun geoToUI(g: GeoEntity): GeoUIModel {
        return GeoUIModel(
            g.lat,
            g.lng
        )
    }

    private fun geoToDomain(g: GeoUIModel): GeoEntity {
        return GeoEntity(
            g.lat,
            g.lng
        )
    }

    private fun addressToUI(a: AddressEntity): AddressUIModel {
        return AddressUIModel(
            city = a.city,
            geo = geoToUI(a.geo),
            street = a.street,
            suite = a.suite,
            zipcode = a.zipcode
        )
    }

    private fun addressToDomain(a: AddressUIModel): AddressEntity {
        return AddressEntity(
            city = a.city,
            geo = geoToDomain(a.geo),
            street = a.street,
            suite = a.suite,
            zipcode = a.zipcode
        )
    }


}