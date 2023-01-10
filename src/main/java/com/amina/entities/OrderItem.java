package com.amina.entities;

import java.sql.Date;

public class OrderItem extends Entity  {
    private int orderitem_id;
    // foreign keys:
    private int order_id;
    private int product_id;
    private int customer_id;
    private Date created_at;
    private int quantity;
    private float unit_price;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(float unit_price) {
        this.unit_price = unit_price;
    }

    public int getOrderitem_id() {
        return orderitem_id;
    }

    public void setOrderitem_id(int orderitem_id) {
        this.orderitem_id = orderitem_id;
    }

    @Override
    public String toString() {
        return String.format("Book(" +
                "ORDERITEM_ID=%s" +
                "order_id=%s" +
                "product_id=%s," +
                "customer_id=%s," +
                "created_at=%s," +
                "unit_price=%s," +
                "quantity=%s" +
                ")", order_id, product_id, customer_id, created_at, unit_price, quantity);
    }

}
