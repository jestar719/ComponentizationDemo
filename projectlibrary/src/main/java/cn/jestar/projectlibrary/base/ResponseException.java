package cn.jestar.projectlibrary.base;

/**
 * Created by jestar on 17-3-5.
 */

public class ResponseException extends RuntimeException {
    public <T extends BaseResponse> ResponseException(T t) {
    }
}
