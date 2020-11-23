package com.github.adapter;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 普通分割线
 * 具体参考https://developer.android.google.cn/reference/kotlin/androidx/recyclerview/widget/RecyclerView.ItemDecoration?hl=en#getItemOffsets(android.graphics.Rect,%20android.view.View,%20androidx.recyclerview.widget.RecyclerView,%20androidx.recyclerview.widget.RecyclerView.State)
 */
class BaseItemDecoration extends RecyclerView.ItemDecoration {
    /**
     * @param outRect
     * @param view    recyclerView ItemView
     * @param parent  recyclerView
     * @param state   recycler
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        /*设定底部边距为1px*/
        outRect.set(0, 0, 0, 1);
    }
}