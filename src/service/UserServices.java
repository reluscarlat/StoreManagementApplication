/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dataAccesObjects.UserDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author relu
 */
public class UserServices {
    private String url = "jdbc:mysql://localhost/storedatabase";
    private String pass = "";
    private String user = "root";
    private Connection connection;
    
    private UserServices() {
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static UserServices singletone = null;
    
    public static UserServices getInstance(){
        if(singletone == null) {
            singletone = new UserServices();
        }
        return singletone;
    }
    
    public List<User> getUsers() {
        UserDao userDao = new UserDao(this.connection);
        try{
            return userDao.getUsers();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<User> searchUsers(String username, String role, String employ_first_name, String employ_last_name){
        UserDao userDao = new UserDao(this.connection);
        try{ User user = new User(1, username, "", employ_last_name, employ_first_name,role);
            return userDao.searchUsers(user);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public boolean addUser(String username,
            String pass,
            String employ_name,
            String employ_first_name,
            String role) {
        boolean verify = false;
        User user = new User(123, username, pass, employ_name, employ_first_name, role);
        UserDao userDao = new UserDao(this.connection);
        try{
            verify = userDao.addUser(user);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return verify;
    }
    
    public void deleteUsers(String username) {
        UserDao userDao = new UserDao(this.connection);
        try{
            userDao.deleteUser(username);
        } catch(Exception ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public void updateUser(int user_id,String username, String password, String first_name, String last_name, String role) {
        UserDao userDao = new UserDao(this.connection);
        try{
            userDao.updateUser(user_id, username, password, first_name, last_name, role);
        } catch(Exception ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
