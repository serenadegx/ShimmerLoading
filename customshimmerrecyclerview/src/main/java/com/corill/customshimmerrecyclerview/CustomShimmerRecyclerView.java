package com.corill.customshimmerrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by hhhhh on 2017/9/26.
 */

public class CustomShimmerRecyclerView extends RecyclerView {

    public enum LayoutMangerType {
        LINEAR_VERTICAL, LINEAR_HORIZONTAL, mLayoutMangerType, GRID
    }
    private LayoutMangerType mLayoutMangerType = LayoutMangerType.LINEAR_VERTICAL;
    private CustomShimmerAdapter mShimmerAdapter;
    private LayoutManager mShimmerLayoutManager;
    private LayoutManager mRealLayoutManager;
    private Adapter mRealAdapter;
    private int mGridCount;
    private boolean mCanScroll;

    public CustomShimmerRecyclerView(Context context) {
        super(context);
        init(null);
    }

    public CustomShimmerRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomShimmerRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.ShimmerRecyclerView, 0, 0);
        int mShimmerAngle;
        int mShimmerColor;
        int duration;
        Drawable mShimmerItemBackground;
        duration = ta.getInteger(R.styleable.ShimmerRecyclerView_shimmer_duration, 1000);
        mShimmerAdapter = new CustomShimmerAdapter(duration);

        if (ta.hasValue(R.styleable.ShimmerRecyclerView_shimmer_layout)){
            setLayoutReference(ta.getResourceId(R.styleable.ShimmerRecyclerView_shimmer_layout,
                    R.layout.layout_sample_view));//默认item布局layout_sample_view
        }

        if (ta.hasValue(R.styleable.ShimmerRecyclerView_shimmer_child_count)){
            setSimpleChildCount(ta.getInteger(R.styleable.ShimmerRecyclerView_shimmer_child_count, 5));//默认为item条数为5
        }

        if (ta.hasValue(R.styleable.ShimmerRecyclerView_shimmer_grid_child_count)) {
            setGridChildCount(ta.getInteger(R.styleable.ShimmerRecyclerView_shimmer_grid_child_count, 2));
        }

        if (ta.hasValue(R.styleable.ShimmerRecyclerView_shimmer_layout_manager_type)) {
            switch (ta.getInteger(R.styleable.ShimmerRecyclerView_shimmer_layout_manager_type, 0)) {
                case 1:
                    setDemoLayoutManager(LayoutMangerType.LINEAR_HORIZONTAL);
                    break;
                case 2:
                    setDemoLayoutManager(LayoutMangerType.GRID);
                    break;
                case 0:
                default:
                    setDemoLayoutManager(LayoutMangerType.LINEAR_VERTICAL);
                    break;

            }
        }
        mShimmerAngle = ta.getInteger(R.styleable.ShimmerRecyclerView_shimmer_angle, 0);
        mShimmerColor = ta.getColor(R.styleable.ShimmerRecyclerView_shimmer_shimmer_color, getColor(R.color.default_shimmer_color));
        mShimmerItemBackground = ta.getDrawable(R.styleable.ShimmerRecyclerView_shimmer_view_holder_item_background);


        ta.recycle();

        mShimmerAdapter.setShimmerAngle(mShimmerAngle);
        mShimmerAdapter.setShimmerColor(mShimmerColor);
        mShimmerAdapter.setShimmerItemBackground(mShimmerItemBackground);
        showShimmerAdapter();
    }

    public void setLayoutReference(int mLayoutReference) {
        mShimmerAdapter.setLayoutReference(mLayoutReference);
    }


    public void setSimpleChildCount(int simpleChildCount) {
        mShimmerAdapter.setLoadingItemCount(simpleChildCount);
    }


    public void setGridChildCount(int gridChildCount) {
        this.mGridCount = gridChildCount;
    }

    public void setDemoLayoutManager(LayoutMangerType type) {
        mLayoutMangerType = type;
    }

    public void showShimmerAdapter() {
        mCanScroll = false;

        if (mShimmerLayoutManager == null) {
            initShimmerManager();
        }

        setLayoutManager(mShimmerLayoutManager);
        setAdapter(mShimmerAdapter);

    }

    private void initShimmerManager() {

        switch (mLayoutMangerType) {
            case LINEAR_VERTICAL:
                mShimmerLayoutManager = new LinearLayoutManager(getContext()) {
                    public boolean canScrollVertically() {
                        return mCanScroll;
                    }
                };
                break;
            case LINEAR_HORIZONTAL:
                mShimmerLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false) {
                    public boolean canScrollHorizontally() {
                        return mCanScroll;
                    }
                };
                break;
            case GRID:
                mShimmerLayoutManager = new GridLayoutManager(getContext(), mGridCount) {
                    public boolean canScrollVertically() {
                        return mCanScroll;
                    }
                };
                break;


        }
    }

    public void setLayoutManager(LayoutManager manager) {

        if (manager == null) {
            mRealLayoutManager = null;
        } else if (manager != mShimmerLayoutManager) {
            mRealLayoutManager = manager;
        }

        super.setLayoutManager(manager);
    }

    public void setAdapter(Adapter adapter) {

        if (adapter == null) {
            mRealAdapter = null;
        } else if (adapter != mShimmerAdapter) {
            mRealAdapter = adapter;
        }

        super.setAdapter(adapter);

    }

    public void hideShimmerAdapter() {
        mCanScroll = true;
        setLayoutManager(mRealLayoutManager);
        setAdapter(mRealAdapter);
    }

    private int getColor(int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getContext().getColor(id);
        } else {
            return getResources().getColor(id);
        }
    }
}
