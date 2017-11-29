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
public class SoldProducts {
    private int id;
    private String product_name;
    private double nbr_of_products;
    private int bill_id;
    
    public SoldProducts() {
    
    }

    public SoldProducts(int id, String product_name, double nbr_of_products, int bill_id) {
        this.id = id;
        this.product_name = product_name;
        this.nbr_of_products = nbr_of_products;
        this.bill_id = bill_id;
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

    public double getNbr_of_products() {
        return nbr_of_products;
    }

    public void setNbr_of_products(double nbr_of_products) {
        this.nbr_of_products = nbr_of_products;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }
    
    
}
