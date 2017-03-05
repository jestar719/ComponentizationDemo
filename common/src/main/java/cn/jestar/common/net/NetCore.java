package cn.jestar.common.net;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jestar on 17-3-5.
 */

public class NetCore {
    private static final int CONNECT_TIMEOUT = 5;
    private static final int IO_TIMEOUT = 5;
    private static final long NET_CACHE_SIZE = 1024 * 1024 * 10;
    private static OkHttpClient sOkHttpClient;
    private static Retrofit sRetrofit;

    private NetCore() {
    }

    public static void init(Context context) {
        if (sOkHttpClient != null)
            return;
        OkHttpClient.Builder okHttpBuilder = getOkHttpBuilder(context);
        sOkHttpClient = okHttpBuilder.build();
        sRetrofit = getRetrofit(NetConst.BASE_URL);
    }

    public static OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            throw new RuntimeException("Net is unInit");
        }
        return sOkHttpClient;
    }

    @NonNull
    private static OkHttpClient.Builder getOkHttpBuilder(Context context) {
        return new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
                .cache(new Cache(context.getCacheDir(), NET_CACHE_SIZE));
    }


    public static Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static <T> T getService(Retrofit retrofit, Class<T> c) {
        return retrofit.create(c);
    }

    public static <T> T getService(Class<T> c) {
        return sRetrofit.create(c);
    }

}
