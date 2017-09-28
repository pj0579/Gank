package com.github.xu.gank.base;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by xukankan on 2017/9/25.
 */

public class BaseActivity extends AppCompatActivity {
    /**
     * 简化findViewById,使用$代替
     * @param resId
     * @param <T>
     * @return
     */
    public <T extends View> T $(int resId) {
        return (T) super.findViewById(resId);
    }
}
