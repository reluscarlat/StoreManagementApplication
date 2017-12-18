/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dataAccesObjects.DepartamentDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Departament;

/**
 *
 * @author relu
 */
public class DepartamentService {
    private String url = "jdbc:mysql://localhost/storedatabase";
    private String pass = "";
    private String user = "root";
    private Connection connection;
    private static DepartamentService singletone = null;
    
    private DepartamentService() {
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch(SQLException ex) {
            Logger.getLogger(StoreService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DepartamentService getInstance(){
        if(singletone == null) {
            singletone = new DepartamentService();
        }
        return singletone;
    }
    
    public List<Departament> getDepartaments() {
        DepartamentDao departamentDao = new DepartamentDao(this.connection);
        try{
            return departamentDao.getDepartaments();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public boolean addDepartament (String departament_name,
            String abbreviation,
            String description) {
        boolean verify = false;
        Departament departament = new Departament( 1, departament_name, abbreviation, 
                description);
        DepartamentDao departamentDao = new DepartamentDao(this.connection);
        try{
            verify = departamentDao.addDepartament(departament);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return verify;
    }
    
    public void deleteDepartament(String departament_name) {
        DepartamentDao departamentDao = new DepartamentDao(this.connection);
        try{
            departamentDao.deleteDepartament(departament_name);
        } catch(SQLException ex) {
            Logger.getLogger(StoreService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateDepartament(int id,String departament_name, String description, String departament_abbreviation) {
        DepartamentDao departamentDao = new DepartamentDao(this.connection);
        try{
            departamentDao.updateDepartament(id, departament_name, description, departament_abbreviation);
        } catch(SQLException ex) {
            Logger.getLogger(StoreService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
