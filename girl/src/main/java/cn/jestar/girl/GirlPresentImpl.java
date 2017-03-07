package cn.jestar.girl;

import java.util.List;

import cn.jestar.projectlibrary.base.AbsBasePresent;
import cn.jestar.projectlibrary.base.BaseModel;
import cn.jestar.projectlibrary.rxjava.SubscriberImpl;

/**
 * Created by jestar on 17-3-6.
 */

class GirlPresentImpl extends AbsBasePresent<GirlContact.GirlView> implements GirlContact.GirlPresent {

    private final GirlRepository mGirlRepository;

    GirlPresentImpl() {
        mGirlRepository = new GirlRepository();
    }

    @Override
    public void getNew() {
        mGirlRepository.getGirls(true)
                .subscribe(new SubscriberImpl<List<BaseModel>>() {
                    @Override
                    public void onNext(List<BaseModel> baseModels) {
                        getView().onGetNew(baseModels);
                    }
                });
    }

    @Override
    public void loadMore() {
        mGirlRepository.getGirls(false)
                .subscribe(new SubscriberImpl<List<BaseModel>>() {
                    @Override
                    public void onNext(List<BaseModel> baseModels) {
                        getView().onLoadMore(baseModels, baseModels == null);
                    }
                });
    }
}
