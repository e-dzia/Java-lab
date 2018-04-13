package App;

import Database.DAO.ClientDAO;
import Database.DAO.EmployeeDAO;
import Database.Entities.Client;
import Database.Entities.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        ClientDAO clientDAO = new ClientDAO();
        List<Client> clients = new ArrayList<>();
        try {
            clients = clientDAO.getEntities();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++){
            System.out.println(clients.get(i));
        }

        EmployeeDAO employeeDAO = new EmployeeDAO();
        List<Employee> employees = new ArrayList<>();
        try {
            employees = employeeDAO.getEntities();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++){
            System.out.println(employees.get(i));
        }
    }

}
