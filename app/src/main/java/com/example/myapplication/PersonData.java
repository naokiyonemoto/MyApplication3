package com.example.myapplication;

import java.io.Serializable;

public class PersonData implements Serializable {

    private String name;
    private String subName;
    private String phoneNumber;
    private String email;
    private String company;

    public PersonData(){}

    public PersonData(String name, String subName, String phoneNumber, String email, String company){
        this.name = name;
        this.subName = subName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

}
