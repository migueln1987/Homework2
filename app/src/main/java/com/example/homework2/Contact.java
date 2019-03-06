package com.example.homework2;

import java.io.Serializable;
import java.util.Date;

public class Contact implements Serializable {
    private String name;
    private String occupation;
    private String age;
    private String address;
    private String email;
    private String phone;
    private Date freelance;

    public Contact(String name, String occupation, String age, String address, String phone, Date freelance) {
        this.name = name;
        this.occupation = occupation;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.freelance = freelance;
    }

    public Contact(String name, String occupation,String age, String address, String email,  String phone) {
        this.name = name;
        this.occupation = occupation;
        this.age = age;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public Contact() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }


    public String getAge() {
        return age;
    }


    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }


    public String getPhone() {
        return phone;
    }


    public Date getFreelance() {
        return freelance;
    }


    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFreelance(Date freelance) {
        this.freelance = freelance;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", occupation='" + occupation + '\'' +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", freelance=" + freelance +
                '}';
    }
}
