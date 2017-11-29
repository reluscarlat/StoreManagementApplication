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
public class EmployeeAddress {
    private int id;
    private String country,
            state,
            district,
            city_or_village,
            street;
    private int address_number;
    private String mansion;

    public EmployeeAddress(int id, String country, String state, String district, String city_or_village, String street, int address_number, String mansion) {
        this.id = id;
        this.country = country;
        this.state = state;
        this.district = district;
        this.city_or_village = city_or_village;
        this.street = street;
        this.address_number = address_number;
        this.mansion = mansion;
    }
    
    public EmployeeAddress() {
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity_or_village() {
        return city_or_village;
    }

    public void setCity_or_village(String city_or_village) {
        this.city_or_village = city_or_village;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getAddress_number() {
        return address_number;
    }

    public void setAddress_number(int address_number) {
        this.address_number = address_number;
    }

    public String getMansion() {
        return mansion;
    }

    public void setMansion(String mansion) {
        this.mansion = mansion;
    }
    
    
}
