package com.hmyh.hmyhuserinformation.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.hmyh.hmyhuserinformation.BuildConfig
import com.hmyh.hmyhuserinformation.R
import com.hmyh.hmyhuserinformation.adapter.UserListAdapter
import com.hmyh.hmyhuserinformation.data.vos.UserListVO
import com.hmyh.hmyhuserinformation.databinding.ActivityMainBinding
import com.hmyh.hmyhuserinformation.viewmodel.UserListViewModel

class MainActivity : BaseActivity() {

    private lateinit var viewModel: UserListViewModel

    private lateinit var binding: ActivityMainBinding

    private lateinit var mUserListAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        setUpViewModel()
        setUpRecyclerView()
        setUpListener()
        setUpOnUiReady()
        setUpDataObservations()
    }

    private fun setUpListener() {

        binding.etUserSearch.requestFocus()

        val handler = Handler(Looper.getMainLooper())

        binding.etUserSearch.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                handler.removeCallbacksAndMessages(null)
                handler.postDelayed({
                    s?.toString()?.let { searchWord ->
                        onChangeTextAfterSecond(searchWord)
                    }
                }, 600)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        binding.rlUserListRefresh.setOnClickListener {
            val aniRotate: Animation = AnimationUtils.loadAnimation(this, R.anim.clock_wise)
            binding.ivUserListRefresh.startAnimation(aniRotate)

            binding.etUserSearch.text?.clear()

            viewModel.getUserList().observe(this, Observer {
                it?.let { userList ->
                    mUserListAdapter.setNewData(userList as MutableList<UserListVO>)
                }
            })
        }

    }

    private fun onChangeTextAfterSecond(searchWord: String) {
        viewModel.loadSearch(searchWord).observe(this, Observer {
            it?.let { userList->

                if (userList.isNullOrEmpty()){
                    binding.ivNoSearch.visibility = View.VISIBLE
                    binding.rvUserList.visibility = View.GONE
                }
                else{
                    binding.ivNoSearch.visibility = View.GONE
                    binding.rvUserList.visibility = View.VISIBLE
                    mUserListAdapter.setNewData(userList as MutableList<UserListVO>)
                }

            }
        })
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
            it?.let { userList ->
                mUserListAdapter.setNewData(userList as MutableList<UserListVO>)
            }
        })

        viewModel.getNavigateToUserDetailLiveData().observe(this, Observer {
            it?.let { userId ->
                startActivity(UserDetailActivity.newIntent(this, userId))
            }
        })

    }

}