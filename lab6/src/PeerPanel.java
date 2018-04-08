import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Vector;

public class PeerPanel extends JPanel {
    private JList filesList;
    private JPanel panel;
    private JButton zarejestrujButton;
    private JButton wyslijButton;
    private JButton pobierzButton;
    private JLabel labelID;
    private JButton odswiezButton;

    private Peer peer = new Peer();
    Vector<String> fileParts = new Vector<>();

    public PeerPanel() {
        zarejestrujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    peer.serverInterface = (ServerInterface) LocateRegistry.getRegistry("localhost", peer.PORT).lookup("Server1");
                    peer.setId(peer.serverInterface.registerNode(peer));
                } catch (RemoteException | NotBoundException e1) {
                    e1.printStackTrace();
                }
                labelID.setText("ID: " + peer.getId());
                peer.createDirectories();
                zarejestrujButton.setEnabled(false);
                updateGUI();
            }
        });
        wyslijButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File folder = new File(".\\Peer" + peer.getId() + "-files");
                File[] possibleValues = folder.listFiles();

                Object selectedValue = JOptionPane.showInputDialog(null,
                        "Choose a file", "Files in your directory",
                        JOptionPane.INFORMATION_MESSAGE, null,
                        possibleValues, possibleValues[0]);
                try {
                    File file = new File(selectedValue.toString());
                    FileInputStream in = new FileInputStream(file);
                    byte [] data = new byte[(int) file.length()]; //TODO: ?
                    int length = in.read(data);
                    System.out.println("Data read");
                    if (length > 0){
                        if (!peer.serverInterface.uploadFile(file.getName(), data)){
                            JOptionPane.showMessageDialog(null,"Error.","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }catch (Exception e1){
                    e1.printStackTrace();
                }
                updateGUI();
            }
        });
        pobierzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                updateGUI();
            }
        });
        odswiezButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGUI();
            }
        });
    }

    public void updateGUI()  {
        fileParts.clear();
        for (String file : peer.filesParts.keySet()){
            fileParts.addElement(file + " " + peer.filesParts.get(file));
        }
        filesList.setListData(fileParts);
    }

    private void createUIComponents() {
        panel = this;
    }
}
