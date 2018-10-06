package com.hackgsu.economical;


public class Food {
    private String item;
    private String price;

    Food(String item, String price) {
        this.setItem(item);
        this.setPrice(price);
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    String getPrice() {
        return price;
    }

    private void setPrice(String price) {
        this.price = price;
    }

    }