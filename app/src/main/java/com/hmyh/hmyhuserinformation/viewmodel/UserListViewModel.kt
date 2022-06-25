package com.hmyh.hmyhuserinformation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hmyh.hmyhuserinformation.adapter.UserListAdapter
import com.hmyh.hmyhuserinformation.data.model.UserInformationModel
import com.hmyh.hmyhuserinformation.data.model.impl.UserInformationModelImpl
import com.hmyh.hmyhuserinformation.data.vos.UserListVO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserListViewModel : ViewModel(),UserListAdapter.Delegate {

    private val mModel: UserInformationModel = UserInformationModelImpl

    private var mUserList: LiveData<List<UserListVO>> = mModel.getUserList()

    private var mErrorMessage: MutableLiveData<String> = MutableLiveData<String>()

    private var navigateToDetail: MutableLiveData<Int> = MutableLiveData()

    fun onUiReady() {
        mModel.loadUserList(
            onSuccess = {},
            onFailure = {
                GlobalScope.launch {
                    mErrorMessage.postValue(it)
                }
            })
    }

    fun loadSearch(searchWord: String): LiveData<List<UserListVO>>{
        return mModel.loadSearch(searchWord)
    }

    fun getUserList(): LiveData<List<UserListVO>>{
        return mUserList
    }

    override fun onTapUserItem(userId: Int) {
        GlobalScope.launch {
            navigateToDetail.postValue(userId)
        }
    }

    fun getNavigateToUserDetailLiveData(): LiveData<Int>{
        return navigateToDetail
    }

}