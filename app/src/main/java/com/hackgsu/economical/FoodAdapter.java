package com.hackgsu.economical;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends ArrayAdapter {
    List list = new ArrayList();

    FoodAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
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
            foodHolder.tx_item = (TextView) row.findViewById(R.id.tx_item);
            foodHolder.tx_price = (TextView) row.findViewById(R.id.tx_price);
            row.setTag(foodHolder);
        } else {
            foodHolder = (FoodHolder) row.getTag();
        }

        Food food = (Food) this.getItem(position);
        foodHolder.tx_item.setText(food.getItem());
        foodHolder.tx_price.setText(food.getPrice());

        return row;
    }

    static class FoodHolder {
        TextView tx_item, tx_price;
    }

}
