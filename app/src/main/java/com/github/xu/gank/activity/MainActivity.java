package com.github.xu.gank.activity;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.xu.gank.R;
import com.github.xu.gank.base.BaseActivity;
import com.github.xu.gank.bean.BoonBean;
import com.github.xu.gank.api.GankService;
import com.github.xu.gank.fragment.DemoFrament;
import com.github.xu.gank.adapter.FragAdapter;
import com.github.xu.gank.fragment.ImageFragment;
import com.github.xu.gank.fragment.ListFragment;
import com.github.xu.gank.fragment.MineFragment;
import com.github.xu.gank.service.ServiceFactory;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends BaseActivity {
    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    private BottomNavigationView bottomNavigationView;
    private ViewPager mainViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = $(R.id.bottom_navigation);
        mainViewpager = $(R.id.main_viewpager);
        setNavigationListener();
        setUpViewpager();
    }


    /**
     * 设置变化监听，转换fragment
     */
    public void setNavigationListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.action_image:
                        mainViewpager.setCurrentItem(0,false);
                       break;
                    case R.id.action_all:
                        mainViewpager.setCurrentItem(1,false);
                        break;
                    case R.id.action_demo:
                        mainViewpager.setCurrentItem(2,false);
                        break;
                    case R.id.action_mime:
                        mainViewpager.setCurrentItem(3,false);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }



    public void setUpViewpager() {
        List<Fragment> listFragment=new ArrayList<Fragment>();
        listFragment.add(new ImageFragment());
        listFragment.add(new ListFragment());
        listFragment.add(new DemoFrament());
        listFragment.add(new MineFragment());
        FragAdapter fragAdapter=new FragAdapter(getSupportFragmentManager(),listFragment);
        mainViewpager.setAdapter(fragAdapter);
        mainViewpager.setOffscreenPageLimit(4);//设置缓存4个fragment，防止切换重新加载
        mainViewpager.setCurrentItem(0);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
}
