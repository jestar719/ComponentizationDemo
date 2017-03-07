package cn.jestar.common.utils;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by jestar on 17-3-6.
 */

public class LogUtils {
    private LogUtils() {
    }

    public static void init(boolean debug, int methodCount, String packageName) {
        Logger.init(packageName).logLevel(debug ? LogLevel.FULL : LogLevel.NONE).methodCount(methodCount);
    }

    public static void i(String msg, Object... args) {
        Logger.i(msg, args);
    }

    public static void i(String msg) {
        Logger.i(msg);
    }

    public static void d(String msg, Object... args) {
        Logger.i(msg, args);
    }

    public static void d(String msg) {
        Logger.i(msg);
    }

    public static void e(String msg, Object... args) {
        Logger.e(msg, args);
    }

    public static void e(Throwable e, String msg) {
        Logger.e(e, msg);
    }

    public static void logJson(String log) {
        Logger.json(log);
    }
}
