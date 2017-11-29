/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author relu
 */
public class Employee {
    private String employee_name,
            employee_first_name;
    private char [] cnp = new char[13];
    private String serie,
            nbr,
            job_title;
    private double salary;
    private Date employment_data;
    private int address_id;
    private String departament_name,
            store_name;

    public Employee(String employee_name, String employee_first_name, String serie, String nbr, String job_title, double salary, Date employment_data, int address_id, String departament_name, String store_name) {
        this.employee_name = employee_name;
        this.employee_first_name = employee_first_name;
        this.serie = serie;
        this.nbr = nbr;
        this.job_title = job_title;
        this.salary = salary;
        this.employment_data = employment_data;
        this.address_id = address_id;
        this.departament_name = departament_name;
        this.store_name = store_name;
    }
    
    public Employee() {
    
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_first_name() {
        return employee_first_name;
    }

    public void setEmployee_first_name(String employee_first_name) {
        this.employee_first_name = employee_first_name;
    }

    public char[] getCnp() {
        return cnp;
    }

    public void setCnp(char[] cnp) {
        this.cnp = cnp;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNbr() {
        return nbr;
    }

    public void setNbr(String nbr) {
        this.nbr = nbr;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getEmployment_data() {
        return employment_data;
    }

    public void setEmployment_data(Date employment_data) {
        this.employment_data = employment_data;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getDepartament_name() {
        return departament_name;
    }

    public void setDepartament_name(String departament_name) {
        this.departament_name = departament_name;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }
    
}
