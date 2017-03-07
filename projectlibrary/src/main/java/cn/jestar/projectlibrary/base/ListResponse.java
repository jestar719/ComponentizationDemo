package cn.jestar.projectlibrary.base;

import java.util.List;

/**
 * Created by jestar on 17-3-5.
 */

public class ListResponse extends BaseResponse {
    private List<BaseModel> results;

    public void setResults(List<BaseModel> results) {
        this.results = results;
    }

    public List<BaseModel> getResult() {
        return results;
    }
}
