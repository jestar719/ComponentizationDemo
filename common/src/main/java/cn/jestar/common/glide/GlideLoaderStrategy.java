package cn.jestar.common.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

import java.io.File;

import cn.jestar.common.image.ImageLoaderStrategy;
import cn.jestar.common.image.ImageOptions;
import cn.jestar.common.image.Resize;

/**
 * Created by jestar on 17-3-5.
 */

public class GlideLoaderStrategy implements ImageLoaderStrategy {

    @Override
    public void loadImage(ImageView view, String url, ImageOptions options) {
        Context context = view.getContext();
        DrawableTypeRequest<String> load = Glide.with(context).load(url);
        setOptions(load, options).into(view);
    }

    @Override
    public void loadFile(ImageView view, File file, ImageOptions options) {
        DrawableTypeRequest<File> load = Glide.with(view.getContext()).load(file);
        setOptions(load, options).into(view);
    }

    private DrawableTypeRequest setOptions(DrawableTypeRequest load, ImageOptions options) {
        if (options == null) {
            return load;
        }
        int errorResId = options.getErrorResId();
        if (errorResId != ImageOptions.EMPTY) {
            load.error(errorResId);
        }
        int placeHolderResId = options.getPlaceHolderResId();
        if (placeHolderResId != ImageOptions.EMPTY) {
            load.placeholder(placeHolderResId);
        }
        Resize resize = options.getResize();
        if (resize != null) {
            load.override(resize.getWidth(), resize.getHeight());
        }
        return load;
    }
}
