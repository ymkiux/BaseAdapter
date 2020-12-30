package com.github.adapter.universal

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import java.util.*

abstract class BaseAdapter<T>(private val getLayoutId: Int) :
    RecyclerView.Adapter<BaseViewHolder>() {
    private var arrayList: MutableList<T>? = ArrayList()

    @Suppress("UNCHECKED_CAST")
    fun setData(list: List<T?>?) {
        if (arrayList != null) {
            arrayList?.clear()
            arrayList = list as MutableList<T>?
            notifyDataSetChanged()
        }
    }

    //When using the onBindViewHolder method, call the getListData() method to obtain the corresponding data set
    fun getListData(): MutableList<T>? {
        return arrayList
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BaseViewHolder {
        val view = LayoutInflater.from(p0.context)
            .inflate(getLayoutId, p0, false)
        return BaseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (arrayList!!.size!=0) arrayList!!.size else 0
    }

    //Remove the position item under its view to show that the adapter removes the default animation by default
    fun removeItem(position: Int) {
        arrayList?.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    override fun onBindViewHolder(p0: BaseViewHolder, p1: Int) {

    }
}