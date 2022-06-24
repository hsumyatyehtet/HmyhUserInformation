package com.hmyh.hmyhuserinformation

import android.app.Application
import android.content.Context
import com.hmyh.hmyhuserinformation.data.model.impl.UserInformationModelImpl

/**
 * Created by Hsu Myat Ye Htet on 6/24/2022
 */
class UserInformationApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        application = applicationContext

        UserInformationModelImpl.init(applicationContext)
    }

    companion object{
        lateinit var application: Context
    }

}