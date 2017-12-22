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
import model.Departament;

/**
 *
 * @author relu
 */
public class DepartamentDao {
    private Connection connection;
    
    public DepartamentDao(Connection connection) {
        this.connection = connection;
    }
    
    public boolean addDepartament(Departament departament) throws SQLException {
        String select_command = "select * from departaments where departament_name = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(select_command)) {
            statement.setString(1, departament.getDepartament_name());
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                JOptionPane.showMessageDialog(null,"This departament already exists.");
                return false;  
            } 
        } catch(Exception e) { 
            e.printStackTrace();
            return false;
        }
        
        String command = "insert into departaments values (null, ?, ?, ?)";
        
        try(PreparedStatement statement = connection.prepareStatement(command)) {
            statement.setString(1, departament.getDepartament_name());
            statement.setString(2, departament.getDepartament_abbreviation());
            statement.setString(3, departament.getDescription());
            statement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public List<Departament> getDepartaments() throws SQLException {
        List<Departament> departaments_list = new ArrayList<>();
        String command = "Select * from departaments order by id ";      
        
        try(PreparedStatement statement = connection.prepareStatement(command);
            ResultSet rs = statement.executeQuery();
            ) {
            while(rs.next()) {
                Departament departament = new Departament(
                        rs.getInt("id"),
                        rs.getString("departament_name"),
                        rs.getString("description"),
                        rs.getString("departament_abbreviation")
                );
                departaments_list.add(departament);
            }          
        }catch(Exception e) {
            e.printStackTrace();
        }        
        return departaments_list;
    }
        
    public void deleteDepartament(String departament_name) throws SQLException {
        String command = "delete from departaments where departament_name = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(command)) {
            statement.setString(1, departament_name);
            statement.executeUpdate();
        } catch(SQLException ex) {
            Logger.getLogger(DepartamentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateDepartament(int id,String departament_name, String description, String departament_abbreviation) throws SQLException{
        List<Departament> departaments = new ArrayList();
        String command1 = "Select * from departaments where id = ?";
        String command2 = "Update departaments set departament_name = ? , description = ? , departament_abbreviation = ? "
                + " where id = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(command1)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Departament departament = new Departament(
                    rs.getInt("id"),
                    rs.getString("departament_name"),
                    rs.getString("description"),
                    rs.getString("departament_abbreviation")
                );
                departaments.add(departament);
            }
        } catch(SQLException ex) {
            Logger.getLogger(DepartamentDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String db_departament_name = departaments.get(0).getDepartament_name();
        String db_departament_abbreviation = departaments.get(0).getDepartament_abbreviation();
        String db_description = departaments.get(0).getDescription();
        
        
        if(!db_departament_name.equals(departament_name) || !db_departament_abbreviation.equals(departament_abbreviation)
                || !db_description.equals(description)) {
            try(PreparedStatement statement = connection.prepareStatement(command2)){
                statement.setString(1, departament_name);
                statement.setString(2, description);
                statement.setString(3, departament_abbreviation);
                statement.setInt(4, id);
                statement.executeUpdate();
            } catch(SQLException ex) {
                Logger.getLogger(DepartamentDao.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }   
    }
}
