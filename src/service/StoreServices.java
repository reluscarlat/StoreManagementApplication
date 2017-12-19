/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dataAccesObjects.StoreDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Store;

/**
 *
 * @author relu
 */
public class StoreServices {
    private String url = "jdbc:mysql://localhost/storedatabase";
    private String pass = "";
    private String user = "root";
    private Connection connection;
    private static StoreServices singletone = null;
    
    private StoreServices() {
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch(SQLException ex) {
            Logger.getLogger(StoreServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static StoreServices getInstance(){
        if(singletone == null) {
            singletone = new StoreServices();
        }
        return singletone;
    }
    
    public List<Store> getStores() {
        StoreDao storeDao = new StoreDao(this.connection);
        try{
            return storeDao.getStores();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public boolean addStore (String store_name,
            String address,
            String phone_number,
            String email) {
        boolean verify = false;
        Store store = new Store(123, store_name, address, 
                phone_number, email);
        StoreDao storeDao = new StoreDao(this.connection);
        try{
            verify = storeDao.addStore(store);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return verify;
    }

    public void deleteStore(String store_name) {
        StoreDao storeDao = new StoreDao(this.connection);
        try{
            storeDao.deleteStore(store_name);
        } catch(SQLException ex) {
            Logger.getLogger(StoreServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateStore(int store_id,String store_name, String address, String phone_number, String email) {
        StoreDao storeDao = new StoreDao(this.connection);
        try{
            storeDao.updateStores(store_id, store_name, address, phone_number, email);
        } catch(SQLException ex) {
            Logger.getLogger(StoreServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
