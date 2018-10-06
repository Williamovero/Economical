package com.hackgsu.economical;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String json_string;
    String json_string2;
    private TextView mTextViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewResult = findViewById(R.id.text_view_result);

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
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    json_string = myResponse;
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextViewResult.setText(myResponse);
                        }
                    });
                }
            }
        });
    }
}

public void parseJSON (View view) {
    if (json_string == null) {
        Toast.makeText(getApplicationContext(), "No Food Available", Toast.LENGTH_SHORT).show();
    } else {
        Intent intent = new Intent(this, DisplayListView.class);
        intent.putExtra("json_data",json_string);
        intent.putExtra("json_price",json_string2); //get this data!!
        startActivity(intent);
    }
}