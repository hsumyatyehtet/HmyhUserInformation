package com.hmyh.hmyhuserinformation.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hmyh.hmyhuserinformation.data.vos.UserListVO
import com.hmyh.hmyhuserinformation.databinding.ActivityUserDetailBinding
import com.hmyh.hmyhuserinformation.viewmodel.UserDetailViewModel

class UserDetailActivity : BaseActivity() {

    companion object {
        const val EXTRA_USER_ID = "extra user id"

        fun newIntent(context: Context, userId: Int): Intent {
            return Intent(context, UserDetailActivity::class.java).apply {
                putExtra(EXTRA_USER_ID, userId)
            }
        }
    }

    private lateinit var userDetailViewModel: UserDetailViewModel

    private lateinit var binding: ActivityUserDetailBinding

    private var userId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
        getDataFromIntent()
        setUpDataObservation()
        setUpListener()
    }

    private fun setUpViewModel() {
        userDetailViewModel = ViewModelProviders.of(this)[UserDetailViewModel::class.java]
    }

    private fun getDataFromIntent() {
        intent.getIntExtra(EXTRA_USER_ID, 0).let {
            userId = it
        }
    }

    private fun setUpDataObservation() {
        userId?.let { mUserId ->
            userDetailViewModel.getUserByUserId(mUserId).observe(this, Observer {
                it?.let { userList->
                    setUpData(userList)
                }
            })
        }

    }

    @SuppressLint("SetTextI18n")
    private fun setUpData(user: UserListVO) {
        binding.tvNameDetail.text = user.name ?: ""
        binding.tvUserNameDetail.text = "@${user.useName}"
        binding.tvEmailDetail.text = user.email ?: ""

        user.address?.let { address->
            binding.tvAddressDetail.text = "${address.suite}, ${address.street}, ${address.city}"
        }

        binding.tvPhoneDetail.text = user.phone ?: ""
        binding.tvWebsiteDetail.text = user.website ?: ""
    }

    private fun setUpListener() {
        binding.ivBackDetail.setOnClickListener {
            finish()
        }
    }

}