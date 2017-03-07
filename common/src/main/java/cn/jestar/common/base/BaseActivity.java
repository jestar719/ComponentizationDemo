package cn.jestar.common.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import cn.jestar.common.utils.ToastUtils;

/**
 * Created by jestar on 17-3-7.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    @LayoutRes
    protected abstract int getLayoutId();

    public void showMsg(CharSequence msg) {
        ToastUtils.shortMsg(msg);
    }

    public void showMsg(@StringRes int msgId) {
        ToastUtils.shortMsg(msgId);
    }

}
