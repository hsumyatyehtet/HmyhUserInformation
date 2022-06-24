package com.hmyh.hmyhuserinformation.netwrok

import com.hmyh.hmyhuserinformation.data.vos.UserListVO
import com.hmyh.hmyhuserinformation.utils.GET_ALL_USER_LIST
import io.reactivex.Observable
import retrofit2.http.GET

interface UserInformationApi {

    @GET(GET_ALL_USER_LIST)
    fun loadUserList(): Observable<List<UserListVO>>

}