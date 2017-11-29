/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author relu
 */
public class FiscalBill {
    private int id;
    private Date release_data;
    private Time release_time;
    private String store_name;
    private int bill_number;
    private double total_value;

    public FiscalBill(int id, Date release_data, Time release_time, String store_name, int bill_number, double total_value) {
        this.id = id;
        this.release_data = release_data;
        this.release_time = release_time;
        this.store_name = store_name;
        this.bill_number = bill_number;
        this.total_value = total_value;
    }

    public FiscalBill() {
    
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRelease_data() {
        return release_data;
    }

    public void setRelease_data(Date release_data) {
        this.release_data = release_data;
    }

    public Time getRelease_time() {
        return release_time;
    }

    public void setRelease_time(Time release_time) {
        this.release_time = release_time;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public int getBill_number() {
        return bill_number;
    }

    public void setBill_number(int bill_number) {
        this.bill_number = bill_number;
    }

    public double getTotal_value() {
        return total_value;
    }

    public void setTotal_value(double total_value) {
        this.total_value = total_value;
    }
    
}
