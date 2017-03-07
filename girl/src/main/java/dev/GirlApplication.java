package dev;

import com.alibaba.android.arouter.launcher.ARouter;
import com.orhanobut.logger.Logger;

import cn.jestar.common.image.ImageManager;
import cn.jestar.common.net.NetCore;
import cn.jestar.projectlibrary.base.BaseApplication;
import cn.jestar.projectlibrary.glide.GlideLoaderStrategy;

/**
 * Created by jestar on 17-3-6.
 */

public class GirlApplication extends BaseApplication {
    private static final String TAG = "GIRL";

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init(TAG).methodCount(2);
        NetCore.init(this);
        ImageManager.init(new GlideLoaderStrategy(), null);
        ARouter.openDebug();
        ARouter.openLog();
        ARouter.init(this);
    }
}
