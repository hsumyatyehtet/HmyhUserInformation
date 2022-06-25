package com.hmyh.hmyhuserinformation.data.model.impl

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hmyh.hmyhuserinformation.data.model.BaseAppModel
import com.hmyh.hmyhuserinformation.data.model.UserInformationModel
import com.hmyh.hmyhuserinformation.data.vos.UserListVO
import com.hmyh.hmyhuserinformation.utils.subscribeDBWithCompletable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object UserInformationModelImpl : BaseAppModel(), UserInformationModel {

    @SuppressLint("CheckResult")
    override fun loadUserList(
        onSuccess: (userListVO: List<UserListVO>) -> Unit,
        onFailure: (String) -> Unit
    ): LiveData<List<UserListVO>> {

        var liveData = MutableLiveData<List<UserListVO>>()

        mApi.loadUserList().subscribeOn(
            Schedulers.io()
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { userList ->
                    GlobalScope.launch {
                        liveData.postValue(userList)
                    }
                    onSuccess(userList)
                    mDatabase.userListDao().insertUserList(userList).subscribeDBWithCompletable()
                }
            }, {
                onFailure(it.toString())
            })
        return liveData
    }

    override fun getUserList(): LiveData<List<UserListVO>> {
        return mDatabase.userListDao().getUserList()
    }

    override fun getUserByUserId(userId: Int): LiveData<UserListVO> {
        return mDatabase.userListDao().getUserByUserId(userId)
    }

    override fun loadSearch(searchWord: String): LiveData<List<UserListVO>> {
        return mDatabase.userListDao().retrieveUserListBySearch("%$searchWord%")
    }

}