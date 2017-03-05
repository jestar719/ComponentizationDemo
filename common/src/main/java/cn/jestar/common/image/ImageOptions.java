package cn.jestar.common.image;

/**
 * Created by jestar on 17-3-5.
 */

public class ImageOptions {
    public static final int EMPTY = -1;
    private int errorResId = EMPTY;
    private int placeHolderResId = EMPTY;
    private Resize resize;

    public int getErrorResId() {
        return errorResId;
    }

    public ImageOptions setErrorResId(int errorResId) {
        this.errorResId = errorResId;
        return this;
    }

    public int getPlaceHolderResId() {
        return placeHolderResId;
    }

    public ImageOptions setPlaceHolderResId(int placeHolderResId) {
        this.placeHolderResId = placeHolderResId;
        return this;
    }

    public Resize getResize() {
        return resize;
    }

    public ImageOptions setResize(Resize resize) {
        this.resize = resize;
        return this;
    }
}
