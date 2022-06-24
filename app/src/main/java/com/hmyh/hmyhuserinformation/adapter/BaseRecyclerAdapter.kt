package com.hmyh.hmyhuserinformation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.hmyh.hmyhuserinformation.view.BaseViewHolder

/**
 * Created by Hsu Myat Ye Htet on 6/24/2022
 */
abstract class BaseRecyclerAdapter<T: BaseViewHolder<W>,W>: RecyclerView.Adapter<T>() {
    protected var mData: MutableList<W> = ArrayList()

    override fun getItemCount() = mData.size

    override fun onBindViewHolder(holder: T, position: Int) = holder.bindData(mData[position])

    fun setNewData(newData: MutableList<W>) {
        if (newData.isEmpty()) mData.clear() else mData = newData

        notifyDataSetChanged()
    }

    fun appendNewData(newData: MutableList<W>){
        if(newData.isEmpty()) return

        mData.addAll(newData)
        notifyItemRangeInserted(mData.size, newData.size)
    }

    fun getItemAt(position : Int) : W?{
        return if(position < mData.size) mData[position] else null
    }

    fun removeData(data : W){
        mData.remove(data)
        notifyDataSetChanged()
    }

    fun removeGently(data : W){
        val index = mData.indexOf(data)

        if(index == -1) return

        mData.remove(data)
        notifyItemRemoved(index)
        notifyItemRangeChanged(index,mData.size)
        notifyDataSetChanged()
    }

    fun addNewData(data : W){
        mData.add(data)
      //  mData.add(data)
        notifyDataSetChanged()
    }

    fun clearData(){
        mData = ArrayList()
        notifyDataSetChanged()
    }
}