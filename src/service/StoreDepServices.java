/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dataAccesObjects.DepartamentDao;
import dataAccesObjects.StoreDepartamentDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Departament;
import model.StoreDepartament;

/**
 *
 * @author relu
 */
public class StoreDepServices {
    private String url = "jdbc:mysql://localhost/storedatabase";
    private String pass = "";
    private String user = "root";
    private Connection connection;
    private static StoreDepServices singletone = null;
    
    private StoreDepServices() {
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch(SQLException ex) {
            Logger.getLogger(StoreDepServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static StoreDepServices getInstance(){
        if(singletone == null) {
            singletone = new StoreDepServices();
        }
        return singletone;
    }
    
    public List<StoreDepartament> getStoreDepartamentsOrderedBy(String criteria) {
        StoreDepartamentDao storeDepartamentDao = new StoreDepartamentDao(this.connection);
        try{
            return storeDepartamentDao.getStoreDepartamentsOrderedBy(criteria);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public boolean addStoreDepartament (String departament_name,
            String store_name) {
        boolean verify = false;
        StoreDepartament storeDepartament = new StoreDepartament( 1, departament_name, store_name); 
        StoreDepartamentDao storeDepartamentDao = new StoreDepartamentDao(this.connection);
        try{
            verify = storeDepartamentDao.addStoreDepartament(storeDepartament);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return verify;
    }
    
    public void deleteStoreDepartament(String departament_name, String store_name) {
        StoreDepartamentDao storeDepartamentDao = new StoreDepartamentDao(this.connection);
        try{
            storeDepartamentDao.deleteStoreDepartament(departament_name, store_name);
        } catch(SQLException ex) {
            Logger.getLogger(StoreDepServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateDepartament(int id,String departament_name, String store_name) {
        StoreDepartamentDao storeDepartamentDao = new StoreDepartamentDao(this.connection);
        try{
            storeDepartamentDao.updateStoreDepartament(id, departament_name, store_name);
        } catch(SQLException ex) {
            Logger.getLogger(DepartamentServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}

