package com.hackgsu.economical;


public class Food {
    private String item,price,location;

    public Food(String item, String price) {
        this.setItem(item);
        this.setPrice(price);
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    }