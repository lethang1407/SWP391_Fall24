/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author KEISHA
 */
public class Product {
    private int product_id;
    private String name;
    private BigDecimal price;
    private Date date;
    private int stock;
    private Discount discount;
    private Brand brand;
    private boolean status;
    private ArrayList<Capacity> capacity;
    private ArrayList<Gender> gender;
    private Employee_User emp;
    private ArrayList<Image> img;
    private ArrayList<Feedback> fb;
    private ArrayList<Item> items;
    private ArrayList<OrderDetail> orders;
    
}
