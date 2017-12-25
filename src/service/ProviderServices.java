/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dataAccesObjects.ProviderDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Provider;

/**
 *
 * @author relu
 */
public class ProviderServices {
    private String url = "jdbc:mysql://localhost/storedatabase";
    private String pass = "";
    private String user = "root";
    private Connection connection;
    private static ProviderServices singletone = null;
    
    private ProviderServices(){
        try{
            connection = DriverManager.getConnection(url, user, pass);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static ProviderServices getInstance() {
        if(singletone == null) {
            singletone = new ProviderServices();
        }
        return singletone;
    }

    public List<Provider> getProviders() {
        ProviderDao providerDao = new ProviderDao(connection);
        try{
            return providerDao.getProviders();
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return Collections.emptyList();
    }
    
    public boolean addProvider(String provider_name, String Phone_number, String email) {
        Provider provider = new Provider(1, provider_name, Phone_number,  email);
        ProviderDao providerDao = new ProviderDao(connection);
        try{
            return providerDao.addProvider(provider);
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    
    public void updateProvider(int id, Provider provider) {
        ProviderDao providerDao = new ProviderDao(connection);
        try {
            providerDao.updateProvider(id, provider);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void deleteProvider(String provider_name){
        ProviderDao providerDao = new ProviderDao(connection);
        try{
            providerDao.deleteProvider(provider_name);
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}
