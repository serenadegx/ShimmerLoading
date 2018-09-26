package com.corill.customshimmerrecyclerview;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.supercharge.shimmerlayout.ShimmerLayout;

/**
 * Created by hhhhh on 2017/9/26.
 */

public class CustomShimmerViewHolder extends RecyclerView.ViewHolder {

    private final ShimmerLayout mShimmerLayout;
    private int mDuration;

    public CustomShimmerViewHolder(int mDuration,LayoutInflater inflater, ViewGroup parent, int innerViewResId) {
        super(inflater.inflate(R.layout.viewholder_shimmer, parent, false));
        this.mDuration = mDuration;
        mShimmerLayout = (ShimmerLayout) itemView;
        //填充自己加载中的item布局
        inflater.inflate(innerViewResId, mShimmerLayout, true);
    }

    public void setShimmerAngle(int angle) {
        mShimmerLayout.setShimmerAngle(angle);
    }

    public void setShimmerColor(int color) {
        mShimmerLayout.setShimmerColor(color);
    }

    public void setShimmerViewHolderBackground(Drawable viewHolderBackground) {
        if (viewHolderBackground != null) {
            setBackground(viewHolderBackground);
        }
    }

    public void setDuration(int duration){
        mShimmerLayout.setShimmerAnimationDuration(duration);
    }
    private void setBackground(Drawable background) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            mShimmerLayout.setBackground(background);
        } else {
            mShimmerLayout.setBackgroundDrawable(background);
        }
    }

    public void bind() {
        ShimmerLayout layout = (ShimmerLayout) itemView;
        layout.setShimmerAnimationDuration(mDuration);
        layout.startShimmerAnimation();
    }
}
