package com.hmyh.hmyhuserinformation.data.vos

import com.google.gson.annotations.SerializedName

data class UserListVO(

    @SerializedName("id")
    var id: Int?=null,

    @SerializedName("name")
    var name: String?=null,

    @SerializedName("username")
    var useName: String?=null,

    @SerializedName("email")
    var email: String?=null,

    @SerializedName("address")
    var address: AddressVO?=null,

    @SerializedName("phone")
    var phone: String?=null,

    @SerializedName("website")
    var website: String?=null,

    @SerializedName("company")
    var company: CompanyVO?=null

)