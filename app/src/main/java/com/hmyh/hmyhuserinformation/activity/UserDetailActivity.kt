package com.hmyh.hmyhuserinformation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.hmyh.hmyhuserinformation.databinding.ActivityUserDetailBinding

class UserDetailActivity: BaseActivity() {

    companion object{
        const val EXTRA_USER_ID="extra user id"

        fun newIntent(context: Context,userId: Int): Intent{
            return Intent(context,UserDetailActivity::class.java).apply {
                putExtra(EXTRA_USER_ID,userId)
            }
        }
    }

    private lateinit var binding: ActivityUserDetailBinding

    private var userId: Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDataFromIntent()
        setUpData()
        setUpListener()
    }

    private fun setUpListener() {
        binding.ivBackDetail.setOnClickListener {
            finish()
        }
    }

    private fun setUpData() {

    }

    private fun getDataFromIntent() {
        intent.getIntExtra(EXTRA_USER_ID,0).let {
            userId = it
        }
    }

}