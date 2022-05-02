package com.shumak.common.app;

import com.shumak.common.auto.Auto;
import com.shumak.common.auto.AutoForm;
import com.shumak.common.clients.Client;
import com.shumak.common.clients.ClientForm;
import com.shumak.common.employees.Employee;
import com.shumak.common.employees.EmployeeForm;
import com.shumak.common.jdbc.QueryData;
import com.shumak.common.mode.Mode;
import com.shumak.common.sales.Sale;
import com.shumak.common.sales.SaleForm;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class AndroidRestController {

    @RequestMapping("/auto/getAll")
    public List<Auto> getAutoFromAndroidClient() throws SQLException, ClassNotFoundException {
        return QueryData.getDataFromDb("table_auto", MainController.tableMap.get("table_auto"), Auto.class);
    }

    @RequestMapping(value = { "auto/updateAuto" }, method = RequestMethod.POST)
    public Auto updateAutoFromAndroidClient(@RequestBody Auto auto) throws SQLException, ClassNotFoundException {
        QueryData.updateDataInDb("table_auto", MainController.tableMap.get("table_auto"), auto);
        return auto;
    }

    @RequestMapping(value = { "auto/addAuto" }, method = RequestMethod.POST)
    public Auto addAutoFromAndroidClient(@RequestBody Auto auto) throws SQLException, ClassNotFoundException {
        AutoForm autoForm = new AutoForm();
        autoForm.setMode(auto.getMode());
        autoForm.setImage(auto.getImage());
        autoForm.setModel(auto.getModel());
        autoForm.setModelYear(auto.getModelYear());
        autoForm.setSits(auto.getSits());
        QueryData.addDataToDb("table_auto", MainController.tableMap.get("table_auto"), autoForm);
        return auto;
    }

    @RequestMapping("/mode/getAll")
    public List<Mode> getModeFromAndroidClient() throws SQLException, ClassNotFoundException {
        return QueryData.getDataFromDb("table_mode", MainController.tableMap.get("table_mode"), Mode.class);
    }

    @RequestMapping("/clients/getAll")
    public List<Client> getClientsFromAndroidClient() throws SQLException, ClassNotFoundException {
        return QueryData.getDataFromDb("table_clients", MainController.tableMap.get("table_clients"), Client.class);
    }

    @RequestMapping("/sales/getAll")
    public List<Sale> getSalesFromAndroidClient() throws SQLException, ClassNotFoundException {
        return QueryData.getDataFromDb("table_sales", MainController.tableMap.get("table_sales"), Sale.class);
    }

    @RequestMapping(value = { "sales/updateSale" }, method = RequestMethod.POST)
    public Sale updateSaleFromAndroidClient(@RequestBody Sale sale) throws SQLException, ClassNotFoundException {
        QueryData.updateDataInDb("table_sales", MainController.tableMap.get("table_sales"), sale);
        return sale;
    }

    @RequestMapping(value = { "sales/addSale" }, method = RequestMethod.POST)
    public Sale addSaleFromAndroidClient(@RequestBody Sale sale) throws SQLException, ClassNotFoundException {
        SaleForm saleForm = new SaleForm();
        saleForm.setClient(sale.getClient());
        saleForm.setAuto(sale.getAuto());
        saleForm.setDate(sale.getDate());
        saleForm.setEmployee(sale.getEmployee());
        QueryData.addDataToDb("table_sales", MainController.tableMap.get("table_sales"), saleForm);
        return sale;
    }

    @RequestMapping("/employees/getAll")
    public List<Employee> getEmployeesFromAndroidClient() throws SQLException, ClassNotFoundException {
        return QueryData.getDataFromDb("table_employee", MainController.tableMap.get("table_employee"), Employee.class);
    }

    @RequestMapping(value = { "employees/updateEmployee" }, method = RequestMethod.POST)
    public Employee updateEmployeeFromAndroidClient(@RequestBody Employee employee) throws SQLException, ClassNotFoundException {
        QueryData.updateDataInDb("table_employee", MainController.tableMap.get("table_employee"), employee);
        return employee;
    }

    @RequestMapping(value = { "employees/addEmployee" }, method = RequestMethod.POST)
    public Employee addEmployeeFromAndroidClient(@RequestBody Employee employee) throws SQLException, ClassNotFoundException {
        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setSurname(employee.getSurname());
        employeeForm.setName(employee.getName());
        employeeForm.setPatr(employee.getPatr());
        employeeForm.setPosition(employee.getPosition());
        employeeForm.setAddress(employee.getAddress());
        employeeForm.setPhone(employee.getPhone());
        QueryData.addDataToDb("table_employee", MainController.tableMap.get("table_employee"), employeeForm);
        return employee;
    }

    @RequestMapping(value = { "clients/addClient" }, method = RequestMethod.POST)
    public Client addClientFromAndroidClient(@RequestBody Client client) throws SQLException, ClassNotFoundException {
        ClientForm clientForm = new ClientForm();
        clientForm.setSurname(client.getSurname());
        clientForm.setName(client.getName());
        clientForm.setPatr(client.getPatr());
        clientForm.setPhone(client.getPhone());
        QueryData.addDataToDb("table_clients", MainController.tableMap.get("table_clients"), clientForm);
        return client;
    }

    @RequestMapping(value = { "clients/updateClient" }, method = RequestMethod.POST)
    public Client updateClientFromAndroidClient(@RequestBody Client client) throws SQLException, ClassNotFoundException {
        QueryData.updateDataInDb("table_clients", MainController.tableMap.get("table_clients"), client);
        return client;
    }

    @RequestMapping(value = { "clients/deleteClient" }, method = RequestMethod.POST)
    public Long deleteClientFromAndroidClient(@RequestBody Long id) throws SQLException, ClassNotFoundException {
        QueryData.deleteDataFromDb("table_clients", id);
        return id;
    }

    @RequestMapping(value = { "employees/deleteEmployee" }, method = RequestMethod.POST)
    public Long deleteEmployeeFromAndroidClient(@RequestBody Long id) throws SQLException, ClassNotFoundException {
        QueryData.deleteDataFromDb("table_employee", id);
        return id;
    }

    @RequestMapping(value = { "sales/deleteSale" }, method = RequestMethod.POST)
    public Long deleteSaleFromAndroidClient(@RequestBody Long id) throws SQLException, ClassNotFoundException {
        QueryData.deleteDataFromDb("table_sales", id);
        return id;
    }

    @RequestMapping(value = { "auto/deleteAuto" }, method = RequestMethod.POST)
    public Long deleteAutoFromAndroidClient(@RequestBody Long id) throws SQLException, ClassNotFoundException {
        QueryData.deleteDataFromDb("table_auto", id);
        return id;
    }
}
