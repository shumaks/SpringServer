package com.shumak.common.clients;

public class Client {
    private long id;
    private String surname;
    private String name;
    private String patr;
    private String phone;

    public Client(long id, String surname, String name, String patr, String phone) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patr = patr;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
