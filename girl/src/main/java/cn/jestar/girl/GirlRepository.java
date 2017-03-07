package cn.jestar.girl;

import java.util.List;

import cn.jestar.common.net.NetCore;
import cn.jestar.common.utils.ContainerUtils;
import cn.jestar.projectlibrary.base.BaseModel;
import cn.jestar.projectlibrary.base.ListResponse;
import cn.jestar.projectlibrary.rxjava.RxUtils;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by jestar on 17-3-5.
 */

class GirlRepository {
    private GirlService mGirlService;
    private int mPage = 1;

    public GirlRepository() {
        mGirlService = NetCore.getService(GirlService.class);
    }

    public Observable<List<BaseModel>> getGirls(boolean isNew) {
        if (isNew) {
            mPage = 0;
        }
        int page = mPage + 1;
        return mGirlService.getListModel(page).
                compose(RxUtils.<ListResponse>getDefault()).
                map(new Func1<ListResponse, List<BaseModel>>() {
                    @Override
                    public List<BaseModel> call(ListResponse listResponse) {
                        List<BaseModel> result = listResponse.getResult();
                        if (!ContainerUtils.isEmpty(result)) {
                            mPage++;
                        }
                        return result;
                    }
                });
    }
}
