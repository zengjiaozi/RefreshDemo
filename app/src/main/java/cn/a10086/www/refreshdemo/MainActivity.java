package cn.a10086.www.refreshdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoadMoreListview.OnRefreshListener, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private LoadMoreListview loadMoreListView;
    private ArrayList<ListViewItem> items;
    private RefreshListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        initData();
    }

    private void initData() {
        items = new ArrayList<ListViewItem>();

        //这里只是模拟10个列表项数据，在现实开发中，listview中的数据都是从服务器获取的。
        for (int i = 0; i < 10; i++) {
            ListViewItem item = new ListViewItem();
            item.setUserImg(R.mipmap.ic_launcher);
            item.setUserName("seven" + i);
            item.setUserComment("这是google官方一个下拉刷新ListView+自定义ListView上拉加载跟多");
            items.add(item);
        }


        //为listview配置adapter
        adapter = new RefreshListAdapter(MainActivity.this, items);
        loadMoreListView.setAdapter(adapter);
    }

    private void initEvent() {
        swipeRefreshLayout.setOnRefreshListener(this);

//        设置圆圈进度条的背景颜色
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.colorPrimary));


    }

    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_google_and_loadmore_refresh_layout);
        loadMoreListView = (LoadMoreListview) findViewById(R.id.google_and_loadmore_refresh_listview);
        loadMoreListView.setOnRefreshListener(this);
    }

    @Override
    public void onLoadingMore() {
        //因为本例中没有从网络获取数据，因此这里使用Handler演示4秒延迟来从服务器获取数据的延迟现象，以便于大家
        // 能够看到listView正在刷新的状态。大家在现实使用时只需要使用run（）{}方法中的代码就行了。
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //获取最新的list数据
                setRefreshData();
                //通知界面显示，
                adapter.notifyDataSetChanged();
                // 通知listview刷新数据完毕,让listview停止刷新,取消加载跟多
                loadMoreListView.loadMoreComplete();
            }
        }, 6000);
    }


    @Override
    public void onRefresh() {
        //因为本例中没有从网络获取数据，因此这里使用Handler演示4秒延迟来从服务器获取数据的延迟现象，以便于大家
        // 能够看到listView正在刷新的状态。大家在现实使用时只需要使用run（）{}方法中的代码就行了。
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //获取最新的list数据
                setRefreshData();
                //通知界面显示，
                adapter.notifyDataSetChanged();
                // 通知listview刷新数据完毕,让listview停止刷新
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 6000);


    }

    private void setRefreshData() {
        for (int i = 0; i < 3; i++) {
            ListViewItem item = new ListViewItem();
            item.setUserImg(R.mipmap.ic_launcher);
            item.setUserName("seven" + i);
            item.setUserComment("这是一个下拉刷新，上拉加载更多的ListView");
            items.add(item);
        }

    }
}
