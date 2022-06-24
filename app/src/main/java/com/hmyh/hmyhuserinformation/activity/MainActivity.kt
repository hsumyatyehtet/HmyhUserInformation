package com.hmyh.hmyhuserinformation.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmyh.hmyhuserinformation.R
import com.hmyh.hmyhuserinformation.adapter.UserListAdapter
import com.hmyh.hmyhuserinformation.databinding.ActivityMainBinding
import com.hmyh.hmyhuserinformation.utils.getUserList

class MainActivity : BaseActivity(),UserListAdapter.Delegate {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mUserListAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        setUpDataObservations()
    }

    private fun setUpRecyclerView() {
        mUserListAdapter = UserListAdapter(this)
        binding.rvUserList.layoutManager =
            LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.rvUserList.adapter = mUserListAdapter
    }

    private fun setUpDataObservations() {
        mUserListAdapter.setNewData(getUserList())
    }

    override fun onTapUserItem(userId: Int) {
        startActivity(UserDetailActivity.newIntent(this,userId))
    }

}