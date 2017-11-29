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
public class User {
    private int user_id;
    private String username,
            pass,
            employ_name,
            employ_first_name,
            role;
    private enum Role{
        admin,normal
    }
    
    public User() {
    
    }
    
    public User(int user_id, String username, String pass, String employ_name, String employ_first_name, String role) {
        this.user_id = user_id;
        this.username = username;
        this.pass = pass;
        this.employ_name = employ_name;
        this.employ_first_name = employ_first_name;
        this.role = role;
    }
    
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmploy_name() {
        return employ_name;
    }

    public void setEmploy_name(String employ_name) {
        this.employ_name = employ_name;
    }

    public String getEmploy_first_name() {
        return employ_first_name;
    }

    public void setEmploy_first_name(String employ_first_name) {
        this.employ_first_name = employ_first_name;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role.toString();
    }
    
    @Override
    public String toString() {
        String result = this.user_id + "  " +
                this.username + "  " +
                this.pass + "  " +
                this.employ_name + "  " + 
                this.employ_first_name;
        return result;
    }
}
