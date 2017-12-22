/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dataAccesObjects.AddressDao;
import dataAccesObjects.DepartamentDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Address;


/**
 *
 * @author relu
 */
public class AddressServices {
    private String url = "jdbc:mysql://localhost/storedatabase";
    private String pass = "";
    private String user = "root";
    private Connection connection;
    private static AddressServices singletone = null;
    
    private AddressServices() {
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch(SQLException ex) {
            Logger.getLogger(AddressServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static AddressServices getInstance(){
        if(singletone == null) {
            singletone = new AddressServices();
        }
        return singletone;
    }
    
    public List<Address> getAddresses() {
        AddressDao addressDao = new AddressDao(this.connection);
        try{
            return addressDao.getAddressess();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public boolean addAddress (Address address) {
        boolean verify = false;
        AddressDao addressDao = new AddressDao(this.connection);
        try{
            verify = addressDao.addAddress(address);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return verify;
    }
    
    public void deleteAddress(String first_name, String last_name) {
        AddressDao addressDao = new AddressDao(this.connection);
        try{
            addressDao.deleteAddress(first_name, last_name);
        } catch(SQLException ex) {
            Logger.getLogger(AddressServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateAddress(String first_name, String last_name, Address address) {
        AddressDao addressDao = new AddressDao(this.connection);
        try{
            addressDao.updateAddress(first_name, last_name, address);
        } catch(SQLException ex) {
            Logger.getLogger(AddressServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
