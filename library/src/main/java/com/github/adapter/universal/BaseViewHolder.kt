package com.github.adapter.universal

import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View
import android.widget.TextView
import com.github.adapter.universal.interfaces.OnClickCall
import com.github.adapter.universal.interfaces.OnLongClickCall


class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var mViews = SparseArray<View?>()

    //Get view ID
    fun <T : View?> getView(viewId: Int): T? {
        var view = mViews[viewId]
        if (view == null) {
            view = itemView.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view as T?
    }

    //Draw a line equally in the text
    fun setFlags(viewID: Int): BaseViewHolder {
        val tv = getView<TextView>(viewID)!!
        tv.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG or Paint.ANTI_ALIAS_FLAG
        return this
    }

    //call back the user's response behavior through the id control click event
    fun setOnClick(viewID: Int, onClickCall: OnClickCall) {
        (getView<TextView>(viewID) as View).setOnClickListener {
            onClickCall.doWork()
        }
    }

    //call back the user's response behavior through the id control long press event
    fun setOnLongClick(viewID: Int, onLongClickCall: OnLongClickCall) {
        (getView<TextView>(viewID) as View).setOnLongClickListener {
            onLongClickCall.doWork()
            return@setOnLongClickListener true
        }
    }
}