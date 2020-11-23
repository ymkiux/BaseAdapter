package com.github.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.annotation.IntRange
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.woo.tools.R
import java.util.*


abstract class BaseAdapter<T> : Adapter<BaseViewHolder> {
    protected var tList: MutableList<T>? = ArrayList()
    protected var recyclerView: RecyclerView? = null
    private var cusom_animation_tag = false

    /**
     * Gets the adapter access sub-control content set
     *
     * @return HashMap collection
     */
    var map =
        HashMap<String, String>()

    abstract fun getlnflater(): Int


    /**
     * Construct a non-participating method
     */
    constructor() {}

    /**
     * Initialize the data
     *
     * @param recyclerView
     */
    constructor(recyclerView: RecyclerView?) {
        this.recyclerView = recyclerView
    }

    /**
     * Implement this method to manipulate the view
     *
     * @param helper Represents the content at a given location in the data set
     * @param list   The dataSet that needs to be displayed
     */
    protected abstract fun convert(helper: BaseViewHolder?, list: T?)

    /**
     * @param position The position of the control in the adapter data set
     * @return Specify the location of the data
     */
    fun getItem(@IntRange(from = 0) position: Int): T? {
        return if (position >= 0 && position < tList!!.size) tList!![position] else null
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        if (recyclerView != null && !cusom_animation_tag) {
            val animation =
                AnimationUtils.loadAnimation(
                    recyclerView!!.context,
                    R.anim.load_default
                )
            holder.itemView.startAnimation(animation)
        }
        convert(holder, getItem(position))
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(getlnflater(), parent, false)
        return BaseViewHolder(view)
    }

    /**
     * Add an empty page
     *
     * @param context The current activity
     * @param id      Parent control ID
     * @param resID   The content of the resource
     */
    @SuppressLint("ObsoleteSdkInt")
    @Throws(Exception::class)
    fun addEmptyViews(context: Context, id: Int, resID: Int) {
        if (tList!!.size != 0) throw Exception("This method cannot be called when a dataSet exists")
        val relativeLayout =
            (context as Activity).findViewById<View>(id) as RelativeLayout
        val frameLayoutParm = FrameLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        relativeLayout.layoutParams = frameLayoutParm
        val iv_adapter_empty_view = ImageView(context)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) iv_adapter_empty_view.id =
            View.generateViewId() else iv_adapter_empty_view.id = ViewCompat.generateViewId()
        iv_adapter_empty_view.setImageResource(resID)
        val layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        iv_adapter_empty_view.layoutParams = layoutParams
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT)
        relativeLayout.addView(iv_adapter_empty_view)
    }

    /**
     * Custom item animation
     *
     * @param animator Custom animation classes
     */
    fun setCusItemAnimator(animator: ItemAnimator?) {
        if (recyclerView != null) {
            cusom_animation_tag = true
            recyclerView!!.itemAnimator = animator
        }
    }

    /**
     * Normal gray split line
     *
     * @param ItemDecorationStatus Split line state
     */
    fun addItemDecoration(ItemDecorationStatus: Boolean) {
        if (recyclerView != null) if (ItemDecorationStatus) recyclerView!!.addItemDecoration(
            BaseItemDecoration()
        )
    }

    /**
     * Custom split lines
     *
     * @param decor Split line View classes
     */
    fun addCusItemDecoration(decor: ItemDecoration) {
        if (recyclerView != null) recyclerView!!.addItemDecoration(decor)
    }

    /**
     * @return The length of the dataSet
     */
    override fun getItemCount(): Int {
        return if (tList != null) tList!!.size else 0
    }

    /**
     * Remove the specified content
     *
     * @param position The position of the control in the adapter data set
     */
    fun remove(position: Int) {
        tList!!.removeAt(position)
        notifyDataSetChanged()
    }

    /**
     * Clear old data to add new dataSets and update them
     *
     * @param tList The array set
     */
    fun setData(tList: List<T>?) {
        if (null != tList) {
            this.tList!!.clear()
            this.tList!!.addAll(tList)
            notifyDataSetChanged()
        }
    }

    /**
     * Gets the child controls stored in the dataSet
     *
     * @param hashMap HashMap collection
     */
    fun setHashMap(hashMap: HashMap<String, String>) {
        map = hashMap
    }

    /**
     * Adapter jump event
     *
     * @param context The current activity
     * @param cls     Jump class
     * @param anim    Whether to turn on the animation
     */
    fun startActivity(
        context: Context,
        cls: Class<*>?,
        anim: Boolean
    ) {
        val intent = Intent(context, cls)
        context.startActivity(intent)
        if (anim) {
            (context as Activity).overridePendingTransition(0, 0)
        }
    }

    /**
     * adapter jump carrying param
     *
     * @param context The current activity
     * @param cls     Jump class
     * @param Key     key
     * @param value   carrying param
     * @param anim    Whether to turn on the animation
     */
    fun startActivityPram(
        context: Context,
        cls: Class<*>?,
        Key: String?,
        value: Int?,
        anim: Boolean
    ) {
        val intent = Intent(context, cls)
        intent.putExtra(Key, value)
        context.startActivity(intent)
        if (anim) {
            (context as Activity).overridePendingTransition(0, 0)
        }
    }
}