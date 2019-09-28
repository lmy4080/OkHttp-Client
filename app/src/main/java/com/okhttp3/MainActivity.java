package com.okhttp3;

/**
 * MainActivity Functions
 * 1. Create The HttpConnection Class, Which Builds OkHttpClient Instance With Server URL
 * 2. Sends Request To Server
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "OkHttp3";
    private HttpConnection httpConn = HttpConnection.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestHttp(); // Request To WebServer
    }

    /* Request To Web Server With Get Method */
    private void requestHttp() {

        // Network Communication Must Be On A Work Thread Instead Of Main One
        new Thread() {
            public void run() {

                httpConn.requestWebServer(callback);
            }
        }.start();
    }

    /* OkHttp3 Callback */
    private final Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.d(TAG, "Callback Error:" + e.getMessage());
        }
        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String body = response.body().string();
            Log.d(TAG, "Server Response Body:" + body);
        }
    };
}
