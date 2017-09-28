package com.github.xu.gank.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.xu.gank.R;
import com.github.xu.gank.adapter.RecyclerAdapter;
import com.github.xu.gank.api.GankService;
import com.github.xu.gank.bean.BoonBean;
import com.github.xu.gank.service.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xukankan on 2017/9/25.
 */

public class ImageFragment extends Fragment {
    private RecyclerView imageRecyclerView;
    private RecyclerAdapter recyclerAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private int page;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        mRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.layout_swipe_refresh);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               getMoreData(1,2);
            }
        });
        imageRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_image);
        imageRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerAdapter=new RecyclerAdapter(new ArrayList<String>(),view);
        imageRecyclerView.setAdapter(recyclerAdapter);
        imageRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!recyclerView.canScrollVertically(1)){
                    recyclerAdapter.setLoading(true);
                    page++;
                    getMoreData(page,1);
                }
            }
        });
        getMoreData(1,1);
        return view;
    }
    /**
     * 获取页面数据
     */
    public void getMoreData(int nPage, final int state) {
        GankService gankService = ServiceFactory.getInstance().createSerivice(GankService.class);
        gankService.getBoon(nPage)
                .subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求结果
                .subscribe(new Observer<BoonBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(BoonBean value) {
                        List<String> list=new ArrayList<String>();
                        List<BoonBean.BoonResultBoon> boonResultBoon=value.getResults();
                        for(BoonBean.BoonResultBoon v:boonResultBoon){
                            list.add(v.getUrl());
                        }
                        if(state==1){
                            recyclerAdapter.setLoading(false);
                            recyclerAdapter.addAll(list);
                            return;
                        }
                        page=1;
                        recyclerAdapter.reFresh(list);
                        mRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.v("error","获取数据错误");
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

}
