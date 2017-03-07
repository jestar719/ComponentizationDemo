package cn.jestar.girl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import cn.jestar.projectlibrary.NavigationPath;
import cn.jestar.projectlibrary.base.BaseMVPActivity;
import cn.jestar.projectlibrary.base.BaseModel;

@Route(path = NavigationPath.GIRL_GIRL)
public class GirlActivity extends BaseMVPActivity<GirlContact.GirlPresent> implements GirlContact.GirlView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.common_recycler_view)
    RecyclerView mRecycler;
    @BindView(R.id.common_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    private GirlsPhotoAdapter mAdapter;

    @NonNull
    @Override
    protected GirlContact.GirlPresent initPresent() {
        return new GirlPresentImpl();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_girl;
    }

    @Override
    protected void init() {
        StaggeredGridLayoutManager layout = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layout.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecycler.setLayoutManager(layout);
        mRecycler.setAdapter(getAdapter());
        mRefreshLayout.setOnRefreshListener(this);
        mRecycler.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                BaseModel item = (BaseModel) adapter.getItem(position);
                String url = item.getUrl();
                Intent intent = new Intent(GirlActivity.this, PhotoActivity.class);
                intent.putExtra("url", url);
                Bundle share = ActivityOptionsCompat.makeSceneTransitionAnimation(GirlActivity.this, view, "share").toBundle();
                startActivity(intent, share);
            }
        });

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mPresent.getNew();
    }

    private RecyclerView.Adapter getAdapter() {
        mAdapter = new GirlsPhotoAdapter();
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresent.loadMore();
            }
        });
        return mAdapter;
    }

    @Override
    public void onLoadMore(List<BaseModel> list, boolean isEnd) {
        if (isEnd) {
            mAdapter.loadMoreEnd();
        } else {
            mAdapter.addData(list);
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onGetNew(List<BaseModel> list) {
        mAdapter.setNewData(list);
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onError(CharSequence msg) {

    }

    @Override
    public void onRefresh() {
        mPresent.getNew();
    }
}
