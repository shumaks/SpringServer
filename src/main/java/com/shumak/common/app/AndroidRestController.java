package com.shumak.common.app;

import com.shumak.common.auto.Auto;
import com.shumak.common.jdbc.QueryData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@RestController
public class AndroidRestController {

    public static HashMap<String, List<String>> tableMap = new HashMap<>();

    static {
        tableMap.put("table_auto", List.of("id", "model", "sits", "year", "id_mode"));
        tableMap.put("table_clients", List.of("id", "surname", "name", "patr", "phone"));
        tableMap.put("table_employee", List.of("id", "surname", "name", "patr", "position", "address", "phone"));
        tableMap.put("table_mode", List.of("id", "name", "max_speed", "acceleration_time", "engine_volume", "gas_mileage", "price"));
        tableMap.put("table_sales", List.of("id", "date", "id_emp", "id_client", "id_auto"));
        tableMap.put("table_users", List.of("id", "login", "password"));
    }

    @RequestMapping("/getAutoFromAndroidClient")
    public List<Auto> getAutoFromAndroidClient() throws SQLException, ClassNotFoundException {
        return QueryData.getDataFromDb("table_auto", tableMap.get("table_auto"), Auto.class);
    }
}
