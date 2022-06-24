package com.hmyh.hmyhuserinformation.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Hsu Myat Ye Htet on 6/24/2022
 */
abstract class BaseViewHolder<W>(itemView: View): RecyclerView.ViewHolder(itemView) {

    protected var mData: W? = null

    abstract fun bindData(data: W)
}