package com.hmyh.hmyhuserinformation.data.model

import androidx.lifecycle.LiveData
import com.hmyh.hmyhuserinformation.data.vos.UserListVO

interface UserInformationModel {

    fun loadUserList(
        onSuccess: (userListVO: List<UserListVO>)->Unit,
        onFailure: (String)->Unit
    ): LiveData<List<UserListVO>>

    fun getUserList(): LiveData<List<UserListVO>>

    fun getUserByUserId(userId: Int): LiveData<UserListVO>

    fun loadSearch(searchWord: String): LiveData<List<UserListVO>>

}