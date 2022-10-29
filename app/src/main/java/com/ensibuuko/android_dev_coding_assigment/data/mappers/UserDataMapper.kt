package com.ensibuuko.android_dev_coding_assigment.data.mappers

import com.ensibuuko.android_dev_coding_assigment.data.BaseDataMapper
import com.ensibuuko.android_dev_coding_assigment.data.models.UserDataModel
import com.ensibuuko.android_dev_coding_assigment.data.models.user.AddressDataModel
import com.ensibuuko.android_dev_coding_assigment.data.models.user.CompanyDataModel
import com.ensibuuko.android_dev_coding_assigment.data.models.user.GeoDataModel
import com.ensibuuko.android_dev_coding_assigment.domain.entities.UserEntity
import com.ensibuuko.android_dev_coding_assigment.domain.entities.user.AddressEntity
import com.ensibuuko.android_dev_coding_assigment.domain.entities.user.CompanyEntity
import com.ensibuuko.android_dev_coding_assigment.domain.entities.user.GeoEntity
import javax.inject.Inject

class UserDataMapper @Inject constructor() : BaseDataMapper<UserEntity, UserDataModel> {

    override fun toDomain(f: UserDataModel): UserEntity {
        return UserEntity(
            address = addressToDomain(f.address),
            company = companyToDomain(f.company),
            email = f.email,
            id = f.id,
            name = f.name,
            phone = f.phone,
            username = f.username,
            website = f.website,
            createdAt = f.createdAt
        )
    }

    override fun toData(t: UserEntity): UserDataModel {
        return UserDataModel(
            address = addressToData(t.address),
            company = companyToData(t.company),
            email = t.email,
            id = t.id,
            name = t.name,
            phone = t.phone,
            username = t.username,
            website = t.website,
            createdAt = t.createdAt
        )
    }

    private fun companyToData(c: CompanyEntity): CompanyDataModel {
        return CompanyDataModel(
            c.bs,
            c.catchPhrase,
            c.name
        )
    }

    private fun companyToDomain(c: CompanyDataModel): CompanyEntity {
        return CompanyEntity(
            c.bs,
            c.catchPhrase,
            c.name
        )
    }

    private fun geoToData(g: GeoEntity): GeoDataModel {
        return GeoDataModel(
            g.lat,
            g.lng
        )
    }

    private fun geoToDomain(g: GeoDataModel): GeoEntity {
        return GeoEntity(
            g.lat,
            g.lng
        )
    }

    private fun addressToData(a: AddressEntity): AddressDataModel {
        return AddressDataModel(
            city = a.city,
            geo = geoToData(a.geo),
            street = a.street,
            suite = a.suite,
            zipcode = a.zipcode
        )
    }

    private fun addressToDomain(a: AddressDataModel): AddressEntity {
        return AddressEntity(
            city = a.city,
            geo = geoToDomain(a.geo),
            street = a.street,
            suite = a.suite,
            zipcode = a.zipcode
        )
    }


}