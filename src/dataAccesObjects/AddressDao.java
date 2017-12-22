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
import model.Address;

/**
 *
 * @author relu
 */
public class AddressDao {
    private Connection connection;
    
    public AddressDao(Connection connection) {
        this.connection = connection;
    }
    
    public boolean addAddress(Address address) throws SQLException {
        String select_command = "select * from employee_addresses where employee_first_name = ? and employee_last_name = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(select_command)) {
            statement.setString(1, address.getFirst_name());
            statement.setString(2, address.getLast_name());
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                return false;  
            } 
        } catch(Exception e) { 
            e.printStackTrace();
            return false;
        }
        
        String command = "insert into employee_addresses values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try(PreparedStatement statement = connection.prepareStatement(command)) {
            statement.setString(1, address.getFirst_name());
            statement.setString(2, address.getLast_name());
            statement.setString(3, address.getCountry());
            statement.setString(4, address.getState());
            statement.setString(5, address.getDistrict());
            statement.setString(6, address.getCity_or_village());
            statement.setString(7, address.getStreet());
            statement.setInt(8, address.getAddress_number());
            statement.setString(9, address.getMansion());
            statement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public List<Address> getAddressess() throws SQLException {
        List<Address> addresses_list = new ArrayList<>();
        String command = "Select * from employee_addresses ";      
        
        try(PreparedStatement statement = connection.prepareStatement(command);
            ResultSet rs = statement.executeQuery();
            ) {
            while(rs.next()) {
                Address address = new Address(
                        rs.getString("employee_first_name"),
                        rs.getString("employee_last_name"),
                        rs.getString("country"),
                        rs.getString("state"),
                        rs.getString("district"),
                        rs.getString("city_or_village"),
                        rs.getString("street"),
                        rs.getInt("address_number"),
                        rs.getString("mansion")
                );
                addresses_list.add(address);
            }          
        }catch(Exception e) {
            e.printStackTrace();
        }        
        return addresses_list;
    }
    
    public void deleteAddress(String employee_first_name, String employee_last_name) throws SQLException {
        String command = "delete from employee_addresses where employee_first_name = ? and employee_last_name = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(command)) {
            statement.setString(1, employee_first_name);
            statement.setString(2, employee_last_name);
            statement.executeUpdate();
        } catch(SQLException ex) {
            Logger.getLogger(AddressDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateAddress(String old_first_name, String old_last_name ,Address address) throws SQLException{
        
        String command = "Update employee_addresses set employee_first_name = ? , employee_last_name = ? , country = ? ,"
                + "state = ?, district = ?, city_or_village = ?, street = ?, address_number?, mansion = ?"
                + " where employee_first_name = ? and employee_last_name = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(command)) {
            statement.setString(1, address.getFirst_name());
            statement.setString(2, address.getLast_name());
            statement.setString(3, address.getCountry());
            statement.setString(4, address.getState());
            statement.setString(5, address.getDistrict());
            statement.setString(6, address.getCity_or_village());
            statement.setString(7, address.getStreet());
            statement.setInt(8, address.getAddress_number());
            statement.setString(9, address.getMansion());
            statement.setString(10, old_first_name);
            statement.setString(11, old_last_name);
            statement.executeUpdate();   
        } catch(SQLException ex) {
            Logger.getLogger(AddressDao.class.getName()).log(Level.SEVERE, null, ex);
        }         
    }
}
