package client;

import javax.swing.*;
import javax.xml.soap.SOAPException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;


public class NodeWindow extends JFrame {
    private JTabbedPane tabbedPane1;
    private JComboBox receiverTypeBox;
    private JTextArea messageText;
    private JButton wyslijButton;
    private JTextArea receivedText;
    private JButton odbierzButton;
    private JTextField nameTextField;
    private JButton OKButton;
    private JPanel panel1;
    private JPanel sendingPanel;
    private JPanel receivingPanel;

    String[] receivers = {"Broadcast", "Layer broadcast:A", "Layer broadcast:B","Layer broadcast:C", "Unicast:A1",
            "Unicast:A2", "Unicast:A3","Unicast:A4","Unicast:A5","Unicast:B1","Unicast:B2","Unicast:B3", "Unicast:B4", "Unicast:B5",
            "Unicast:C1","Unicast:C2","Unicast:C3", "Unicast:C4", "Unicast:C5"};

    NodeClient nodeClient;

    public NodeWindow() {
        tabbedPane1.setVisible(false);
        receivedText.setEditable(false);

        wyslijButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String receiverType = (String) receiverTypeBox.getSelectedItem();
                String message = messageText.getText();
                try {
                    nodeClient.sendMessage(receiverType, message);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        });
        odbierzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<String> messages = nodeClient.receiveMessages();
                    if (messages != null)
                        for (String message : messages){
                            receivedText.append(message + "\n");
                        }
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        });
        OKButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                try {
                    nodeClient = new NodeClient(nameTextField.getText());
                    OKButton.setEnabled(false);
                    nameTextField.setEditable(false);
    
                    tabbedPane1.setVisible(true);
                    pack();
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        });
    
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
//                try {
//                    if (nodeClient != null){
//                        nodeClient.service.removeNode(nodeClient.getName());
//                    }
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                }
                System.exit(0);
            }
        });
    
        this.setTitle("SOAP");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        this.setVisible(true);
        this.pack();
    }

    private void createUIComponents() {
        receiverTypeBox = new JComboBox(receivers);
    }
    
    
}
