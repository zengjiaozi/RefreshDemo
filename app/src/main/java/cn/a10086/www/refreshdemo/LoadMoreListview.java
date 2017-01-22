package cn.a10086.www.refreshdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * @author
 * @time 2017/1/22  10:24
 * @desc ${TODD}
 */
public class LoadMoreListview extends ListView implements AbsListView.OnScrollListener {

    private static final String TAG = "RefreshListView";
    /**
     * 底部加载更多部分
     */
    private boolean isScrollToBottom;//判断是不是滑到了底部
    private View footerView; //底部的footer   view
    private int footerViewHeight; //底部view的高度
    private boolean isLoadingMore = false; //判断是不是"加载更多"

    /**
     * listview的接口，监听listview的下来刷新和上拉加载更多
     */
    private OnRefreshListener mOnRefreshListener;


    public LoadMoreListview(Context context) {
        super(context);
    }

    public LoadMoreListview(Context context, AttributeSet attrs) {
        super(context, attrs);
//        初始化foot布局
        initFootView();
        this.setOnScrollListener(this);
    }


    /**
     * 初始化底部View
     */
    private void initFootView() {
        footerView = View.inflate(getContext(), R.layout.footview, null);
        //设置（0，0）以便系统测量footerView的宽高
        footerView.measure(0, 0);
        int measuredHeight = footerView.getMeasuredHeight();
        footerView.setPadding(0, -measuredHeight, 0, 0);
        this.addFooterView(footerView);

    }

    /**
     * 监听listview滚动的状态变化，如果滑到了底部，就“加载更多..."
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_FLING) {
            if (isScrollToBottom && !isLoadingMore) {
                isLoadingMore = true;
                footerView.setPadding(0, 0, 0, 0);
                this.setSelection(this.getCount());
                if (mOnRefreshListener != null) {
                    mOnRefreshListener.onLoadingMore();
                }
            }

        }


    }

    //    监听是否滑动到了底部
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if (getLastVisiblePosition() == (totalItemCount - 1)) {
            isScrollToBottom = true;
        } else {
            isScrollToBottom = false;
        }
    }

    /**
     * 设置监听接口，当为
     *
     * @param listener
     */
    public void setOnRefreshListener(OnRefreshListener listener) {
        mOnRefreshListener = listener;
    }


    /**
     * 为外界提供的方法，当Activity中的加载更多数据加载完后，就调用这个方法来隐藏底部的footerView
     */
    public void loadMoreComplete() {
        footerView.setPadding(0, -footerViewHeight, 0, 0);
        isLoadingMore = false;
    }


    /**
     * 设置接口，供外界实现，监听listview的刷新和加载更多的状态
     */
    public interface OnRefreshListener {
        /**
         * 上拉加载更多
         */
        void onLoadingMore();
    }
}
