package com.github.adapter

import android.graphics.Paint
import android.util.SparseArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mViews = SparseArray<View?>()

        /**
         * Get ID
         *
         * @param viewId ID
         * @param <T>
        </T> */
        fun <T : View?> getView(viewId: Int): T? {
            var view = mViews[viewId]
            if (view == null) {
                view = itemView.findViewById(viewId)
                mViews.put(viewId, view)
            }
            return view as T?
        }

        /**
         * @param viewId ID
         * @param text   Set the text content
         */
        fun setText(viewId: Int, text: String?): BaseViewHolder {
            val tv = getView<TextView>(viewId)!!
            tv.text = text
            return this
        }

        /**
         * @param viewId ID
         * @return Get the text
         */
        fun getText(viewId: Int): String {
            val tv = getView<TextView>(viewId)!!
            return tv.text.toString()
        }

        /**
         * Set the color of the text
         *
         * @param viewId ID
         * @param resID  Color resources
         */
        fun setTextColor(viewId: Int, resID: Int): BaseViewHolder {
            val tv = getView<TextView>(viewId)!!
            tv.setTextColor(resID)
            return this
        }

        /**
         * Draw a line equally in the text
         *
         * @param viewID ID
         */
        fun setFlags(viewID: Int): BaseViewHolder {
            val tv = getView<TextView>(viewID)!!
            tv.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG or Paint.ANTI_ALIAS_FLAG
            return this
        }

        /**
         * Set up a picture resource
         *
         * @param viewID ID
         * @param resId  Photo Resources
         */
        fun setImageRes(viewID: Int, resId: Int): BaseViewHolder {
            val iv = getView<ImageView>(viewID)!!
            iv.setImageResource(resId)
            return this
        }
    }