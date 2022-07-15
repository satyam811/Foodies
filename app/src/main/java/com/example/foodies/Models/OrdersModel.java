package com.example.foodies.Models;

public class OrdersModel {

    int orderImaage;
    String soldItemName, price, orderNumber;

    public OrdersModel() {
        this.orderImaage = orderImaage;
        this.soldItemName = soldItemName;
        this.price = price;
        this.orderNumber = orderNumber;
    }

    public int getOrderImaage() {
        return orderImaage;
    }

    public void setOrderImaage(int orderImaage) {
        this.orderImaage = orderImaage;
    }

    public String getSoldItemName() {
        return soldItemName;
    }

    public void setSoldItemName(String soldItemName) {
        this.soldItemName = soldItemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
