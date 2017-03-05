package cn.jestar.common.image;

/**
 * Created by jestar on 17-3-5.
 */

public class Resize {
    private final int width;
    private final int height;

    public Resize(int width, int height) {
        this.width = width > 0 ? width : 0;
        this.height = height > 0 ? height : 0;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
