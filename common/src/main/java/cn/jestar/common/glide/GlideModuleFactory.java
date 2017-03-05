package cn.jestar.common.glide;

import android.content.Context;

import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;

import java.io.InputStream;

import cn.jestar.common.net.NetCore;
import okhttp3.OkHttpClient;

/**
 * Created by jestar on 17-3-5.
 */

class GlideModuleFactory implements com.bumptech.glide.load.model.ModelLoaderFactory<com.bumptech.glide.load.model.GlideUrl, java.io.InputStream> {
    @Override
    public ModelLoader<GlideUrl, InputStream> build(Context context, GenericLoaderFactory factories) {
        NetCore.init(context);
        return new OkHttpLoader(NetCore.getOkHttpClient());
    }

    @Override
    public void teardown() {

    }

    private class OkHttpLoader implements ModelLoader<GlideUrl, InputStream> {

        private final OkHttpClient mOkHttpClient;

        public OkHttpLoader(OkHttpClient okHttpClient) {
            mOkHttpClient = okHttpClient;
        }

        @Override
        public DataFetcher<InputStream> getResourceFetcher(GlideUrl model, int width, int height) {
            return new OkHttpStreamFetcher(mOkHttpClient, model);
        }
    }
}
