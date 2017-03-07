package cn.jestar.girl;

import java.util.List;

import cn.jestar.projectlibrary.base.BaseMVP;
import cn.jestar.projectlibrary.base.BaseModel;
import cn.jestar.projectlibrary.base.RefreshAble;

/**
 * Created by jestar on 17-3-6.
 */

interface GirlContact {
    interface GirlView extends BaseMVP.BaseView, RefreshAble<BaseModel> {
        void onLoadMore(List<BaseModel> list, boolean isLoadEnd);

        void onGetNew(List<BaseModel> list);
    }

    interface GirlPresent extends BaseMVP.BasePresent<GirlView> {
        void getNew();

        void loadMore();
    }
}
