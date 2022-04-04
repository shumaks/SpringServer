package com.shumak.common.employees;

public class Employee {
    private long id;
    private String surname;
    private String name;
    private String patr;
    private String position;
    private String address;
    private String phone;

    public Employee(long id, String surname, String name, String patr, String position, String address, String phone) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patr = patr;
        this.position = position;
        this.address = address;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatr() {
        return patr;
    }

    public void setPatr(String patr) {
        this.patr = patr;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
