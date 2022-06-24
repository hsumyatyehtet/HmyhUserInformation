package com.hmyh.hmyhuserinformation.view

import android.view.View
import com.hmyh.hmyhuserinformation.data.vos.UserListVO
import com.hmyh.hmyhuserinformation.databinding.ViewHolderInUserListBinding

class UserListViewHolder(
    private val binding: ViewHolderInUserListBinding
)
    : BaseViewHolder<UserListVO>(binding.root) {

    override fun bindData(data: UserListVO) {
        mData = data

        binding.tvNameItem.text = "${data.name} ( @${data.useName} )"
        binding.tvUserNameItem.text = "@${data.useName}"
        binding.tvEmailItem.text = data.email ?: ""
    }

}