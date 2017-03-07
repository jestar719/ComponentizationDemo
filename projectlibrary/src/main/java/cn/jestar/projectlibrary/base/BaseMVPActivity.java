package cn.jestar.projectlibrary.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

import butterknife.ButterKnife;
import cn.jestar.common.base.BaseActivity;

public abstract class BaseMVPActivity<T extends BaseMVP.BasePresent> extends BaseActivity implements BaseMVP.BaseView {
    protected T mPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mPresent = initPresent();
        mPresent.bind(this);
        init();
    }

    @NonNull
    protected abstract T initPresent();

    protected abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresent.unBind();
    }
}
