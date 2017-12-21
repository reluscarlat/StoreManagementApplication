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
import javax.swing.JOptionPane;
import model.Employee;

/**
 *
 * @author relu
 */
public class EmployeeDao {
        private Connection connection;
    
    public EmployeeDao(Connection connection) {
        this.connection = connection;
    }
    
    public boolean addEmployee(Employee employee) throws SQLException {
        String select_command = "select * from employees where employee_first_name = ? and employee_last_name = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(select_command)) {
            statement.setString(1, employee.getFirst_name());
            statement.setString(1, employee.getLast_name());
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                JOptionPane.showMessageDialog(null,"This employee already exists.");
                return false;  
            } 
        } catch(Exception e) { 
            e.printStackTrace();
            return false;
        }
        
        String command = "insert into employees values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try(PreparedStatement statement = connection.prepareStatement(command)) {
            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setString(3, employee.getCnp());
            statement.setString(4, employee.getSeries());
            statement.setString(5, employee.getNbr());
            statement.setString(6, employee.getJob_title());
            statement.setDouble(7, employee.getSalary());
            statement.setDate(8,employee.getEmployment_data());
            statement.setString(9, employee.getDepartament_name());
            statement.setString(10, employee.getStore_name());
            statement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public List<Employee> getEmployees() throws SQLException {
        List<Employee> employees_list = new ArrayList<>();
        String command = "Select * from employees ";      
        
        try(PreparedStatement statement = connection.prepareStatement(command);
            ResultSet rs = statement.executeQuery();
            ) {
            while(rs.next()) {
                Employee employee = new Employee(
                        rs.getString("employee_first_name"),
                        rs.getString("employee_last_name"),
                        rs.getString("cnp"),
                        rs.getString("series"),
                        rs.getString("nbr"),
                        rs.getString("job_title"),
                        rs.getDouble("salary"),
                        rs.getDate("employment_data"),
                        rs.getString("departament_name"),
                        rs.getString("store_name")
                );
                employees_list.add(employee);
            }          
        }catch(Exception e) {
            e.printStackTrace();
        }        
        return employees_list;
    }
    
    public List<Object[]> getEmployeesWithAddresses() {
        List<Object[]> join_list = new ArrayList<Object[]>();
        Object [] row = new Object[17];
        String command = "select employees.* , employee_addresses.country , employee_addresses.state, employee_addresses.district, \n" +
        "employee_addresses.city_or_village, employee_addresses.street, employee_addresses.address_number, employee_addresses.mansion\n" +
        "   from employees join employee_addresses \n" +
        "       where employees.employee_first_name = employee_addresses.employee_first_name ;\n";
        try(PreparedStatement statement = connection.prepareStatement(command);
            ResultSet rs = statement.executeQuery();
            ) {
            while(rs.next()) {
                row[0] = rs.getString("employee_first_name");
                row[1] = rs.getString("employee_last_name");
                row[2] = rs.getString("cnp");
                row[3] = rs.getString("series");
                row[4] = rs.getString("nbr");
                row[5] = rs.getString("job_title");
                row[6] = rs.getDouble("salary");
                row[7] = rs.getDate("employment_data");
                row[8] = rs.getString("departament_name");
                row[9] = rs.getString("store_name");
                row[10] = rs.getString("country");
                row[11] = rs.getString("state");
                row[12] = rs.getString("district");
                row[13] = rs.getString("city_or_village");
                row[14] = rs.getString("street");
                row[15] = rs.getInt("address_number");
                row[16] = rs.getString("mansion");
                join_list.add(row);
            }          
        }catch(Exception e) {
            e.printStackTrace();
        }        
        return join_list;
    }
    
    
    public void deleteEmployee(String employee_first_name, String employee_last_name) throws SQLException {
        String command = "delete from employees where employee_first_name = ? and employee_last_name = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(command)) {
            statement.setString(1, employee_first_name);
            statement.setString(2, employee_last_name);
            statement.executeUpdate();
        } catch(SQLException ex) {
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateEmployee(String old_first_name, String old_last_name ,Employee employee) throws SQLException{
        
        String command = "Update employees set employee_first_name = ? , employee_last_name = ? , cnp = ? ,"
                + "series = ?, nbr = ?, job_title = ?, salary = ?, employment_data?, departament_name = ?, store_name = ?"
                + " where employee_first_name = ? and employee_last_name = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(command)) {
            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setString(3, employee.getCnp());
            statement.setString(4, employee.getSeries());
            statement.setString(5, employee.getNbr());
            statement.setString(6, employee.getJob_title());
            statement.setDouble(7, employee.getSalary());
            statement.setDate(8, employee.getEmployment_data());
            statement.setString(9, employee.getDepartament_name());
            statement.setString(10, employee.getStore_name());
            statement.setString(11, old_first_name);
            statement.setString(12, old_last_name);
            statement.executeUpdate();   
        } catch(SQLException ex) {
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }         
    }
}
