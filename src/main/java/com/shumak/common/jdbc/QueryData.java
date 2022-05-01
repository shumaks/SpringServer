package com.shumak.common.jdbc;

import com.shumak.common.auto.Auto;
import com.shumak.common.auto.AutoForm;
import com.shumak.common.clients.Client;
import com.shumak.common.clients.ClientForm;
import com.shumak.common.employees.Employee;
import com.shumak.common.employees.EmployeeForm;
import com.shumak.common.mode.Mode;
import com.shumak.common.sales.Sale;
import com.shumak.common.sales.SaleForm;
import com.shumak.common.service.ImageCodingService;
import com.shumak.common.users.User;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class QueryData {

    public static void deleteDataFromDb(String table, Long id) throws ClassNotFoundException, SQLException {
        // Get Connection
        Connection connection = ConnectionUtils.getConnection();

        Statement statement = connection.createStatement();

        if (Objects.equals(table, "table_auto")) {
            String sqlAuto = "Delete from table_sales where id_auto = " + id;
            statement.executeUpdate(sqlAuto);
        } else if (Objects.equals(table, "table_clients")) {
            String sqlClient = "Delete from table_sales where id_client = " + id;
            statement.executeUpdate(sqlClient);
        } else if (Objects.equals(table, "table_employee")) {
            String sqlEmp = "Delete from table_sales where id_emp = " + id;
            statement.executeUpdate(sqlEmp);
        } else if (Objects.equals(table, "table_mode")) {
            String sqlMode = "Delete from table_auto where id_mode = " + id;
            statement.executeUpdate(sqlMode);
        }

        String sql = "Delete from " + table + " where id = " + id;

        // Execute statement
        // executeUpdate(String) using for Insert, Update, Delete statement.
        statement.executeUpdate(sql);
        connection.close();
    }

    public static <T> void addDataToDb(String table, List<String> columns, T data) throws ClassNotFoundException, SQLException {
        // Get Connection
        Connection connection = ConnectionUtils.getConnection();

        Statement statement = connection.createStatement();
        StringBuilder values = new StringBuilder();

        if (data.getClass() == ClientForm.class) {
            ClientForm client = (ClientForm) data;
            values.append("'");
            values.append(client.getSurname());
            values.append("'");
            values.append(", ");
            values.append("'");
            values.append(client.getName());
            values.append("'");
            values.append(", ");
            values.append("'");
            values.append(client.getPatr());
            values.append("'");
            values.append(", ");
            values.append("'");
            values.append(client.getPhone());
            values.append("'");
        } else if (data.getClass() == AutoForm.class) {
            AutoForm auto = (AutoForm) data;
            values.append("'");
            values.append(auto.getModel());
            values.append("'");
            values.append(", ");
            values.append(auto.getSits());
            values.append(", ");
            values.append(auto.getModelYear());
            values.append(", ");

            File f =  new File("D:\\ДИПЛОМ\\SpringServer\\src\\main\\resources\\images\\audia3.jpg");
            try {
                String encodstring = ImageCodingService.encodeFileToBase64Binary(f);

                values.append("'");
                values.append(encodstring);
                values.append("'");
                values.append(", ");
            } catch (Exception e) {
                e.printStackTrace();
            }

            values.append(auto.getMode().getId());
        } else if (data.getClass() == EmployeeForm.class) {
            EmployeeForm employee = (EmployeeForm) data;
            values.append("'");
            values.append(employee.getSurname());
            values.append("'");
            values.append(", ");
            values.append("'");
            values.append(employee.getName());
            values.append("'");
            values.append(", ");
            values.append("'");
            values.append(employee.getPatr());
            values.append("'");
            values.append(", ");
            values.append("'");
            values.append(employee.getPosition());
            values.append("'");
            values.append(", ");
            values.append("'");
            values.append(employee.getAddress());
            values.append("'");
            values.append(", ");
            values.append("'");
            values.append(employee.getPhone());
            values.append("'");
        } else {
            SaleForm sale = (SaleForm) data;
            values.append("'");
            values.append(sale.getDate());
            values.append("'");
            values.append(", ");
            values.append(sale.getEmployee().getId());
            values.append(", ");
            values.append(sale.getClient().getId());
            values.append(", ");
            values.append(sale.getAuto().getId());
        }

        String sql = "Insert into " + table + " (" + getColumnsString(columns.subList(1, columns.size())) + ") values (" + values + ")";
        // Execute statement
        // executeUpdate(String) using for Insert, Update, Delete statement.
        statement.executeUpdate(sql);
        connection.close();
    }

    public static <T> List<T> getDataFromDb(String table, List<String> columns, Class<T> classType) throws ClassNotFoundException, SQLException {

        // Get Connection
        Connection connection = ConnectionUtils.getConnection();

        // Create statement
        Statement statement = connection.createStatement();

        String sql = "Select " + getColumnsString(columns) + " from " + table;
        // Execute SQL statement returns a ResultSet object.
        ResultSet rs = statement.executeQuery(sql);


        ArrayList<T> list = new ArrayList();

        // Fetch on the ResultSet
        // Move the cursor to the next record.
        while (rs.next()) {
            if (classType == User.class) {
                try {
                    list.add((T) new User(rs.getInt(columns.get(0)), rs.getString(columns.get(1)), rs.getString(columns.get(2))));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (classType == Client.class) {
                try {
                    list.add((T) new Client(rs.getInt(columns.get(0)), rs.getString(columns.get(1)), rs.getString(columns.get(2)), rs.getString(columns.get(3)), rs.getString(columns.get(4))));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (classType == Auto.class) {
                try {
                    int id = rs.getInt(columns.get(0));
                    String model = rs.getString(columns.get(1));
                    int sits =  rs.getInt(columns.get(2));
                    int modelYear =  rs.getInt(columns.get(3));
                    String image = rs.getString(columns.get(4));
                    int modeId = rs.getInt(columns.get(5));

                    // Create statement
                    Statement modeStatement = connection.createStatement();

                    String sqlMode = "Select * from table_mode where id = " + modeId;
                    // Execute SQL statement returns a ResultSet object.
                    ResultSet rsMode = modeStatement.executeQuery(sqlMode);
                    rsMode.next();
                    Mode mode = new Mode(rsMode.getInt(1), rsMode.getString(2), rsMode.getInt(3), rsMode.getDouble(4), rsMode.getDouble(5), rsMode.getDouble(6), rsMode.getInt(7));

                    list.add((T) new Auto(id, model, sits, modelYear, image, mode));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }  else if (classType == Employee.class) {
                try {
                    list.add((T) new Employee(rs.getInt(columns.get(0)), rs.getString(columns.get(1)), rs.getString(columns.get(2)), rs.getString(columns.get(3)), rs.getString(columns.get(4)), rs.getString(columns.get(5)), rs.getString(columns.get(6))));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (classType == Mode.class) {
                try {
                    list.add((T) new Mode(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getInt(7)));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (classType == Sale.class) {
                try {
                    int id = rs.getInt(columns.get(0));
                    String date = rs.getString(columns.get(1));
                    int empId = rs.getInt(columns.get(2));
                    int clientId = rs.getInt(columns.get(3));
                    int autoId = rs.getInt(columns.get(4));

                    Statement empStatement = connection.createStatement();
                    String sqlEmp = "Select * from table_employee where id = " + empId;
                    ResultSet rsEmp = empStatement.executeQuery(sqlEmp);
                    rsEmp.next();
                    Employee employee = new Employee(rsEmp.getInt(1), rsEmp.getString(2), rsEmp.getString(3), rsEmp.getString(4), rsEmp.getString(5), rsEmp.getString(6), rsEmp.getString(7));

                    Statement clientStatement = connection.createStatement();
                    String sqlClient = "Select * from table_clients where id = " + clientId;
                    ResultSet rsClient = clientStatement.executeQuery(sqlClient);
                    rsClient.next();
                    Client client = new Client(rsClient.getInt(1), rsClient.getString(2), rsClient.getString(3), rsClient.getString(4), rsClient.getString(5));

                    Statement autoStatement = connection.createStatement();
                    String sqlAuto = "Select * from table_auto where id = " + autoId;
                    ResultSet rsAuto = autoStatement.executeQuery(sqlAuto);
                    rsAuto.next();

                    int idAuto = rsAuto.getInt(1);
                    String model = rsAuto.getString(2);
                    int sits =  rsAuto.getInt(3);
                    int modelYear =  rsAuto.getInt(4);
                    String image = rsAuto.getString(5);
                    int modeId = rsAuto.getInt(6);

                    // Create statement
                    Statement modeStatement = connection.createStatement();

                    String sqlMode = "Select * from table_mode where id = " + modeId;
                    // Execute SQL statement returns a ResultSet object.
                    ResultSet rsMode = modeStatement.executeQuery(sqlMode);
                    rsMode.next();
                    Mode mode = new Mode(rsMode.getInt(1), rsMode.getString(2), rsMode.getInt(3), rsMode.getDouble(4), rsMode.getDouble(5), rsMode.getDouble(6), rsMode.getInt(7));
                    Auto auto = new Auto(idAuto, model, sits, modelYear, image, mode);

                    list.add((T) new Sale(id, date, employee, client, auto));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // Close connection.
        connection.close();

        return list;
    }

    public static <T> void updateDataInDb(String table, List<String> columns, T data) throws ClassNotFoundException, SQLException {
        // Get Connection
        Connection connection = ConnectionUtils.getConnection();

        Statement statement = connection.createStatement();

        String update = null, condition = null;

        if (data.getClass() == Client.class) {
            Client client = (Client) data;
            update = columns.get(1) + " = '" + client.getSurname() +"', " +
                    columns.get(2) + " = '" + client.getName() + "', " +
                    columns.get(3) + " = '" + client.getPatr() + "', " +
                    columns.get(4) + " = '" + client.getPhone() + "'";
            condition = "id = " + client.getId();
        } else if (data.getClass() == Auto.class) {
            Auto auto = (Auto) data;
            update = columns.get(1) + " = '" + auto.getModel() +"', " +
                    columns.get(2) + " = " + auto.getSits() + ", " +
                    columns.get(3) + " = " + auto.getModelYear() + ", " +
                    columns.get(4) + " = '" + auto.getImage() + "', " +
                    columns.get(5) + " = " + auto.getMode().getId();
            condition = "id = " + auto.getId();
        } else if (data.getClass() == Employee.class) {
            Employee employee = (Employee) data;
            update = columns.get(1) + " = '" + employee.getSurname() +"', " +
                    columns.get(2) + " = '" + employee.getName() + "', " +
                    columns.get(3) + " = '" + employee.getPatr() + "', " +
                    columns.get(4) + " = '" + employee.getPosition() + "', " +
                    columns.get(5) + " = '" + employee.getAddress() + "', " +
                    columns.get(6) + " = '" + employee.getPhone() + "'";
            condition = "id = " + employee.getId();
        } else {
            Sale sale = (Sale) data;
            update = columns.get(1) + " = '" + sale.getDate() +"', " +
                    columns.get(2) + " = " + sale.getEmployee().getId() + ", " +
                    columns.get(3) + " = " + sale.getClient().getId() + ", " +
                    columns.get(4) + " = " + sale.getAuto().getId();
            condition = "id = " + sale.getId();
        }

        String sql = "Update " + table + " set " + update + " where " + condition;

        // Execute statement
        // executeUpdate(String) using for Insert, Update, Delete statement.
        statement.executeUpdate(sql);
        connection.close();
    }

    private static String getColumnsString(List<String> columns) {
        StringBuilder columnsSql = new StringBuilder();
        AtomicInteger counter = new AtomicInteger(0);

        columns.forEach(column -> {
            columnsSql.append(column);
            if (counter.get() < columns.size() - 1) {
                columnsSql.append(", ");
            }
            counter.getAndIncrement();
        });

        return columnsSql.toString();
    }
}
