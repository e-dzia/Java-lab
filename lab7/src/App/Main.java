package App;

import Database.DAO.ClientDAO;
import Database.DAO.EmployeeDAO;
import Database.Entities.Client;
import Database.Entities.Employee;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login login = new Login();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    static void showMainWindow(){
        EventQueue.invokeLater(() -> {
            try {
                JFrame frame = new JFrame();
                frame.setTitle("Welcome to my hotel!");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                MainWindow mainWindow = new MainWindow();
                frame.setContentPane(mainWindow);
                frame.setVisible(true);
                frame.pack();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
