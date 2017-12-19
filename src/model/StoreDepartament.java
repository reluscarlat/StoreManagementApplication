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
public class StoreDepartament {
    int id;
    private String departament_name,
            departament_abbreviation,
            store_name,
            phone_number,
            email;

    public StoreDepartament() {
    
    }
    
    public StoreDepartament(int id, String departament_name, String store_name) {
        this.id = id;
        this.departament_name = departament_name;
        this.store_name = store_name;
    }
    
    public StoreDepartament(int id, String departament_name, String departament_abbreviation, String store_name, String phone_number, String email) {
        this.id = id;
        this.departament_name = departament_name;
        this.departament_abbreviation = departament_abbreviation;
        this.store_name = store_name;
        this.phone_number = phone_number;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getDepartament_name() {
        return departament_name;
    }

    public void setDepartament_name(String departament_name) {
        this.departament_name = departament_name;
    }

    public String getDepartament_abbreviation() {
        return departament_abbreviation;
    }

    public void setDepartament_abbreviation(String departament_abbreviation) {
        this.departament_abbreviation = departament_abbreviation;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
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
