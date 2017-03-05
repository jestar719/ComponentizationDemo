package cn.jestar.common.base;

import android.app.Application;
import android.support.annotation.CallSuper;

import cn.jestar.common.utils.ContextUtils;

/**
 * Created by jestar on 17-3-5.
 */

public class BaseApplication extends Application {
    @CallSuper
    @Override
    public void onCreate() {
        super.onCreate();
        ContextUtils.init(this);
    }
}
