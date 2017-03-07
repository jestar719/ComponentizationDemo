package cn.jestar.projectlibrary.base;

/**
 * Created by jestar on 17-3-6.
 */

public interface BaseMVP {
    interface BaseView {
        void showMsg(CharSequence Msg);

        void onError(CharSequence msg);
    }

    interface BasePresent<T extends BaseView> {
        void bind(T view);

        void unBind();
    }
}
