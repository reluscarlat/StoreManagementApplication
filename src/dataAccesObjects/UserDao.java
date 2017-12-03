/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccesObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class UserDao {
    private Connection connection;
    
    public UserDao(Connection connection) {
        this.connection = connection;
    }
    
    public boolean addUser(User user) throws SQLException {
        String select_command = "select * from users where username = ?";
        try(PreparedStatement statement = connection.prepareStatement(select_command)) {
            statement.setString(1, user.getUsername());
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                return false;
            }
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        
        String command = "insert into users values (null, ?, ?, ?, ?,?)";
        try(PreparedStatement statement = connection.prepareStatement(command)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPass());
            statement.setString(3, user.getEmploy_name());
            statement.setString(4, user.getEmploy_first_name());
            statement.setString(5, user.getRole());
            statement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    public List<User> searchUsers( User user) throws SQLException {
        List<User> users_list = new ArrayList<>();
        String username = user.getUsername();
        String employ_name = user.getEmploy_name();
        String employ_first_name = user.getEmploy_first_name();
        String role = user.getRole();
        String c1, c2, c3, c4;
        int i = 0;
        int n;
        
        if(username.equals("")) {
            c1 = "username";
        } else {
            c1 = "?";
            ++i;
        }
        if(employ_name.equals("")) {
            c2 ="employ_name";
        } else {
            c2 = "?";
            ++i;
        }
        if(employ_first_name.equals("")) {
            c3 = "employ_first_name";
        } else {
            c3 = "?";
            ++i;
        }
        if(role.equals("")) {
            c4 = "role";
        } else {
            c4 = "?";
            ++i;
        }        
        n = i+1;
        
        String command = "select * from users where username = " + c1 + " and employ_name = " + c2 + " and "
                + "employ_first_name = " + c3 + " and role = " + c4 + " ";
        try(PreparedStatement statement = connection.prepareStatement(command)){
            if(c1.equals("?")) statement.setString(n-(i--), username);
            if(c2.equals("?")) statement.setString(n-(i--), employ_name);
            if(c3.equals("?")) statement.setString(n-(i--), employ_first_name);
            if(c4.equals("?")) statement.setString(n-(i--), role);
            ResultSet rs = statement.executeQuery();    
            while(rs.next()) {
                User user2 = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("pass"),
                        rs.getString("employ_name"),
                        rs.getString("employ_first_name"),
                        rs.getString("role")
                );
                users_list.add(user2);
            }          
        }catch(Exception e) {
            e.printStackTrace();
        }  
        return users_list;
    }
    
    public List<User> getUsers() throws SQLException {
        List<User> users_list = new ArrayList<>();
        String command = "select * from users";
        
        try(PreparedStatement statement = connection.prepareStatement(command);
            ResultSet rs = statement.executeQuery();
            ) {
            while(rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("pass"),
                        rs.getString("employ_name"),
                        rs.getString("employ_first_name"),
                        rs.getString("role")
                );
                users_list.add(user);
            }          
        }catch(Exception e) {
            e.printStackTrace();
        }
        
        return users_list;
    }
    
    public void deleteUser(String username) throws SQLException {
        String command = "delete from users where username = ?";
        try(PreparedStatement statement = connection.prepareStatement(command)) {
            statement.setString(1, username);
            statement.executeUpdate();
        } catch(SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
