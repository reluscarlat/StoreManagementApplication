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
public class StoreProduct {
    private int id;
    private String product_name,
            store_name;

    public StoreProduct(int id, String product_name, String store_name) {
        this.id = id;
        this.product_name = product_name;
        this.store_name = store_name;
    }
    
    public StoreProduct(){
    
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }
    
    
}
