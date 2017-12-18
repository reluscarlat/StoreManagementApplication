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
import model.StoreDepartament;


/**
 *
 * @author relu
 */
public class StoreDepartamentDao {
 private Connection connection;
    
    public StoreDepartamentDao(Connection connection) {
        this.connection = connection;
    }
    
    public boolean addDepartament(StoreDepartament storeDep) throws SQLException {
        String select_command = "select * from stores_departaments where store_name = ? and departament_name = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(select_command)) {
            statement.setString(1, storeDep.getStore_name());
            statement.setString(2, storeDep.getDepartament_name());
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                JOptionPane.showMessageDialog(null, "This departament name already exists, please enter other departament name.");
                return false;  
            } 
        } catch(Exception e) { 
            e.printStackTrace();
            return false;
        }
        
        String command = "insert into stores_departaments values (null, ?, ?)";
        
        try(PreparedStatement statement = connection.prepareStatement(command)) {
            statement.setString(1, storeDep.getStore_name());
            statement.setString(2, storeDep.getDepartament_name());
            statement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public void deleteStoreDepartament(String store_name, String departament_name) throws SQLException {
        String command = "delete from departaments where store_name = ? and departament_name = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(command)) {
            statement.setString(1, store_name);
            statement.setString(1, departament_name);
            statement.executeUpdate();
        } catch(SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateStoreDepartmanet(int id,String store_name, String departament_name) throws SQLException{
        List<StoreDepartament> storeDeps = new ArrayList();
        String command1 = "Select * from stores_departaments where id = ?";
        String command2 = "Update stores_departaments set store_name= ? , departament_name = ?"
                + " where id = ?";
        String command3 = "Select count(*) as 'nb' from stores_departaments where store_name = ? and "
                + "departament_name = ?";
        boolean verify = false;
        try(PreparedStatement statement = connection.prepareStatement(command1)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                StoreDepartament storeDep = new StoreDepartament(
                    rs.getInt("id"),
                    rs.getString("store_name"),
                    rs.getString("departament_name")
                );
                storeDeps.add(storeDep);
            }
        } catch(SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }   
        String db_store_name = storeDeps.get(0).getStore_name();
        String db_departament_name = storeDeps.get(0).getDepartament_name();     
        
        try(PreparedStatement statement = connection.prepareStatement(command3)) {
            statement.setString(1, store_name);
            statement.setString(2, departament_name);
            ResultSet rs = statement.executeQuery();
            if (rs.getInt("nb") > 1) {
                verify = false;
            }
        } catch(SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        if(verify && (!db_departament_name.equals(departament_name) || !db_store_name.equals(store_name))) {
            try(PreparedStatement statement = connection.prepareStatement(command2)){
                statement.setString(1, store_name);
                statement.setString(2, departament_name);
                statement.setInt(3, id);
                statement.executeUpdate();
            } catch(SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }   
    }    
}
