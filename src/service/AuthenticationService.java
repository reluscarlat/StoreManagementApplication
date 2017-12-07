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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author relu
 */
public class AuthenticationService {
    private String url = "jdbc:mysql://localhost/storedatabase";
    private String pass = "";
    private String user = "root";
    private Connection connection;
    
    private AuthenticationService() {
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(MainService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static AuthenticationService singletone = null;
    
    public static AuthenticationService getInstance(){
        if(singletone == null) {
            singletone = new AuthenticationService();
        }
        return singletone;
    }
    
    public boolean testCredentials(String username, String password, String role) {
        UserDao userDao = new UserDao(connection);
        List<User> users_list = new ArrayList<User>();
        try {
            users_list = userDao.getUsers();
        } catch (SQLException ex) {
            Logger.getLogger(AuthenticationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        int n = users_list.size();
        for(int i = 0 ; i < n ; i++) {
            if(username.equals(users_list.get(i).getUsername())
                    && password.equals(users_list.get(i).getPass()) 
                    && role.equals(users_list.get(i).getRole())) {
                return true;
            }
        }
        return false;
    }
}
