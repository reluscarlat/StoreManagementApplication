/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dataAccesObjects.AddressDao;
import dataAccesObjects.EmployeeDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;

/**
 *
 * @author relu
 */
public class EmployeeServices {
    private String url = "jdbc:mysql://localhost/storedatabase";
    private String pass = "";
    private String user = "root";
    private Connection connection;
    private static EmployeeServices singletone = null;
    
    private EmployeeServices() {
        try {
            connection = DriverManager.getConnection(url, user, pass);
        } catch(SQLException ex) {
            Logger.getLogger(EmployeeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static EmployeeServices getInstance(){
        if(singletone == null) {
            singletone = new EmployeeServices();
        }
        return singletone;
    }
    
    public List<Employee> getEmployees() {
        EmployeeDao employeeDao = new EmployeeDao(this.connection);
        try{
            return employeeDao.getEmployees();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public List<Object[]> getEmployeesWithAddresses() {
        EmployeeDao employeeDao = new EmployeeDao(this.connection);
        try{
            return employeeDao.getEmployeesWithAddresses();
        } catch (Exception e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    
    public boolean addEmployee (Employee employee) {
        boolean verify = false;
        EmployeeDao employeeDao = new EmployeeDao(this.connection);
        try{
            verify = employeeDao.addEmployee(employee);
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return verify;
    }
    
    public void deleteEmployeet(String first_name, String last_name) {
        EmployeeDao employeeDao = new EmployeeDao(this.connection);
        try{
            employeeDao.deleteEmployee(first_name, last_name);
        } catch(SQLException ex) {
            Logger.getLogger(EmployeeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateEmployee(String first_name, String last_name, Employee employee) {
        EmployeeDao employeeDao = new EmployeeDao(this.connection);
        try{
            employeeDao.updateEmployee(first_name, last_name, employee);
        } catch(SQLException ex) {
            Logger.getLogger(EmployeeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
