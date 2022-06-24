package com.hmyh.hmyhuserinformation.data.vos

import com.google.gson.annotations.SerializedName

data class AddressVO(

    @SerializedName("street")
    var street: String?=null,

    @SerializedName("suite")
    var suite: String?=null,

    @SerializedName("city")
    var city: String?=null,

    @SerializedName("zipcode")
    var zipCode: String?=null

)