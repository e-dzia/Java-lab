package App;


import Database.DAO.EmployeeDAO;
import Database.Entities.Employee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class Login extends JFrame {
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JPanel panel1;
    private JButton zalogujButton;
    private JButton anulujButton;

    Login() {
        zalogujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int login = -1;
                try {
                    login = Integer.parseInt(textField1.getText());
                }
                catch (Exception e1){
                    JOptionPane.showMessageDialog(null, "ID nie jest liczba.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                String password = String.valueOf(passwordField1.getPassword());
                /*if (login == 999){
                    Main.showMainWindow();
                    dispose();
                }
                else {*/
                    try {
                        Employee employee = new EmployeeDAO().getEntityById(login);
                        String encryptedPassword = Password.encrypt(password);
                        if (encryptedPassword.equals(employee.getHash_code())){
                            Main.showMainWindow();
                            dispose();
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Nieprawidłowe hasło.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NoSuchAlgorithmException | SQLException e1) {
                        JOptionPane.showMessageDialog(null, "Wystąpił błąd.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    catch(NullPointerException e1){
                        JOptionPane.showMessageDialog(null, "Pracownik o podanym ID nie istnieje.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                //}
            }
        });
        anulujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setTitle("Login");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setVisible(true);
        pack();

    }

}
