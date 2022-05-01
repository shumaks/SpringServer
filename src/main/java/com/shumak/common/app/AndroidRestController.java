package com.shumak.common.app;

import com.shumak.common.auto.Auto;
import com.shumak.common.clients.Client;
import com.shumak.common.employees.Employee;
import com.shumak.common.jdbc.QueryData;
import com.shumak.common.mode.Mode;
import com.shumak.common.sales.Sale;
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

    @RequestMapping("/mode/getAll")
    public List<Mode> geModeFromAndroidClient() throws SQLException, ClassNotFoundException {
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

    @RequestMapping("/employees/getAll")
    public List<Employee> getEmployeesFromAndroidClient() throws SQLException, ClassNotFoundException {
        return QueryData.getDataFromDb("table_employee", MainController.tableMap.get("table_employee"), Employee.class);
    }

    @RequestMapping(value = { "clients/addClient" }, method = RequestMethod.POST)
    public Client addClientFromAndroidClient(@RequestBody Client client) throws SQLException, ClassNotFoundException {
        QueryData.addDataToDb("table_clients", MainController.tableMap.get("table_clients"), client);
        return client;
    }

    @RequestMapping(value = { "clients/updateClient" }, method = RequestMethod.POST)
    public Client updateClientFromAndroidClient(@RequestBody Client client) throws SQLException, ClassNotFoundException {
        QueryData.updateDataInDb("table_clients", MainController.tableMap.get("table_clients"), client);
        return client;
    }
}
