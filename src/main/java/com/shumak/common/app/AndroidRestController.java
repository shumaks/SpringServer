package com.shumak.common.app;

import com.shumak.common.auto.Auto;
import com.shumak.common.clients.Client;
import com.shumak.common.employees.Employee;
import com.shumak.common.jdbc.QueryData;
import com.shumak.common.sales.Sale;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLException;
import java.util.List;

@RestController
public class AndroidRestController {

    @RequestMapping("/auto/getAll")
    public List<Auto> getAutoFromAndroidClient() throws SQLException, ClassNotFoundException {
        return QueryData.getDataFromDb("table_auto", MainController.tableMap.get("table_auto"), Auto.class);
    }

    @RequestMapping("/clients/gelAll")
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
}
