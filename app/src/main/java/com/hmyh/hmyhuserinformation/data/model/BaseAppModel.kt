package com.hmyh.hmyhuserinformation.data.model

import android.content.Context
import com.google.gson.GsonBuilder
import com.hmyh.hmyhuserinformation.BuildConfig
import com.hmyh.hmyhuserinformation.netwrok.UserInformationApi
import com.hmyh.hmyhuserinformation.persistance.UserListDatabase
import com.hmyh.hmyhuserinformation.utils.ApiConstants
import com.hmyh.hmyhuserinformation.utils.BASE_URL
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Hsu Myat Ye Htet on 6/24/2022
 */
abstract class BaseAppModel: BaseModel() {

   protected lateinit var mApi: UserInformationApi
    protected lateinit var mDatabase: UserListDatabase

    override fun init(context: Context) {
        initNetwork(context)
        initDatabase(context)
    }

    private fun initDatabase(context: Context) {
        mDatabase = UserListDatabase.getDatabase(context)
    }

    private fun initNetwork(context: Context) {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .apply {
                if (BuildConfig.DEBUG) addInterceptor(ChuckInterceptor(context))
            }
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .addHeader(ApiConstants.HEADER_ACCEPT, ApiConstants.HEADER_VALUE)
                        .addHeader(ApiConstants.HEADER_CONTENT_TYPE, ApiConstants.HEADER_VALUE)
                        .build()
                )
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        mApi = retrofit.create(UserInformationApi::class.java)
    }

}