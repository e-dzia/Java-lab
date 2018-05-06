package App;

import Database.DAO.ClientDAO;
import Database.Entities.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


public class ClientMenu extends JFrame{
    private JButton przegladajButton;
    private JButton dodajButton;
    private JButton modyfikujButton;
    private JPanel panel1;

    public ClientMenu() {
        przegladajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(() -> {
                    try {
                        Vector<String> clients = new Vector<>();
                        for (Client client : new ClientDAO().getEntities()){
                            clients.add(client.toString());
                        }
                        ShowAll showAll = new ShowAll(clients);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                });
            }
        });
        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientAdd clientAdd = new ClientAdd();
            }
        });
        modyfikujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientModify clientModify = new ClientModify();
            }
        });
        setTitle("Clients");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(panel1);
        setVisible(true);
        pack();
    }

}
