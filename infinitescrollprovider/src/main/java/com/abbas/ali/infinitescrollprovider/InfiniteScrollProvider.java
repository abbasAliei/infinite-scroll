package com.abbas.ali.infinitescrollprovider;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * created by abbas aliei at 7 september 2018
 * this class provide the infinite scrolling behavior for android RecyclerView
 */

public class InfiniteScrollProvider {

    /**
     * the {@link #recyclerView} which we need to have infinite scrolling
     */
    private RecyclerView recyclerView;

    /**
     * used to define the {@link #lastVisibleItem}, {@link #totalItemsCount} and {@link #previousItemCount}
     */
    private RecyclerView.LayoutManager layoutManager;

    /**
     * the listener will called when user reach to end of list
     */
    private OnLoadMoreListener onLoadMoreListener;

    /**
     * position of the last item user can see
     */
    private int lastVisibleItem;

    /**
     * total items count of before last time {@link #onLoadMoreListener} is called
     */
    private int previousItemCount = 0;

    /**
     * total items count of {@link #recyclerView}
     */
    private int totalItemsCount;

    /**
     * the threshold of {@link #recyclerView}
     * <p>
     * {@link #onLoadMoreListener} will called when user reach to {@link #totalItemsCount} - {@link #THRESHOLD}
     */
    private static final int THRESHOLD = 3;

    /**
     * to determine are user currently wait for loading items or not
     */
    private boolean isLoading = false;

    /**
     * used to set infinite scrolling behavior on {@link #recyclerView}
     *
     * @param recyclerView
     * @param onLoadMoreListener
     */
    public void setInfiniteScroll(RecyclerView recyclerView, OnLoadMoreListener onLoadMoreListener) {
        this.recyclerView = recyclerView;
        this.layoutManager = recyclerView.getLayoutManager();
        this.onLoadMoreListener = onLoadMoreListener;
        setScrollControl(layoutManager);
    }

    /**
     * get control of the {@link #recyclerView} scrolling
     * call the {@link #onLoadMoreListener} whe user reach to end of list
     *
     * @param layoutManager
     */
    private void setScrollControl(final RecyclerView.LayoutManager layoutManager) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                totalItemsCount = layoutManager.getItemCount();

                if (previousItemCount > totalItemsCount) {
                    previousItemCount = totalItemsCount - THRESHOLD;
                }

                if (layoutManager instanceof GridLayoutManager) {
                    lastVisibleItem = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                } else if (layoutManager instanceof LinearLayoutManager) {
                    lastVisibleItem = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                    StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                    int spanCount = staggeredGridLayoutManager.getSpanCount();
                    int[] ids = new int[spanCount];
                    staggeredGridLayoutManager.findLastVisibleItemPositions(ids);

                    int max = ids[0];
                    for (int i = 0; i < ids.length; i++) {
                        if (ids[i] > max) {
                            max = ids[i];
                        }
                    }
                    lastVisibleItem = max;
                }

                if (totalItemsCount > THRESHOLD) {
                    if (previousItemCount <= totalItemsCount && isLoading) {
                        isLoading = false;
                    }
                    if (previousItemCount < totalItemsCount && !isLoading && (lastVisibleItem > (totalItemsCount - THRESHOLD))) {
                        if (onLoadMoreListener != null) {
                            onLoadMoreListener.onLoadMore();
                        }
                        isLoading = true;
                        previousItemCount = totalItemsCount;
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
