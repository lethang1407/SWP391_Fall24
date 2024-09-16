/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.ArrayList;

import java.util.Date;
/**
 *
 * @author KEISHA
 */
public class Order {
    private int order_id;
    private Customer_User customer;
    private BigDecimal total_price;
    private Date create_at;
    private String status;
    private String payment_method;
    private String shipping_method;
    private ArrayList<OrderDetail> orderDetails;
    
}
