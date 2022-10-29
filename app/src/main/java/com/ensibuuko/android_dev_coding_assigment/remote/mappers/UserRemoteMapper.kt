package com.ensibuuko.android_dev_coding_assigment.remote.mappers

import com.ensibuuko.android_dev_coding_assigment.data.models.UserDataModel
import com.ensibuuko.android_dev_coding_assigment.data.models.user.AddressDataModel
import com.ensibuuko.android_dev_coding_assigment.data.models.user.CompanyDataModel
import com.ensibuuko.android_dev_coding_assigment.data.models.user.GeoDataModel
import com.ensibuuko.android_dev_coding_assigment.remote.BaseRemoteMapper
import com.ensibuuko.android_dev_coding_assigment.remote.user.Address
import com.ensibuuko.android_dev_coding_assigment.remote.user.Company
import com.ensibuuko.android_dev_coding_assigment.remote.user.Geo
import com.ensibuuko.android_dev_coding_assigment.remote.user.User
import javax.inject.Inject

class UserRemoteMapper @Inject constructor() : BaseRemoteMapper<UserDataModel, User> {
    override fun toRemote(d: UserDataModel): User {
        return User(
            address = addressToRemote(d.address),
            company = companyToRemote(d.company),
            email = d.email,
            id = d.id,
            name = d.name,
            phone = d.phone,
            username = d.username,
            website = d.website,
        )
    }

    override fun toData(r: User): UserDataModel {
        return UserDataModel(
            address = addressToData(r.address),
            company = companyToData(r.company),
            email = r.email,
            id = r.id,
            name = r.name,
            phone = r.phone,
            username = r.username,
            website = r.website,
        )
    }

    private fun companyToData(c: Company): CompanyDataModel {
        return CompanyDataModel(
            c.bs,
            c.catchPhrase,
            c.name
        )
    }

    private fun companyToRemote(c: CompanyDataModel): Company {
        return Company(
            c.bs,
            c.catchPhrase,
            c.name
        )
    }

    private fun geoToData(g: Geo): GeoDataModel {
        return GeoDataModel(
            g.lat,
            g.lng
        )
    }

    private fun geoToRemote(g: GeoDataModel): Geo {
        return Geo(
            g.lat,
            g.lng
        )
    }

    private fun addressToData(a: Address): AddressDataModel {
        return AddressDataModel(
            city = a.city,
            geo = geoToData(a.geo),
            street = a.street,
            suite = a.suite,
            zipcode = a.zipcode
        )
    }

    private fun addressToRemote(a: AddressDataModel): Address {
        return Address(
            city = a.city,
            geo = geoToRemote(a.geo),
            street = a.street,
            suite = a.suite,
            zipcode = a.zipcode
        )
    }
}