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
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import model.Provider;

/**
 *
 * @author relu
 */
public class ProviderDao {
    private Connection connection;

    public ProviderDao(Connection connection) {
        this.connection = connection;
    }
    
    public List<Provider> getProviders() throws SQLException {
        List<Provider> providers_list = new ArrayList<>();
        String command = "select * from providers";
        
        try(PreparedStatement statement = connection.prepareStatement(command)) {
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Provider provider = new Provider(
                        rs.getInt("id"),
                        rs.getString("provider_name"),
                        rs.getString("phone_number"),
                        rs.getString("email")
                );
                providers_list.add(provider);
            }
        
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return providers_list;
    }
    
    public boolean addProvider(Provider provider) throws SQLException {
        String command1 = "select * from providers where provider_name = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(command1)) {
            statement.setString(1, provider.getProvider_name());
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                JOptionPane.showMessageDialog(null, "This provider already exists.");
                return false;
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        String command2 = "insert into providers value(? , ? , ?, null)";
        try(PreparedStatement statement = connection.prepareStatement(command2)) {
            statement.setString(1, provider.getProvider_name());
            statement.setString(2, provider.getPhone_number());
            statement.setString(3, provider.getEmail());
            statement.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    public void updateProvider(int id, Provider provider) throws SQLException {
        Provider aux_provider = new Provider();
        String command1 = "Select * from providers where id = ?";
        String command2 = "update providers set provider_name = ?, "
                + "phone_number = ?, email =? where id = ?";
        try(PreparedStatement statement = connection.prepareStatement(command1)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                aux_provider.setId(rs.getInt("id"));
                aux_provider.setProvider_name(rs.getString("provider_name"));   
                aux_provider.setPhone_number(rs.getString("phone_number"));      
                aux_provider.setEmail(rs.getString("email"));    
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        if(!provider.getProvider_name().equals(aux_provider.getProvider_name()) ||
            !provider.getPhone_number().equals(aux_provider.getPhone_number()) ||
            !provider.getEmail().equals(aux_provider.getEmail())){
            try(PreparedStatement statement = connection.prepareStatement(command2)){
                statement.setString(1, provider.getProvider_name());
                statement.setString(2, provider.getPhone_number());
                statement.setString(3, provider.getEmail());
                statement.setInt(4, id);
                statement.executeUpdate();
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
        } 
    }
    
    public void deleteProvider(String provider_name) throws SQLException {
        String command = "delete from providers where provider_name = ?";
        try(PreparedStatement statement = connection.prepareStatement(command)) {
            statement.setString(1, provider_name);
            statement.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
    

    
}
