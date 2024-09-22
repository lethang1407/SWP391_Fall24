package model;

import java.util.ArrayList;

public class Capacity {

    private int capacity_id;
    private int value;
    private ArrayList<Product> products = new ArrayList<>();

    public int getCapacity_id() {
        return capacity_id;
    }

    public int getValue() {
        return value;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setCapacity_id(int capacity_id) {
        this.capacity_id = capacity_id;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

}
