package com.hackgsu.economical;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
//    String json_string;
    private TextView mTextViewResult;
//    private String myResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        Start of HTTP Request
        final Button button = findViewById(R.id.button_parse);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View activity_main) {


                OkHttpClient client = new OkHttpClient();

                String url = "https://gateway-staging.ncrcloud.com/catalog/items/snapshot";

                Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "application/json")
                        .addHeader("nep-application-key", "8a00860b6641a0ae016646edbaa3000d")
                        .addHeader("nep-organization", "ncr-market")
                        .addHeader("nep-service-version", "2.2.1:2")
                        .addHeader("Authorization", "Basic YWNjdDppbnN0YW50aGFja3dpbm5lcnNAaW5zdGFudGhhY2t3aW5uZXJzc2VydmljZXVzZXI6T1ZlcjcwNzA=")
                        .addHeader("Cache-Control", "no-cache")
                        .addHeader("Postman-Token", "6c84ad3e-d139-4fa1-9636-b922a1566d2f")
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    //Response failed
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        e.printStackTrace();
                    }

                    //Response is successful
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            mTextViewResult = findViewById(R.id.text_view_result);
                            String myResponse = response.body().string();
                            Map<String, Integer> responses = new HashMap<>();
                            //parsing
                            try {
                                int count = 0;

                                JSONObject jsonObject = new JSONObject(myResponse);
                                JSONArray jsonArray = jsonObject.getJSONArray("snapshot");
                                while (count < jsonArray.length()) {
                                    JSONObject jsonObject2 = jsonArray.getJSONObject(count);
                                    jsonObject2 = jsonObject2.getJSONObject("shortDescription");
                                    JSONArray jsonArray2 = jsonObject2.getJSONArray("values");
                                    jsonObject2 = jsonArray2.getJSONObject(0);
                                    //jsonObject = jsonObject.getJSONObject("value");
                                    responses.put(jsonObject2.getString("value"), 0);
                                    count++;
                                }

                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            final String myresp2 = responses.keySet().toString();
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mTextViewResult.setText(myresp2);
                                }
                            });
                        }
                    }
                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main2, menu);
        return true;
    }


}

