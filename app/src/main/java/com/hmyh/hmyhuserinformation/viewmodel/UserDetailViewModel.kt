package com.hmyh.hmyhuserinformation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hmyh.hmyhuserinformation.data.model.UserInformationModel
import com.hmyh.hmyhuserinformation.data.model.impl.UserInformationModelImpl
import com.hmyh.hmyhuserinformation.data.vos.UserListVO

class UserDetailViewModel: ViewModel() {

    private val mModel: UserInformationModel = UserInformationModelImpl

    fun getUserByUserId(userId: Int): LiveData<UserListVO>{
        return mModel.getUserByUserId(userId)
    }

}