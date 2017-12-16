/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccesObjects;

import java.sql.Connection;
import java.sql.SQLException;
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
        
        return true;
    }
}
