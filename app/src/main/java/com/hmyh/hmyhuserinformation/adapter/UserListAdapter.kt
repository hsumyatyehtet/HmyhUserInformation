package com.hmyh.hmyhuserinformation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hmyh.hmyhuserinformation.data.vos.UserListVO
import com.hmyh.hmyhuserinformation.databinding.ViewHolderInUserListBinding
import com.hmyh.hmyhuserinformation.view.UserListViewHolder

class UserListAdapter() : BaseRecyclerAdapter<UserListViewHolder, UserListVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        return UserListViewHolder(
            ViewHolderInUserListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
}