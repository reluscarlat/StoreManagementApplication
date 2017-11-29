/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author relu
 */
public class Departament {
    private String departament_name,
            description,
            departament_abbreviation;

    public Departament(String departament_name, String description, String departament_abbreviation) {
        this.departament_name = departament_name;
        this.description = description;
        this.departament_abbreviation = departament_abbreviation;
    }

    public Departament() {
    
    }
    
    public String getDepartament_name() {
        return departament_name;
    }

    public void setDepartament_name(String departament_name) {
        this.departament_name = departament_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartament_abbreviation() {
        return departament_abbreviation;
    }

    public void setDepartament_abbreviation(String departament_abbreviation) {
        this.departament_abbreviation = departament_abbreviation;
    }
    
    
}
