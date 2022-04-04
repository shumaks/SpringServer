package com.shumak.common.sales;

import com.shumak.common.auto.Auto;
import com.shumak.common.clients.Client;
import com.shumak.common.employees.Employee;

public class SaleForm {
    private String date;
    private Employee employee;
    private Client client;
    private Auto auto;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }
}
