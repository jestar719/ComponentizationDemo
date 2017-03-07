package cn.jestar.projectlibrary.rxjava;

import cn.jestar.projectlibrary.base.BaseMVP;
import rx.Subscriber;

/**
 * Created by jestar on 17-3-6.
 */

public abstract class SubscriberImpl<T> extends Subscriber<T> {
    BaseMVP.BaseView view;

    public SubscriberImpl() {
    }

    public SubscriberImpl(BaseMVP.BaseView view) {
        this.view = view;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (view != null) {
            view.onError(e.getMessage());
        }
    }

}
