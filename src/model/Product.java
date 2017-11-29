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
public class Product {
    private String product_name,
            categori,
            provider_name,
            description,
            store_name;
    private int nbr_of_products;
    private double purchase_price,
            selling_price;

    public Product(String product_name, String categori, String provider_name, String description, String store_name, int nbr_of_products, double purchase_price, double selling_price) {
        this.product_name = product_name;
        this.categori = categori;
        this.provider_name = provider_name;
        this.description = description;
        this.store_name = store_name;
        this.nbr_of_products = nbr_of_products;
        this.purchase_price = purchase_price;
        this.selling_price = selling_price;
    }
    
    public Product() {
    
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getCategori() {
        return categori;
    }

    public void setCategori(String categori) {
        this.categori = categori;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public int getNbr_of_products() {
        return nbr_of_products;
    }

    public void setNbr_of_products(int nbr_of_products) {
        this.nbr_of_products = nbr_of_products;
    }

    public double getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(double purchase_price) {
        this.purchase_price = purchase_price;
    }

    public double getSelling_price() {
        return selling_price;
    }

    public void setSelling_price(double selling_price) {
        this.selling_price = selling_price;
    }
    
    
    
}
