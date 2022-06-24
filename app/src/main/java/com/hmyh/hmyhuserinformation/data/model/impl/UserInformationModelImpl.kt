package com.hmyh.hmyhuserinformation.data.model.impl

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hmyh.hmyhuserinformation.data.model.BaseAppModel
import com.hmyh.hmyhuserinformation.data.model.UserInformationModel
import com.hmyh.hmyhuserinformation.data.vos.UserListVO
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
                it?.let { userVO ->
                    GlobalScope.launch {
                        liveData.postValue(userVO)
                    }
                    onSuccess(userVO)
                }
            }, {
                onFailure(it.toString())
            })
        return liveData
    }

}