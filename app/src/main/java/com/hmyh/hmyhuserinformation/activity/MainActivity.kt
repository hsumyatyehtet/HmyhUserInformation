package com.hmyh.hmyhuserinformation.activity

import android.os.Bundle
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmyh.hmyhuserinformation.BuildConfig
import com.hmyh.hmyhuserinformation.adapter.UserListAdapter
import com.hmyh.hmyhuserinformation.data.vos.UserListVO
import com.hmyh.hmyhuserinformation.databinding.ActivityMainBinding
import com.hmyh.hmyhuserinformation.viewmodel.UserListViewModel

class MainActivity : BaseActivity(){

    private lateinit var viewModel: UserListViewModel

    private lateinit var binding: ActivityMainBinding

    private lateinit var mUserListAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
        setUpRecyclerView()

        setUpOnUiReady()
        setUpDataObservations()
    }

    private fun setUpOnUiReady() {
        viewModel.onUiReady()
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(this)[UserListViewModel::class.java]
    }

    private fun setUpRecyclerView() {
        mUserListAdapter = UserListAdapter(viewModel)
        binding.rvUserList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvUserList.adapter = mUserListAdapter
    }

    private fun setUpDataObservations() {

        viewModel.getUserList().observe(this, Observer {
            it?.let { userList->
                mUserListAdapter.setNewData(userList as MutableList<UserListVO>)
            }
        })

        viewModel.getNavigateToUserDetailLiveData().observe(this, Observer {
            it?.let { userId->
                startActivity(UserDetailActivity.newIntent(this, userId))
            }
        })

    }

}