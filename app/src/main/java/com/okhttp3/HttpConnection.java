package com.okhttp3;

/**
 * OkHttp3 Connection Class
 */

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpConnection {

    private OkHttpClient client;
    private static HttpConnection instance = new HttpConnection();
    public static HttpConnection getInstance() {
        return instance;
    }

    private HttpConnection(){
        this.client = new OkHttpClient();
    }

    /* Send A Request To Web Server */
    public void requestWebServer(Callback callback) {
        Request request = new Request.Builder()
                .url("http://10.0.2.2:3000/lottery")
                .build();
        client.newCall(request).enqueue(callback);
    }
}
