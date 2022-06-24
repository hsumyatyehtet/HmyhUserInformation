package com.hmyh.hmyhuserinformation.data.vos

import com.google.gson.annotations.SerializedName

data class UserListVO(

    @SerializedName("1")
    var id: Int?=null,

    @SerializedName("name")
    var name: String?=null,

    @SerializedName("username")
    var useName: String?=null,

    @SerializedName("email")
    var email: String?=null

)