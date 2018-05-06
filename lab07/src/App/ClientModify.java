package App;

import Database.DAO.ClientDAO;
import Database.Entities.Client;
import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ClientModify extends JFrame{
    private JTextField firstNameTextField;
    private JTextField emailTextField;
    private JTextField identityNumberTextField;
    private JTextField phoneTextField;
    private JButton zmienButton1;
    private JButton anulujButton;
    private JTextField lastNameTextField;
    private JTextField idTextField;
    private JButton szukajButton;
    private JPanel panel1;

    public ClientModify() {
        szukajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                try {
                    id = Integer.parseInt(idTextField.getText());
                }
                catch (Exception e1){
                    JOptionPane.showMessageDialog(null, "Nieprawidlowe ID","ERROR!",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Client client;
                try {
                    client = new ClientDAO().getEntityById(id);
                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "Blad laczenia z baza danych lub nieprawidłowe ID.","ERROR!",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                firstNameTextField.setText(client.getFirst_name());
                lastNameTextField.setText(client.getLast_name());
                emailTextField.setText(client.getEmail());
                identityNumberTextField.setText(client.getIdentity_number());
                phoneTextField.setText(client.getPhone_number());

                idTextField.setEditable(false);
                szukajButton.setEnabled(false);
            }
        });
        zmienButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id;
                try {
                    id = Integer.parseInt(idTextField.getText());
                }
                catch (Exception e1){
                    JOptionPane.showMessageDialog(null, "Nieprawidlowe ID","ERROR!",JOptionPane.ERROR_MESSAGE);
                    return;
                }
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
                Client client = new Client(id,firstName,lastName,email,identityNumber,phoneNumber);
                ClientDAO clientDAO = new ClientDAO();
                try {
                    clientDAO.updateEntity(client);
                    JOptionPane.showMessageDialog(null, "Zmieniono!","SUCCESS!",JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e1){
                    JOptionPane.showMessageDialog(null, "Podane dane juz wystepuja w bazie","ERROR!",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                catch (SQLException e1) {
                    JOptionPane.showMessageDialog(null, "Blad dodawania do bazy danych","ERROR!",JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
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
        setTitle("Modyfikuj klienta");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(panel1);
        setVisible(true);
        pack();
    }
}
