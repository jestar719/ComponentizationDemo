package cn.jestar.projectlibrary.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by jestar on 17-3-6.
 */

public class AbsBasePresent<T extends BaseMVP.BaseView> implements BaseMVP.BasePresent<T> {
    private Reference<T> mReference;

    @Override
    public void bind(T view) {
        mReference = new WeakReference<>(view);
    }

    @Override
    public void unBind() {
        if (mReference != null) {
            mReference.clear();
            mReference = null;
        }
    }

    protected T getView() {
        return mReference.get();
    }
}
