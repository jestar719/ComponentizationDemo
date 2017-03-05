package cn.jestar.common.image;

import android.widget.ImageView;

import java.io.File;

/**
 * Created by jestar on 17-3-5.
 */

public interface ImageLoaderStrategy {
    void loadImage(ImageView view, String url, ImageOptions options);

    void loadFile(ImageView view, File file, ImageOptions options);
}
