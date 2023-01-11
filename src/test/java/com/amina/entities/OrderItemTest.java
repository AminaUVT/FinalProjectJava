package com.amina.entities;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.sql.Date;
import java.time.LocalDateTime;

public class OrderItemTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public OrderItemTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(OrderItemTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void test() {
        OrderItem x = new OrderItem();
        x.setOrderitem_id(1);
        x.setOrder_id(101);
        x.setProduct_id(23);
        x.setCustomer_id(101);
        x.setCreated_at(Date.valueOf(LocalDateTime.now().toLocalDate()));
        x.setQuantity(99);
        x.setUnit_price(1);
        assertEquals(
                x.toString(),
                String.format(
                        "Book(ORDERITEM_ID=1,order_id=101,product_id=23,customer_id=101,created_at=%s,unit_price=1.0,quantity=99)",
                        Date.valueOf(LocalDateTime.now().toLocalDate()).toString()
                )
        );
    }
}