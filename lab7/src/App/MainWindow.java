package App;


import Database.DAO.ClientDAO;
import Database.DAO.EmployeeDAO;
import Database.DAO.ReservationDAO;
import Database.DAO.RoomDAO;
import Database.Entities.Client;
import Database.Entities.Employee;
import Database.Entities.Reservation;
import Database.Entities.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class MainWindow extends JPanel {
    private JPanel panel1;
    private JButton pokojeButton;
    private JButton klienciButton;
    private JButton rezerwacjeButton;
    private JButton pracownicyButton;

    public MainWindow() {
        pokojeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> {
                    try {
                        Vector<String> rooms = new Vector<>();
                        for (Room room : new RoomDAO().getEntities()){
                            rooms.add(room.toString());
                        }
                        ShowAll showAll = new ShowAll(rooms);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "Wystapil blad.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });
            }
        });
        klienciButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> {
                    try {
                        ClientMenu clientMenu = new ClientMenu();
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "Wystapil blad.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });
            }
        });
        rezerwacjeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> {
                    try {
                        Vector<String> reservations = new Vector<>();
                        for (Reservation reservation : new ReservationDAO().getEntities()){
                            reservations.add(reservation.toString());
                        }
                        ShowAll showAll = new ShowAll(reservations);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "Wystapil blad.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });
            }
        });
        pracownicyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> {
                    try {
                        EmployeesMenu employeesMenu = new EmployeesMenu();
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "Wystapil blad.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });
            }
        });
    }

    private void createUIComponents() {
        panel1 = this;
    }
}
