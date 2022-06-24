package com.hmyh.hmyhuserinformation.data.vos

import com.google.gson.annotations.SerializedName

data class CompanyVO(

    @SerializedName("name")
    var name: String?=null,

    @SerializedName("catchPhrase")
    var catchPhrase: String?=null,

    @SerializedName("bs")
    var bs: String?=null
)