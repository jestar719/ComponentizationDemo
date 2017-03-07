package cn.jestar.projectlibrary.rxjava;

import cn.jestar.projectlibrary.base.BaseResponse;
import cn.jestar.projectlibrary.base.ResponseException;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by jestar on 17-3-5.
 */

public class RxUtils {

    public static <T extends BaseResponse> Observable.Transformer<T, T> getDefault() {
        return new ResponseTransformer<>();
    }


    private static class CheckResponseFunction<T extends BaseResponse> implements Func1<T, Observable<T>> {
        @Override
        public Observable<T> call(T t) {
            if (t.isError()) {
                return Observable.error(new ResponseException(t));
            }
            return Observable.just(t);
        }
    }

    private static class ResponseTransformer<T extends BaseResponse> implements Observable.Transformer<T, T> {
        @Override
        public Observable<T> call(Observable<T> upstream) {
            return upstream.subscribeOn(Schedulers.io())
                    .flatMap(new CheckResponseFunction<T>())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }
}
