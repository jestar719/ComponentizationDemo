package cn.jestar.common.net;

import android.text.TextUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import cn.jestar.common.utils.LogUtils;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * 网络连接日志拦截器
 * Created by jestar on 16/12/13.
 */
public class OkhttpLog implements Interceptor {
    public static final String LINE = "\n";
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final String JSON = "json";
    private static final long CAN_JSON_SIZE = 1024;
    private static final String TEXT_BLANK = " ";
    public List<String> mUrlFilter = null;
    private String[] ends = new String[]{"jpg", "png", "JPG", "PNG"};

    public OkhttpLog() {
        mUrlFilter = new ArrayList<>();
        mUrlFilter.add("https://dev.taoyicai.com/");
        mUrlFilter.add("https://dev.taoyicai.com/");
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Request request = chain.request();
        String s = request.url().toString();
        boolean isReturn = filterUrl(s);
        if (isReturn) {
            request = request.newBuilder().url("https://dev.taoyicai.com/Api/Cart/getGoodsNum").build();
        } else {
            String method = request.method();
            stringBuilder.append("========>开始请求").append(LINE)
                    .append("请求方式=>").append(method).append(LINE)
                    .append("URL=>").append(s).append(LINE);
            RequestBody body = request.body();
            if (body != null) {
                Buffer buffer = new Buffer();
                body.writeTo(buffer);
                stringBuilder.append("请求体=>").append(buffer.readString(UTF8)).append(LINE);
            }
        }
        long start = System.currentTimeMillis();
        Response proceed = chain.proceed(request);
        long end = System.currentTimeMillis();
        String url = proceed.request().url().toString();
        ResponseBody body = proceed.body();
        for (String endText : ends) {
            if (url.endsWith(endText)) {
//                long l = body.contentLength();
//                long kb = l / 1000;
//                Log.i("图片", String.format("图片大小=%skb url=%s", kb, url));
                return proceed;
            }
        }
        MediaType mediaType = body.contentType();
        stringBuilder.append("========>收到响应").append(LINE)
                .append("链接=>").append(proceed.request().url()).append(LINE)
                .append(String.format("=>用时%s秒", (end - start) / 1000)).append(LINE)
                .append("状态=>").append(proceed.code()).append(TEXT_BLANK).append(proceed.message()).append(LINE)
                .append("类型=>").append(mediaType.toString()).append(LINE);
        BufferedSource source = body.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        long size = buffer.size();
        if (size > 0) {
            stringBuilder.append("响应内容大小=>");
            if (size < (10000))
                stringBuilder.append(size).append(TEXT_BLANK).append("byte");
            else
                stringBuilder.append(size / 1000f).append("Kb");
            stringBuilder.append(LINE);
        }
        stringBuilder.append("响应内容=>").append(LINE);
        Buffer clone = buffer.clone();
        String log = clone.readString(UTF8);
        if (size < CAN_JSON_SIZE && mediaType.subtype().equals(JSON)) {
            LogUtils.d(stringBuilder.toString());
            LogUtils.logJson(log);
        } else {
            stringBuilder.append(log);
            LogUtils.d(stringBuilder.toString());
        }
        return proceed;
    }

    private boolean filterUrl(String requestUrl) {
        if (TextUtils.isEmpty(requestUrl))
            return true;
        for (String url : mUrlFilter) {
            if (requestUrl.equals(url))
                return true;
        }
        return false;
    }


    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }
}
