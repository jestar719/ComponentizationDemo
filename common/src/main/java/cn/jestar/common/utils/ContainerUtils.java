package cn.jestar.common.utils;

import java.util.Collection;

/**
 * Created by jestar on 17-3-5.
 */

public class ContainerUtils {
    private ContainerUtils() {
    }

    public static int getSize(Collection container) {
        return container == null ? 0 : container.size();
    }

    public static <T> int getSize(T[] container) {
        return container == null ? 0 : container.length;
    }

    public static boolean isEmpty(Collection list) {
        return list == null || list.isEmpty();
    }

}
