package App;


import Database.DAO.ClientDAO;
import Database.Entities.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ClientAdd extends JFrame {
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField emailTextField;
    private JTextField identityNumberTextField;
    private JTextField phoneTextField;
    private JButton anulujButton;
    private JButton dodajButton1;
    private JPanel panel1;

    public ClientAdd() {
        dodajButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String email = emailTextField.getText();
                String identityNumber = identityNumberTextField.getText();
                String phoneNumber = phoneTextField.getText();
                if (!email.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")){
                    JOptionPane.showMessageDialog(null, "Nieprawidlowy email","ERROR!",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!phoneNumber.matches("^[0-9\\-\\+]{9,15}$")){
                    JOptionPane.showMessageDialog(null, "Nieprawidlowy numer telefonu","ERROR!",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Client client = new Client(firstName,lastName,email,identityNumber,phoneNumber);
                ClientDAO clientDAO = new ClientDAO();
                try {
                    clientDAO.saveEntity(client);
                    JOptionPane.showMessageDialog(null, "Dodano!","SUCCESS!",JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e1){
                    JOptionPane.showMessageDialog(null, "Podane dane juz wystepuja w bazie","ERROR!",JOptionPane.ERROR_MESSAGE);
                    //e1.printStackTrace();
                    return;
                }
                catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "Blad dodawania do bazy danych","ERROR!",JOptionPane.ERROR_MESSAGE);
                    //e1.printStackTrace();
                    return;
                }
            }
        });
        anulujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setTitle("Dodaj klienta");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(panel1);
        setVisible(true);
        pack();
    }

}
