package com.corill.customshimmerrecyclerview;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by hhhhh on 2017/9/26.
 */

public class CustomShimmerAdapter extends RecyclerView.Adapter<CustomShimmerViewHolder> {

    private int mItemCount = 10;
    private int mLayoutReference = R.layout.layout_sample_view;
    private int mShimmerAngle;
    private int mShimmerColor;
    private int mDuration = 1000;
    private Drawable mShimmerItemBackground;

    public CustomShimmerAdapter(int duration) {
        mDuration = duration;
    }

    @Override
    public CustomShimmerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomShimmerViewHolder viewHolder = new CustomShimmerViewHolder(mDuration, inflater, parent, mLayoutReference);
        viewHolder.setShimmerColor(mShimmerColor);
        viewHolder.setShimmerAngle(mShimmerAngle);
        viewHolder.setShimmerViewHolderBackground(mShimmerItemBackground);
        viewHolder.setDuration(mDuration);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomShimmerViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }

    public void setLoadingItemCount(int itemCount) {
        mItemCount = itemCount;
    }


    public void setShimmerAngle(int shimmerAngle) {
        this.mShimmerAngle = shimmerAngle;
    }

    public void setShimmerColor(int shimmerColor) {
        this.mShimmerColor = shimmerColor;
    }

    public void setShimmerItemBackground(Drawable shimmerItemBackground) {
        this.mShimmerItemBackground = shimmerItemBackground;
    }

    public void setDuration(int duration){
        this.mDuration = duration;
    }

    /**
     * 设置加载中item布局
     *
     * @param layoutReference
     */
    public void setLayoutReference(int layoutReference) {
        this.mLayoutReference = layoutReference;
    }
}
