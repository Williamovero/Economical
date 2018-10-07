package com.hackgsu.economical;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodAdapter extends ArrayAdapter {
    private ArrayList<Object> list = new ArrayList<>();

    FoodAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(@Nullable Object object) {
        list.add(object);
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row = convertView;
        FoodHolder foodHolder;
        if (row == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout, parent, false);

            foodHolder = new FoodHolder();
            foodHolder.tx_item = row.findViewById(R.id.tx_item);
            foodHolder.tx_price = row.findViewById(R.id.tx_price);
            row.setTag(foodHolder);
        } else {
            foodHolder = (FoodHolder) row.getTag();
        }

        Food food = (Food) this.getItem(position);
        assert food != null;
        foodHolder.tx_item.setText(food.getItem());
        foodHolder.tx_price.setText(food.getPrice());

        return row;
    }

    static class FoodHolder {
        TextView tx_item, tx_price;
    }

}
