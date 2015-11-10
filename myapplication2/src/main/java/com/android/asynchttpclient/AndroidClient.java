package com.android.asynchttpclient;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by Song on 2015/10/12.
 */
public class AndroidClient {
    public final static String BASE_URL = "http://10.0.3.2:8087/androidcloud/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    /**
     * get 方式请求服务器
     *
     * @param url             相对路径 (BookJsonServlet)
     * @param params          发送到服务器的表单数据
     * @param responseHandler 回调处理响应
     */
    public static void get(String url, RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params,
                            AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        // http://10.0.3.2:8087/androidcloud/BookJsonServlet
        return BASE_URL + relativeUrl;
    }
}
