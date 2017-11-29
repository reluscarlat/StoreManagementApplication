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
    private int id;
    private String store_name,
            departament_name;

    public StoreDepartament(int id, String store_name, String departament_name) {
        this.id = id;
        this.store_name = store_name;
        this.departament_name = departament_name;
    }

    public StoreDepartament() {
    
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getDepartament_name() {
        return departament_name;
    }

    public void setDepartament_name(String departament_name) {
        this.departament_name = departament_name;
    }
    
    
}
