package cn.jestar.common.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by jestar on 17-3-6.
 */

public class ToastUtils {
    private static Toast sToast;

    static {
        Context context = ContextUtils.getContext();
        sToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
    }


    public static void shortMsg(CharSequence msg) {
        showMsg(msg, Toast.LENGTH_SHORT);
    }

    private static void showMsg(CharSequence msg, int time) {
        sToast.setDuration(time);
        sToast.setText(msg);
        sToast.show();
    }

    private static void showMsg(@StringRes int msg, int time) {
        sToast.setDuration(time);
        sToast.setText(msg);
        sToast.show();
    }

    public static void longMsg(CharSequence msg) {
        showMsg(msg, Toast.LENGTH_LONG);
    }

    public static void longMsg(@StringRes int msgId) {
        showMsg(msgId, Toast.LENGTH_LONG);
    }

    public static void shortMsg(@StringRes int msgId) {
        showMsg(msgId, Toast.LENGTH_SHORT);
    }
}
