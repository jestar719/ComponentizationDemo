package cn.jestar.projectlibrary.base;

import android.app.Application;
import android.support.annotation.CallSuper;

import com.alibaba.android.arouter.launcher.ARouter;
import com.orhanobut.logger.Logger;

import cn.jestar.common.image.ImageManager;
import cn.jestar.common.net.NetCore;
import cn.jestar.common.utils.ContextUtils;
import cn.jestar.projectlibrary.glide.GlideLoaderStrategy;

/**
 * Created by jestar on 17-3-5.
 */

public class BaseApplication extends Application {

    @CallSuper
    @Override
    public void onCreate() {
        super.onCreate();
        ContextUtils.init(this);
        Logger.init("Jestar").methodCount(2);
        NetCore.init(this);
        ImageManager.init(new GlideLoaderStrategy(), null);
        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);
    }
}
