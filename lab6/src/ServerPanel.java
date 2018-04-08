import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;


public class ServerPanel extends JPanel{
    private JList filesList;
    private JList peersList;
    private JPanel panel;
    private JButton odswiezButton;

    Server server = new Server();

    Vector<Integer> peerIDs = new Vector<>();

    public ServerPanel() {
        odswiezButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateGUI();
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public void updateGUI() throws RemoteException {
        peerIDs.clear();
        for (PeerInterface peer : server.peers){
            peerIDs.addElement(peer.getId());
        }
        peersList.setListData(peerIDs);
        filesList.setListData(server.files);
    }

    private void createUIComponents() {
        panel = this;
    }
}
