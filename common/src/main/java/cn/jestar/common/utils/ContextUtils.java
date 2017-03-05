package cn.jestar.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

/**
 * Created by jestar on 17-3-5.
 */

public class ContextUtils {

    private static final String SP_NAME = "sp";
    private static Context sContext;

    private ContextUtils() {
    }

    public static void init(Context context) {
        sContext = context;
    }

    public static Context getContext() {
        return sContext;
    }

    public static Resources getResouce() {
        return sContext.getResources();
    }


    public static SharedPreferences getSharedPreferences() {
        return sContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }
}
