package com.example.micha.sendingrest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by micha on 2/7/2018.
 */

public class OkHttpHelper {
    private static final String TAG = OkHttpHelper.class.getSimpleName() ;
    private String BASE_URL;
    private Handler handler;
    OkHttpClient client;
    Request request;

    public OkHttpHelper(String base_url, Handler handler) {
        BASE_URL = base_url;
        this.handler = handler;
        init();
    }

    private void init() {
        client = new OkHttpClient();
        request = new Request.Builder().url(BASE_URL).build();
    }

    public void getResponceAsync(){

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "getResponceAsync: I don't understand");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String answer = response.body().string();
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("data",answer);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        });
    }


}
