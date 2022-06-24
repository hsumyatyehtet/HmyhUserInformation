package com.hmyh.hmyhuserinformation.data.vos

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user_list")
data class UserListVO(

    @PrimaryKey
    @SerializedName("id")
    var id: Int?=null,

    @SerializedName("name")
    var name: String?=null,

    @SerializedName("username")
    var useName: String?=null,

    @SerializedName("email")
    var email: String?=null,

    @Embedded(prefix = "address_")
    @SerializedName("address")
    var address: AddressVO?=null,

    @SerializedName("phone")
    var phone: String?=null,

    @SerializedName("website")
    var website: String?=null,

    @Embedded(prefix = "company_")
    @SerializedName("company")
    var company: CompanyVO?=null

)