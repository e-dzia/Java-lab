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
    private JButton odswiezButton;
    private JPanel sendingPanel;
    private JPanel receivingPanel;

    

    NodeClient nodeClient;
    
    public NodeWindow(String name){
        try {
            setClient(name);
        }catch (Exception e1){
            e1.printStackTrace();
        }
        
        init();
    }
    
    public NodeWindow() {
        tabbedPane1.setVisible(false);
        init();
        
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setClient(nameTextField.getText());
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        });
        
    }
    
    private void setClient(String name) throws RemoteException {
        nodeClient = new NodeClient(name);
        OKButton.setEnabled(false);
        nameTextField.setText(name);
        nameTextField.setEditable(false);
    
        Object[] receivers = nodeClient.connectedNodes().toArray();
        DefaultComboBoxModel model = new DefaultComboBoxModel(receivers);
        receiverTypeBox.setModel(model);
    
        tabbedPane1.setVisible(true);
        pack();
    }
    
    private void init(){
        receivedText.setEditable(false);
        
        this.setTitle("SOAP");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.setVisible(true);
        this.pack();
    
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
        odswiezButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Object[] receivers = nodeClient.connectedNodes().toArray();
                    DefaultComboBoxModel model = new DefaultComboBoxModel(receivers);
                    receiverTypeBox.setModel(model);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                
            }
        });
    }
    
    
    
}
