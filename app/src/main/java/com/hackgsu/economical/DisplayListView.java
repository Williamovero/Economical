package com.hackgsu.economical;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class DisplayListView extends AppCompatActivity {

    String json_string;
//    String json_string2;
    JSONObject jsonObject;
//    JSONObject jsonObject2;
    JSONArray jsonArray;
//    JSONArray jsonArray2;
    FoodAdapter foodAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);
//        MainActivity.parseJSON(R.layout.display_listview_layout);
        listView = findViewById(R.id.listview);

        foodAdapter = new FoodAdapter(this, R.layout.row_layout);
        listView.setAdapter(foodAdapter);
        json_string = Objects.requireNonNull(getIntent().getExtras()).getString("json_data");
//        json_string2 = getIntent().getExtras().getString("json_data2");
        try {
            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("snapshot");
            String item;
//            jsonObject2 = new JSONObject(json_string2);
//            jsonArray2 = jsonObject.getJSONArray("snapshot");

            //while (count < jsonArray.length()) {
                JSONObject JO = jsonArray.getJSONObject(0);//count);
                JSONArray JA = JO.getJSONObject("shortDescription").getJSONArray("values");
                JSONObject JO2 = JA.getJSONObject(1);
                item = JO2.getString("value");

//                JSONObject JO3 = jsonArray2.getJSONObject(count);
//                price = JO3.getString("price");

                Food food = new Food(item, "0");

                foodAdapter.add(food);

                //count++;

            //}
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
