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
import model.Store;
import model.User;



/**
 *
 * @author relu
 */
public class StoreDao {
    private Connection connection;
    
    public StoreDao(Connection connection) {
        this.connection = connection;
    }
    
    public boolean addStore(Store store) {
        String command = "Select * from store where store_name = ?";
        try(PreparedStatement statement = connection.prepareStatement(command)){
            statement.setString(0, store.getStore_name());
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                return false;
            }
        } catch(Exception e){
            e.printStackTrace();
            return false;
        } 
        String command2 = "Insert into stores values(null , ? , ? , ? , ?)";
        try(PreparedStatement statement = connection.prepareStatement(command)){
            statement.setString(0, store.getStore_name());
            statement.setString(1, store.getAddress());
            statement.setString(2, store.getPhone_number());
            statement.setString(3, store.getEmail());
            statement.executeUpdate();
        } catch(Exception e){
            e.printStackTrace();
            return false;
        } 
        return true;
    }
    
    
    public List<Store> getStores() throws SQLException {
        List<Store> stores_list = new ArrayList<>();
        String command = "select * from stores";      
        
        try(PreparedStatement statement = connection.prepareStatement(command);
            ResultSet rs = statement.executeQuery();
            ) {
            while(rs.next()) {
                Store store = new Store(
                        rs.getInt("store_id"),
                        rs.getString("store_name"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("email")
                );
                stores_list.add(store);
            }          
        }catch(Exception e) {
            e.printStackTrace();
        }        
        return stores_list;
    }
    
    public void deleteStore(String store_name) throws SQLException {
        String command = "delete from stores where store_name = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(command)) {
            statement.setString(1, store_name);
            statement.executeUpdate();
        } catch(SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateStores(int store_id,String store_name, String address, String phone_number, String email) throws SQLException{
        List<Store> stores = new ArrayList();
        String command1 = "Select * from stores where store_id = ?";
        String command2 = "Update stores set store_name = ? , address = ? , phone_number =? ,"
                + "email = ? where store_id = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(command1)) {
            statement.setInt(1, store_id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Store store = new Store(
                    rs.getInt("store_id"),
                    rs.getString("store_name"),
                    rs.getString("address"),
                    rs.getString("phone_number"),
                    rs.getString("email")
                );
                stores.add(store);
            }
        } catch(SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String db_store_name = stores.get(0).getStore_name();
        String db_address = stores.get(0).getAddress();
        String db_phone_number = stores.get(0).getPhone_number();
        String db_email = stores.get(0).getEmail();
        
        if(!db_store_name.equals(store_name) || !db_address.equals(address) || !db_phone_number.equals(phone_number)
                || !db_email.equals(email) ) {
            try(PreparedStatement statement = connection.prepareStatement(command2)){
                statement.setString(1, store_name);
                statement.setString(2, address);
                statement.setString(3, phone_number);
                statement.setString(4, email);
                statement.setInt(5, store_id);
                statement.executeUpdate();
            }
        }   
    }
}
