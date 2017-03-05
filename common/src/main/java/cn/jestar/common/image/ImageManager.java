package cn.jestar.common.image;

import android.widget.ImageView;

import java.io.File;

/**
 * Created by jestar on 17-3-5.
 */

public class ImageManager {

    private static ImageLoaderStrategy sLoader;
    private static ImageOptions sOptions;

    private ImageManager() {
    }

    public static void init(ImageLoaderStrategy imageLoaderStrategy, ImageOptions defaultOptions) {
        sLoader = imageLoaderStrategy;
        sOptions = defaultOptions;
    }

    public static void loadImage(ImageView view, String url) {
        loadImage(view, url, sOptions);

    }

    private static void loadImage(ImageView view, String url, ImageOptions options) {
        checkInit();
        sLoader.loadImage(view, url, options);
    }

    public static void loadFile(ImageView view, File file) {
        loadFile(view, file, sOptions);

    }

    private static void loadFile(ImageView view, File file, ImageOptions options) {
        checkInit();
        sLoader.loadFile(view, file, options);
    }


    private static void checkInit() {
        if (sLoader == null)
            throw new RuntimeException("ImageLoader is unInit");
    }

}
