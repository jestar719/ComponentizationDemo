package cn.jestar.girl;

import cn.jestar.projectlibrary.base.ListResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by jestar on 17-3-5.
 */

interface GirlService {
    @GET("data/福利/20/{page}")
    Observable<ListResponse> getListModel(@Path("page") int page);
}
