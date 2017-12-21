/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author relu
 */
public class Employee {
    private String first_name,
            last_name;
    private String cnp,
            series,
            nbr,
            job_title;
    private double salary;
    private Date employment_data;
    private String departament_name,
            store_name;

    public Employee(String first_name, String last_name, String cnp, String serie, String nbr, String job_title, double salary, Date employment_data, String departament_name, String store_name) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.cnp = cnp;
        this.series = serie;
        this.nbr = nbr;
        this.job_title = job_title;
        this.salary = salary;
        this.employment_data = employment_data;
        this.departament_name = departament_name;
        this.store_name = store_name;
    }

    public Employee() {
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setEmployee_last_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getSeries() {
        return series;
    }

    public void setSerie(String serie) {
        this.series = serie;
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
