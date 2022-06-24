

import com.google.gson.annotations.SerializedName
import com.hmyh.hmyhuserinformation.data.vos.MetaVO


/**
 * Created by Tin Tun Aung on 6/23/2020
 */
data class MoreDataResponse<T>(
    @SerializedName("code")
    val code: Int = 0,

    @SerializedName("message")
    val message: String = "",

    @SerializedName("data")
    val data: T? = null,

    @SerializedName("meta")
    val meta: MetaVO? = null
) {
    fun isResponseOk() = code in 200..299 && data!= null
}