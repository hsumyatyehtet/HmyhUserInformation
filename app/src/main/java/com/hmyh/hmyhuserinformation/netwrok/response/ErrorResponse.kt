

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

    @SerializedName("code")
    val code: Int? = 0,
    @SerializedName("message")
    val message: String
) {

}