package com.example.micha.sendingrest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements Handler.Callback {

    private static final String TAG = MainActivity.class.getSimpleName();
    private OkHttpHelper helper;
    private TextView meat;
    private TextView salsa;
    private TextView shell;
    private TextView cheese;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler = new Handler(this);
        meat = findViewById(R.id.okMeat);
        cheese = findViewById(R.id.okCheese);
        shell = findViewById(R.id.okShell);
        salsa = findViewById(R.id.okSalsa);
        String url = "http://www.mocky.io/v2/5a7b45953000004b3128be54";
        helper = new OkHttpHelper(url, handler);
    }

    @Override
    public boolean handleMessage(Message message) {
        String data = message.getData().getString("data");
        JSONObject json = null;
        Iterator<String> jsonList = null;
        String[] array = new String[4];
        String[] keys = new String[4];
        try {
            json = new JSONObject(data);
            jsonList = json.getJSONObject("Taco").keys();
            int i = 0;
            while(jsonList.hasNext()){
                String key = jsonList.next();
                keys[i] = key;
                array[i] = (String) json.getJSONObject("Taco").get(key);
                i++;
            }
            meat.setText(keys[0]+" : "+array[0]);
            cheese.setText(keys[1]+" : "+array[1]);
            shell.setText(keys[2]+" : "+array[2]);
            salsa.setText(keys[3]+" : "+array[3]);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void getResponse(View view) {
        helper.getResponceAsync();
    }
}
