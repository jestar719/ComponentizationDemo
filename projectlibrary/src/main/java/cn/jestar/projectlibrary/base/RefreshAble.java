package cn.jestar.projectlibrary.base;

import java.util.List;

/**
 * Created by jestar on 17-3-6.
 */

public interface RefreshAble<T> {
    void onGetNew(List<T> list);

    void onLoadMore(List<T> list, boolean isEnd);
}
