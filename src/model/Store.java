/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author relu
 */
public class Store {
    private int store_id;
    private String store_name,
            address,
            phone_number,
            email;

    public Store(int store_id, String store_name, String address, String phone_number, String email) {
        this.store_id = store_id;
        this.store_name = store_name;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
    }

    public Store() {
    
    }
    
    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }
    
    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
