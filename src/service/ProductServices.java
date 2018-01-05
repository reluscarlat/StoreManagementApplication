/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dataAccesObjects.EmployeeDao;
import dataAccesObjects.ProductDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author relu
 */
public class ProductServices {
    private String url = "jdbc:mysql://localhost/storedatabase";
    private String pass = "";
    private String user = "root";
    private Connection connection;
    private static ProductServices singletone = null;
    
    private ProductServices() {
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch(SQLException ex) {
            Logger.getLogger(ProductServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ProductServices getInstance(){
        if(singletone == null) {
            singletone = new ProductServices();
        }
        return singletone;
    }
    
    public List<Product> getProducts() {
        ProductDao productDao = new ProductDao(this.connection);
        try{
            return productDao.getProducts();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public boolean addProduct(Product product) {
        boolean verify = false;
        ProductDao productDao = new ProductDao(this.connection);
        try{
            verify = productDao.addProduct(product);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return verify;
    }
    
    public void deleteProduct(String product_name) {
        ProductDao productDao = new ProductDao(this.connection);
        try{
            productDao.deleteProduct(product_name);
        } catch(SQLException ex) {
            Logger.getLogger(ProductServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateProduct(String old_product_name, Product product) {
        ProductDao productDao = new ProductDao(this.connection);
        try{
            productDao.updateProduct(old_product_name, product);
        } catch(SQLException ex) {
            Logger.getLogger(ProductServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
