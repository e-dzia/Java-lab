import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Vector;


public class ServerPanel extends JPanel{
    private JList filesList;
    private JList peersList;
    private JPanel panel;
    private JButton odswiezButton;

    Server server = new Server();

    Vector<Integer> peerIDs = new Vector<>();
    Vector<String> filesParts = new Vector<>();

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

        filesParts.clear();
        for (Vector<Object> vector : server.files){
            String fileInfo = "";
            for (Object obj: vector){
                fileInfo += obj;
                fileInfo += " ";
            }
            filesParts.add(fileInfo);
        }
        filesList.setListData(filesParts);
    }

    private void createUIComponents() {
        panel = this;
    }
}
