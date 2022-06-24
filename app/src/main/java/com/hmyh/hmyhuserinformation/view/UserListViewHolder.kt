package com.hmyh.hmyhuserinformation.view

import android.view.View
import com.hmyh.hmyhuserinformation.adapter.UserListAdapter
import com.hmyh.hmyhuserinformation.data.vos.UserListVO
import com.hmyh.hmyhuserinformation.databinding.ViewHolderInUserListBinding

class UserListViewHolder(
    private val binding: ViewHolderInUserListBinding,
    val delegate: UserListAdapter.Delegate
)
    : BaseViewHolder<UserListVO>(binding.root) {

    init {
        binding.cvUserListContainer.setOnClickListener {
            mData?.let { mData->
                mData.id?.let { id->
                    delegate.onTapUserItem(id)
                }

            }
        }
    }

    override fun bindData(data: UserListVO) {
        mData = data

        binding.tvNameItem.text = "${data.name} ( @${data.useName} )"
        binding.tvPhoneItem.text = data.phone ?: ""
        binding.tvEmailItem.text = data.email ?: ""
    }

}