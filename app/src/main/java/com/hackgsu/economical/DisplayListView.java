package com.hackgsu.economical;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DisplayListView extends AppCompatActivity {

//    String json_string;
//    //    String json_string2;
//    JSONObject jsonObject;
//    //    JSONObject jsonObject2;
//    JSONArray jsonArray;
//    //    JSONArray jsonArray2;
//    FoodAdapter foodAdapter;
//    ListView listView;

    private String myResponse;
    //private FoodAdapter foodAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] foods = {"Bacon", "Ham", "Tuna", "Candy", "Meatball", "Potato"};
        // Replace the Array adapter with your custom adapter.
        // ListAdapter theListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, foods);
//        ListAdapter customListAdapter = new FoodAdapter(DisplayListView.this,foods);// Pass the food arrary to the constructor.
//        ListView customListView = (ListView) findViewById(R.id.listview);
//        ListAdapter customListAdapter = new FoodAdapter(1);
//        customListView.setAdapter(customListAdapter);

//        customListView.setOnItemClickListener(
//                new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        String food = String.valueOf(parent.getItemAtPosition(position));
//                        Toast.makeText(DisplayListView.this, food, Toast.LENGTH_LONG).show();
//                    }
//                }
//        );


//        listView = findViewById(R.id.listview);
////        food = (String[]) new Object[10];
//        String food[] = {"it works", "apple", "carrot"};
//        ListAdapter foodAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, food);
//        listView.setAdapter(foodAdapter);
//        listView.setOnClickListener(
//                new AdapterView.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//
//                }
//        );

        //foodAdapter = new FoodAdapter(this, R.layout.row_layout);
        //listView.setAdapter(foodAdapter);


//        listView = findViewById(R.id.listview);
//        foodAdapter = new FoodAdapter(this, R.layout.row_layout);
//        listView.setAdapter(foodAdapter);
//        json_string = Objects.requireNonNull(getIntent().getExtras()).getString("json_data");
////        json_string2 = getIntent().getExtras().getString("json_data2");
//        try {
//
//            JSONObject jsonObject = new JSONObject(json_string);
//
//            JSONArray jsonArray = jsonObject.getJSONArray("snapshot");
//            jsonObject = jsonArray.getJSONObject(0);
//            jsonObject = jsonObject.getJSONObject("shortDescription");
//            jsonArray = jsonObject.getJSONArray("values");
//            jsonObject = jsonArray.getJSONObject(0);
//            //jsonObject = jsonObject.getJSONObject("value");
//            String item = jsonObject.getString("value");
//            Food food = new Food(item, "0");
//
//            foodAdapter.add(food);
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }


        //        Start of HTTP Request
        //mTextViewResult = findViewById(R.id.listview);

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
                    myResponse = response.body().string();

                    try {
                        JSONObject jsonObject = new JSONObject(myResponse);
                        JSONArray jsonArray = jsonObject.getJSONArray("snapshot");
                        jsonObject = jsonArray.getJSONObject(0);
                        jsonObject = jsonObject.getJSONObject("shortDescription");
                        jsonArray = jsonObject.getJSONArray("values");
                        jsonObject = jsonArray.getJSONObject(0);
                        //jsonObject = jsonObject.getJSONObject("value");
                        String item = jsonObject.getString("value");
                        //Food food = new Food(item, "0");
                        //foodAdapter.add(food);
                        //food[0] = item;
                        //ListAdapter foodAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, food);


                    } catch (Exception JSONException) {

                    }
                }
            }
        });
    }
//        ----------------------------------------------------------
}

