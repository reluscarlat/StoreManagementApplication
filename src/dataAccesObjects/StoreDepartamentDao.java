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
    
    public List<StoreDepartament> getStoreDepartamentsOrderedBy(String criteria) throws SQLException {
        List<StoreDepartament> store_departaments_list = new ArrayList<>();
        String command = "select * from \n" +
"	(select stores_departaments.id, departaments.departament_name, departaments.departament_abbreviation, stores.store_name, stores.phone_number, stores.email\n" +
"		from departaments left outer join stores_departaments on departaments.departament_name = stores_departaments.departament_name \n" +
"		left outer join stores on stores_departaments.store_name = stores.store_name) as myJoin\n" +
"			where myJoin.store_name is not null \n" +
"				order by myJoin." + criteria;      
        //int id, String departament_name, String departament_abbreviation, String store_name, String phone_number, String email
        try(PreparedStatement statement = connection.prepareStatement(command);
            ResultSet rs = statement.executeQuery();
            ) {
            while(rs.next()) {
                StoreDepartament store_departament = new StoreDepartament(
                        rs.getInt("id"),
                        rs.getString("departament_name"),
                        rs.getString("departament_abbreviation"),
                        rs.getString("store_name"),
                        rs.getString("phone_number"),
                        rs.getString("email")
                );
                store_departaments_list.add(store_departament);
            }          
        }catch(Exception e) {
            e.printStackTrace();
        }        
        return store_departaments_list;
    }
    
    public boolean addStoreDepartament(StoreDepartament storeDep) throws SQLException {
        String select_command = "select * from stores_departaments where store_name = ? and departament_name = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(select_command)) {
            statement.setString(1, storeDep.getStore_name());
            statement.setString(2, storeDep.getDepartament_name());
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                JOptionPane.showMessageDialog(null, "This departament already exists, please enter other departament name or store name.");
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
    
    public void deleteStoreDepartament(String departament_name, String store_name) throws SQLException {
        String command = "delete from stores_departaments where departament_name = ? and store_name = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(command)) {
            statement.setString(1, departament_name);
            statement.setString(2, store_name);
            statement.executeUpdate();
        } catch(SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateStoreDepartament(int id,String store_name, String departament_name) throws SQLException{
        List<StoreDepartament> storeDeps = new ArrayList();
        String command1 = "Select * from stores_departaments where id = ?";
        String command2 = "Update stores_departaments set store_name= ? , departament_name = ?"
                + " where id = ?";
        String command3 = "Select count(*) as 'nb' from stores_departaments where store_name = ? and "
                + "departament_name = ?";
        boolean verify = true; // verify if the combination of store and departamnet alreay exists in table.
        
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
