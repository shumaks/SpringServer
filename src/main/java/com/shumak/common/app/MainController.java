package com.shumak.common.app;

import com.shumak.common.auto.Auto;
import com.shumak.common.auto.AutoForm;
import com.shumak.common.clients.Client;
import com.shumak.common.clients.ClientForm;
import com.shumak.common.employees.Employee;
import com.shumak.common.employees.EmployeeForm;
import com.shumak.common.jdbc.QueryData;
import com.shumak.common.mode.Mode;
import com.shumak.common.mode.ModeForm;
import com.shumak.common.sales.Sale;
import com.shumak.common.sales.SaleForm;
import com.shumak.common.service.AuthService;
import com.shumak.common.service.ImageCodingService;
import com.shumak.common.users.User;
import com.shumak.common.users.UserForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Controller
public class MainController {

    public static HashMap<String, List<String>> tableMap = new HashMap<>();
    public static AuthService authService = new AuthService();

    static {
        tableMap.put("table_auto", List.of("id", "model", "sits", "year", "image", "id_mode"));
        tableMap.put("table_clients", List.of("id", "surname", "name", "patr", "phone"));
        tableMap.put("table_employee", List.of("id", "surname", "name", "patr", "position", "address", "phone"));
        tableMap.put("table_mode", List.of("id", "mode", "max_speed", "acceleration_time", "engine_volume", "gas_mileage", "price"));
        tableMap.put("table_sales", List.of("id", "date", "id_emp", "id_client", "id_auto"));
        tableMap.put("table_users", List.of("id", "login", "password", "access_rights"));
    }

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);

        authService.setCurrentUser(null);
        return "index";
    }

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.POST)
    public String checkUser(Model model, //
                                @ModelAttribute("userForm") UserForm userForm) throws SQLException, ClassNotFoundException {
        String login = userForm.getLogin();
        String password = userForm.getPassword();
        List<User> list = QueryData.getDataFromDb("table_users", tableMap.get("table_users"), User.class);

        AtomicReference<String> returnedValue = new AtomicReference<>();

        list.forEach(user -> {
            if (Objects.equals(user.getLogin(), login) && Objects.equals(user.getPassword(), password)) {
                authService.setCurrentUser(user);
                returnedValue.set("redirect:/home");
            }
        });

        if (Objects.equals(returnedValue.get(), "redirect:/home")) {
            return "redirect:/home";
        } else {
            String error = "Неверный логин или пароль!";
            model.addAttribute("errorMessage", error);
            return "index";
        }
    }

    @RequestMapping(value = { "/registration" }, method = RequestMethod.GET)
    public String registration(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);

        if (authService.getCurrentUser() == null) {
            return "registration";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping(value = { "/registration" }, method = RequestMethod.POST)
    public String userRegistration(Model model, //
                            @ModelAttribute("userForm") UserForm userForm) throws SQLException, ClassNotFoundException {
        String login = userForm.getLogin();
        String password = userForm.getPassword();
        List<User> list = QueryData.getDataFromDb("table_users", tableMap.get("table_users"), User.class);

        AtomicReference<String> returnedValue = new AtomicReference<>("redirect:/home");

        list.forEach(user -> {
            if (Objects.equals(user.getLogin(), login)) {
                returnedValue.set("error");
            }
        });

        if (Objects.equals(returnedValue.get(), "redirect:/home")) {
            User user = new User(0, login, password, "manager");
            authService.setCurrentUser(user);
            QueryData.addDataToDb("table_users", MainController.tableMap.get("table_users"), userForm);
            return "redirect:/home";
        } else {
            String error = "Пользователь с таким логином уже существует!";
            model.addAttribute("errorMessage", error);
            return "registration";
        }
    }

    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public String home(Model model) {

        if (authService.getCurrentUser() != null) {
            model.addAttribute("userForm", authService.getCurrentUser());
            return "home";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = { "/clientsTable" }, method = RequestMethod.GET)
    public String clientsTable(Model model) throws SQLException, ClassNotFoundException {

        List<Client> list = QueryData.getDataFromDb("table_clients", tableMap.get("table_clients"), Client.class);

        model.addAttribute("clients", list);

        if (authService.getCurrentUser() != null) {
            model.addAttribute("userForm", authService.getCurrentUser());
            return "clientsTable";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/clientsTable/delete/{id}")
    public String deleteClient(@PathVariable("id") Long id, Model model
    ) throws SQLException, ClassNotFoundException {
        QueryData.deleteDataFromDb("table_clients", id);
        return "redirect:/clientsTable";
    }

    @PostMapping("/clientsTable/update/{id}")
    public String goUpdateClient(@PathVariable("id") Long id, Model model
    ) {
        return "redirect:/updateClient/{id}";
    }

    @RequestMapping(value = { "/updateClient/{id}" }, method = RequestMethod.GET)
    public String updateClient(@PathVariable("id") Long id, Model model) throws SQLException, ClassNotFoundException {

        List<Client> list = QueryData.getDataFromDb("table_clients", tableMap.get("table_clients"), Client.class);
        list.forEach(client -> {
            if (client.getId() == id) {
                model.addAttribute("clientForm", client);
            }
        });
        if (authService.getCurrentUser() != null) {
            return "updateClient";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = { "/updateClient/{id}" }, method = RequestMethod.POST)
    public String updateClientPost(@PathVariable("id") Long id, Model model, @ModelAttribute("clientForm") ClientForm clientForm
    ) throws SQLException, ClassNotFoundException {

        Client client = new Client(id, clientForm.getSurname(), clientForm.getName(), clientForm.getPatr(), clientForm.getPhone());

        QueryData.updateDataInDb("table_clients", tableMap.get("table_clients"), client);

        return "redirect:/clientsTable";
    }


    @RequestMapping(value = { "/addClient" }, method = RequestMethod.GET)
    public String addClientForm(Model model) {

        ClientForm clientForm = new ClientForm();
        model.addAttribute("clientForm", clientForm);

        if (authService.getCurrentUser() != null) {
            return "addClient";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = { "/addClient" }, method = RequestMethod.POST)
    public String addClientSave(Model model, //
                                @ModelAttribute("clientForm") ClientForm clientForm) throws SQLException, ClassNotFoundException {

        String surname = clientForm.getSurname();
        String name = clientForm.getName();
        String patr = clientForm.getPatr();
        String phone = clientForm.getPhone();

        if (name != null && name.length() > 0
                && surname != null && surname.length() > 0
                && patr != null && patr.length() > 0
                && phone != null && phone.length() > 0
        ) {
            QueryData.addDataToDb("table_clients", tableMap.get("table_clients"), clientForm);
            return "redirect:/clientsTable";
        }
        String error = "All fields are required!";
        model.addAttribute("errorMessage", error);

        if (authService.getCurrentUser() != null) {
            return "addClient";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = { "/autoTable" }, method = RequestMethod.GET)
    public String autoTable(Model model) throws SQLException, ClassNotFoundException {

        List<Auto> list = QueryData.getDataFromDb("table_auto", tableMap.get("table_auto"), Auto.class);

        model.addAttribute("autos", list);

        if (authService.getCurrentUser() != null) {
            model.addAttribute("userForm", authService.getCurrentUser());
            return "autoTable";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/autoTable/delete/{id}")
    public String deleteAuto(@PathVariable("id") Long id, Model model
    ) throws SQLException, ClassNotFoundException {
        QueryData.deleteDataFromDb("table_auto", id);
        return "redirect:/autoTable";
    }

    @PostMapping("/autoTable/update/{id}")
    public String goUpdateAuto(@PathVariable("id") Long id, Model model
    ) {
        return "redirect:/updateAuto/{id}";
    }

    @RequestMapping(value = { "/updateAuto/{id}" }, method = RequestMethod.GET)
    public String updateAuto(@PathVariable("id") Long id, Model model) throws SQLException, ClassNotFoundException {

        List<Auto> list = QueryData.getDataFromDb("table_auto", tableMap.get("table_auto"), Auto.class);
        list.forEach(auto -> {
            if (auto.getId() == id) {
                model.addAttribute("autoForm", auto);
                try {
                    List<Mode> listMode = QueryData.getDataFromDb("table_mode", tableMap.get("table_mode"), Mode.class);
                    model.addAttribute("mode", listMode);
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        if (authService.getCurrentUser() != null) {
            return "updateAuto";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = { "/updateAuto/{id}" }, method = RequestMethod.POST)
    public String updateAutoPost(@PathVariable("id") Long id, Model model, @ModelAttribute("autoForm") AutoForm autoForm, @ModelAttribute("selectedMode") Long modeId
    ) throws SQLException, ClassNotFoundException {
        List<Mode> listMode = QueryData.getDataFromDb("table_mode", tableMap.get("table_mode"), Mode.class);
        listMode.forEach(mode -> {
            if (mode.getId() == modeId) {
                autoForm.setMode(mode);
            }
        });

        Auto auto = new Auto(id, autoForm.getModel(), autoForm.getSits(), autoForm.getModelYear(), autoForm.getImage(), autoForm.getMode());

        QueryData.updateDataInDb("table_auto", tableMap.get("table_auto"), auto);

        return "redirect:/autoTable";
    }

    @RequestMapping(value = { "/addAuto" }, method = RequestMethod.GET)
    public String addAutoForm(Model model) throws SQLException, ClassNotFoundException {

        AutoForm autoForm = new AutoForm();
        model.addAttribute("autoForm", autoForm);

        List<Mode> listMode = QueryData.getDataFromDb("table_mode", tableMap.get("table_mode"), Mode.class);
        model.addAttribute("mode", listMode);

        if (authService.getCurrentUser() != null) {
            return "addAuto";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = { "/addAuto" }, method = RequestMethod.POST)
    public String addAuto(Model model, @ModelAttribute("autoForm") AutoForm autoForm,
                          @ModelAttribute("selectedMode") Long modeId
    ) throws SQLException, ClassNotFoundException {
        String modelAuto = autoForm.getModel();
        List<Mode> listMode = QueryData.getDataFromDb("table_mode", tableMap.get("table_mode"), Mode.class);
        listMode.forEach(mode -> {
            if (mode.getId() == modeId) {
                autoForm.setMode(mode);
            }
        });

        if (modelAuto != null && modelAuto.length() > 0) {
            QueryData.addDataToDb("table_auto", tableMap.get("table_auto"), autoForm);
            return "redirect:/autoTable";
        }
        String error = "All fields are required!";
        model.addAttribute("errorMessage", error);

        if (authService.getCurrentUser() != null) {
            return "addAuto";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = { "/modeTable" }, method = RequestMethod.GET)
    public String modeTable(Model model) throws SQLException, ClassNotFoundException {

        List<Mode> list = QueryData.getDataFromDb("table_mode", tableMap.get("table_mode"), Mode.class);

        model.addAttribute("modes", list);

        if (authService.getCurrentUser() != null) {
            model.addAttribute("userForm", authService.getCurrentUser());
            return "modeTable";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/modeTable/delete/{id}")
    public String deleteMode(@PathVariable("id") Long id, Model model
    ) throws SQLException, ClassNotFoundException {
        QueryData.deleteDataFromDb("table_mode", id);
        return "redirect:/modeTable";
    }

    @PostMapping("/modeTable/update/{id}")
    public String goUpdateMode(@PathVariable("id") Long id, Model model
    ) {
        return "redirect:/updateMode/{id}";
    }

    @RequestMapping(value = { "/updateMode/{id}" }, method = RequestMethod.GET)
    public String updateMode(@PathVariable("id") Long id, Model model) throws SQLException, ClassNotFoundException {

        List<Mode> list = QueryData.getDataFromDb("table_mode", tableMap.get("table_mode"), Mode.class);
        list.forEach(mode -> {
            if (mode.getId() == id) {
                model.addAttribute("modeForm", mode);
            }
        });

        if (authService.getCurrentUser() != null) {
            return "updateMode";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = { "/updateMode/{id}" }, method = RequestMethod.POST)
    public String updateModePost(@PathVariable("id") Long id, Model model, @ModelAttribute("modeForm") ModeForm modeForm
    ) throws SQLException, ClassNotFoundException {

        Mode mode = new Mode(id, modeForm.getName(), modeForm.getMaxSpeed(), modeForm.getAccelerationTime(), modeForm.getEngineVolume(), modeForm.getGasMileage(), modeForm.getPrice());

        QueryData.updateDataInDb("table_mode", tableMap.get("table_mode"), mode);

        return "redirect:/modeTable";
    }


    @RequestMapping(value = { "/addMode" }, method = RequestMethod.GET)
    public String addModeForm(Model model) {

        ModeForm modeForm = new ModeForm();
        model.addAttribute("modeForm", modeForm);

        if (authService.getCurrentUser() != null) {
            return "addMode";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = { "/addMode" }, method = RequestMethod.POST)
    public String addModeSave(Model model, //
                                @ModelAttribute("modeForm") ModeForm modeForm) throws SQLException, ClassNotFoundException {

        String name = modeForm.getName();

        if (name != null && name.length() > 0) {
            QueryData.addDataToDb("table_mode", tableMap.get("table_mode"), modeForm);
            return "redirect:/modeTable";
        }
        String error = "All fields are required!";
        model.addAttribute("errorMessage", error);

        if (authService.getCurrentUser() != null) {
            return "addMode";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = { "/employeesTable" }, method = RequestMethod.GET)
    public String employeesTable(Model model) throws SQLException, ClassNotFoundException {

        List<Employee> list = QueryData.getDataFromDb("table_employee", tableMap.get("table_employee"), Employee.class);

        model.addAttribute("employees", list);

        if ( Objects.equals(authService.getCurrentUser().getAccessRights(), "director")) {
            return "employeesTable";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/employeesTable/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id, Model model
    ) throws SQLException, ClassNotFoundException {
        QueryData.deleteDataFromDb("table_employee", id);
        return "redirect:/employeesTable";
    }

    @PostMapping("/employeesTable/update/{id}")
    public String goUpdateEmployee(@PathVariable("id") Long id, Model model
    ) {
        return "redirect:/updateEmployee/{id}";
    }

    @RequestMapping(value = { "/updateEmployee/{id}" }, method = RequestMethod.GET)
    public String updateEmployee(@PathVariable("id") Long id, Model model) throws SQLException, ClassNotFoundException {

        List<Employee> list = QueryData.getDataFromDb("table_employee", tableMap.get("table_employee"), Employee.class);
        list.forEach(employee -> {
            if (employee.getId() == id) {
                model.addAttribute("employeeForm", employee);
            }
        });

        if (Objects.equals(authService.getCurrentUser().getAccessRights(), "director")) {
            return "updateEmployee";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = { "/updateEmployee/{id}" }, method = RequestMethod.POST)
    public String updateEmployeePost(@PathVariable("id") Long id, Model model, @ModelAttribute("employeeForm") EmployeeForm employeeForm
    ) throws SQLException, ClassNotFoundException {

        Employee employee = new Employee(id, employeeForm.getSurname(), employeeForm.getName(), employeeForm.getPatr(), employeeForm.getPosition(), employeeForm.getAddress(), employeeForm.getPhone());

        QueryData.updateDataInDb("table_employee", tableMap.get("table_employee"), employee);

        return "redirect:/employeesTable";
    }


    @RequestMapping(value = { "/addEmployee" }, method = RequestMethod.GET)
    public String addEmployeeForm(Model model) {

        EmployeeForm employeeForm = new EmployeeForm();
        model.addAttribute("employeeForm", employeeForm);

        if (Objects.equals(authService.getCurrentUser().getAccessRights(), "director")) {
            return "addEmployee";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = { "/addEmployee" }, method = RequestMethod.POST)
    public String addEmployeeSave(Model model, //
                                @ModelAttribute("employeeForm") EmployeeForm employeeForm) throws SQLException, ClassNotFoundException {

        String surname = employeeForm.getSurname();
        String name = employeeForm.getName();
        String patr = employeeForm.getPatr();
        String position = employeeForm.getPosition();
        String address = employeeForm.getAddress();
        String phone = employeeForm.getPhone();

        if (name != null && name.length() > 0
                && surname != null && surname.length() > 0
                && patr != null && patr.length() > 0
                && position != null && position.length() > 0
                && address != null && address.length() > 0
                && phone != null && phone.length() > 0
        ) {
            QueryData.addDataToDb("table_employee", tableMap.get("table_employee"), employeeForm);
            return "redirect:/employeesTable";
        }
        String error = "All fields are required!";
        model.addAttribute("errorMessage", error);

        if (Objects.equals(authService.getCurrentUser().getAccessRights(), "director")) {
            return "addEmployee";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = { "/salesTable" }, method = RequestMethod.GET)
    public String salesTable(Model model) throws SQLException, ClassNotFoundException {
        List<Sale> list = QueryData.getDataFromDb("table_sales", tableMap.get("table_sales"), Sale.class);

        model.addAttribute("sales", list);

        if (authService.getCurrentUser() != null) {
            model.addAttribute("userForm", authService.getCurrentUser());
            return "salesTable";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/salesTable/delete/{id}")
    public String deleteSale(@PathVariable("id") Long id, Model model
    ) throws SQLException, ClassNotFoundException {
        QueryData.deleteDataFromDb("table_sales", id);
        return "redirect:/salesTable";
    }

    @PostMapping("/salesTable/update/{id}")
    public String goUpdateSale(@PathVariable("id") Long id, Model model
    ) {
        return "redirect:/updateSale/{id}";
    }

    @RequestMapping(value = { "/updateSale/{id}" }, method = RequestMethod.GET)
    public String updateSale(@PathVariable("id") Long id, Model model) throws SQLException, ClassNotFoundException {

        List<Sale> list = QueryData.getDataFromDb("table_sales", tableMap.get("table_sales"), Sale.class);
        list.forEach(sale -> {
            if (sale.getId() == id) {
                model.addAttribute("saleForm", sale);
                try {
                    List<Employee> listEmployee = QueryData.getDataFromDb("table_employee", tableMap.get("table_employee"), Employee.class);
                    model.addAttribute("employees", listEmployee);

                    List<Client> listClients = QueryData.getDataFromDb("table_clients", tableMap.get("table_clients"), Client.class);
                    model.addAttribute("clients", listClients);

                    List<Auto> listAuto = QueryData.getDataFromDb("table_auto", tableMap.get("table_auto"), Auto.class);
                    model.addAttribute("autos", listAuto);
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        if (authService.getCurrentUser() != null) {
            return "updateSale";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = { "/updateSale/{id}" }, method = RequestMethod.POST)
    public String updateSalePost(
            @PathVariable("id") Long id,
            Model model,
            @ModelAttribute("saleForm") SaleForm saleForm,
            @ModelAttribute("selectedEmployee") Long employeeId,
            @ModelAttribute("selectedClient") Long clientId,
            @ModelAttribute("selectedAuto") Long autoId
    ) throws SQLException, ClassNotFoundException {
        List<Employee> listEmployee = QueryData.getDataFromDb("table_employee", tableMap.get("table_employee"), Employee.class);
        listEmployee.forEach(employee -> {
            if (employee.getId() == employeeId) {
                saleForm.setEmployee(employee);
            }
        });

        List<Client> listClients = QueryData.getDataFromDb("table_clients", tableMap.get("table_clients"), Client.class);
        listClients.forEach(client -> {
            if (client.getId() == clientId) {
                saleForm.setClient(client);
            }
        });

        List<Auto> listAuto = QueryData.getDataFromDb("table_auto", tableMap.get("table_auto"), Auto.class);
        listAuto.forEach(auto -> {
            if (auto.getId() == autoId) {
                saleForm.setAuto(auto);
            }
        });

        Sale sale = new Sale(id, saleForm.getDate(), saleForm.getEmployee(), saleForm.getClient(), saleForm.getAuto());

        QueryData.updateDataInDb("table_sales", tableMap.get("table_sales"), sale);

        return "redirect:/salesTable";
    }

    @RequestMapping(value = { "/addSale" }, method = RequestMethod.GET)
    public String addSaleForm(Model model) throws SQLException, ClassNotFoundException {

        SaleForm saleForm = new SaleForm();
        model.addAttribute("saleForm", saleForm);

        List<Employee> listEmployee = QueryData.getDataFromDb("table_employee", tableMap.get("table_employee"), Employee.class);
        List<Client> listClients = QueryData.getDataFromDb("table_clients", tableMap.get("table_clients"), Client.class);
        List<Auto> listAuto = QueryData.getDataFromDb("table_auto", tableMap.get("table_auto"), Auto.class);

        model.addAttribute("employees", listEmployee);
        model.addAttribute("clients", listClients);
        model.addAttribute("autos", listAuto);

        if (authService.getCurrentUser() != null) {
            return "addSale";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = { "/addSale" }, method = RequestMethod.POST)
    public String addSale(
            Model model,
            @ModelAttribute("saleForm") SaleForm saleForm,
            @ModelAttribute("selectedEmployee") Long employeeId,
            @ModelAttribute("selectedClient") Long clientId,
            @ModelAttribute("selectedAuto") Long autoId
    ) throws SQLException, ClassNotFoundException {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        String date = formatForDateNow.format(dateNow);
        saleForm.setDate(date);

        List<Employee> listEmployee = QueryData.getDataFromDb("table_employee", tableMap.get("table_employee"), Employee.class);
        listEmployee.forEach(employee -> {
            if (employee.getId() == employeeId) {
                saleForm.setEmployee(employee);
            }
        });

        List<Client> listClients = QueryData.getDataFromDb("table_clients", tableMap.get("table_clients"), Client.class);
        listClients.forEach(client -> {
            if (client.getId() == clientId) {
                saleForm.setClient(client);
            }
        });

        List<Auto> listAuto = QueryData.getDataFromDb("table_auto", tableMap.get("table_auto"), Auto.class);
        listAuto.forEach(auto -> {
            if (auto.getId() == autoId) {
                saleForm.setAuto(auto);
            }
        });

        QueryData.addDataToDb("table_sales", tableMap.get("table_sales"), saleForm);

        return "redirect:/salesTable";
    }
}