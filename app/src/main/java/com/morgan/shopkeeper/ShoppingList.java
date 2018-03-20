package com.morgan.shopkeeper;

/**
 * Created by Morgan on 20/03/2018.
 */

public class ShoppingList {
    public String itemName;
    private String itemPrice;
    private String itemShop;
    private double total;

    public ShoppingList() {
        this.itemName = "";
        this.itemPrice = "Left";
        this.itemShop = "Yes";
        this.total = 0.0;
    }

    public ShoppingList(String ItemName, String ItemPrice, String ItemShop, double Total) {
        this.itemName = ItemName;
        this.itemPrice = ItemPrice;
        this.itemShop = ItemShop;
        this.total = Total;

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemShop() {
        return itemShop;
    }

    public void setItemShop(String itemShop) {
        this.itemShop = itemShop;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total += total;
    }

    @Override
    public String toString () {
        return "ShoppingList{" +
                "Item='" + itemName + '\'' +
                "Price='" + itemPrice + '\'' +
                '}';
    }
}