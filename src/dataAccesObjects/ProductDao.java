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
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import model.Product;
import model.Provider;
import service.ProviderServices;

/**
 *
 * @author relu
 */
public class ProductDao {
    private Connection connection;
    
    public ProductDao(Connection connection) {
        this.connection = connection;
    }
    
    public boolean addProduct(Product product) throws SQLException {
        String select_command = "select * from products where product_name = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(select_command)) {
            statement.setString(1, product.getProduct_name());
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                JOptionPane.showMessageDialog(null,"This product already exists.");
                return false;  
            } 
        } catch(Exception e) { 
            e.printStackTrace();
            return false;
        }
        
        ProviderServices providerService = ProviderServices.getInstance();
        List<String> providers_names = providerService.getProviders().stream()
            .map( s -> s.getProvider_name())
            .collect(Collectors.toList());
        if(!providers_names.contains(product.getProvider_name())){
            JOptionPane.showMessageDialog(null,"This provider doesn't exists in your database.");
            return false; 
        }
        String command = "insert into products values (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try(PreparedStatement statement = connection.prepareStatement(command)) {
            statement.setString(1, product.getProduct_name());
            statement.setString(2, product.getCategory());
            statement.setString(3, product.getProvider_name());
            statement.setString(4, product.getDescription());
            statement.setInt(5, product.getNbr_of_products());
            statement.setDouble(6, product.getPurchase_price());
            statement.setDouble(7, product.getSelling_price());
            statement.setString(8, product.getStore_name());
            statement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        
        String command2 = "insert into stores_products values(null, ?, ?)";
        
        try(PreparedStatement statement = connection.prepareStatement(command2)) {
            statement.setString(1, product.getProduct_name());
            statement.setString(2, product.getStore_name());
            statement.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
        
    public List<Product> getProducts() throws SQLException {
        List<Product> products_list = new ArrayList<>();
        String command = "Select * from products ";      
        
        try(PreparedStatement statement = connection.prepareStatement(command);
            ResultSet rs = statement.executeQuery();
            ) {
            while(rs.next()) {
                Product product = new Product(
                        rs.getString("product_name"),
                        rs.getString("categori"),
                        rs.getString("provider_name"),
                        rs.getString("description"),
                        rs.getInt("nbr_of_products"),
                        rs.getDouble("purchase_price"),
                        rs.getDouble("selling_price"),
                        rs.getString("store_name")
                );
                products_list.add(product);
            }          
        }catch(Exception e) {
            e.printStackTrace();
        }        
        return products_list;
    }
       
    public void deleteProduct(String product_name) throws SQLException {
        String command = "delete from products where product_name = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(command)) {
            statement.setString(1, product_name);
            statement.executeUpdate();
        } catch(SQLException ex) {
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateProduct(String old_product_name, Product product) throws SQLException{
        
        String command = "Update products set product_name = ? , categori = ? , provider_name = ? ,"
                + "description = ?, nbr_of_products = ?, purchase_price = ?, selling_price = ?, store_name = ?"
                + " where old_product_name = ?";
        
        try(PreparedStatement statement = connection.prepareStatement(command)) {
            statement.setString(1, product.getProduct_name());
            statement.setString(2, product.getCategory());
            statement.setString(3, product.getProvider_name());
            statement.setString(4, product.getDescription());
            statement.setInt(5, product.getNbr_of_products());
            statement.setDouble(6, product.getPurchase_price());
            statement.setDouble(7, product.getSelling_price());
            statement.setString(8, product.getStore_name());
            statement.setString(9, old_product_name);
            statement.executeUpdate();   
        } catch(SQLException ex) {
            Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, null, ex);
        }         
    }
}
