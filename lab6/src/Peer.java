import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class Peer implements PeerInterface {
    private int id;
    Map<String, Integer> filesParts = new HashMap<>();

    static final int PORT = 12312;
    static final String HOST = "localhost";

    Registry registry;

    ServerInterface serverInterface;

    public Peer(){
        super();

        try {
            String name = "Peer";
            PeerInterface stub = (PeerInterface) UnicastRemoteObject.exportObject(this, 0);
            registry = LocateRegistry.getRegistry(PORT);
            registry.rebind(name, stub);
            System.out.println("Peer bound");
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }

    }

    public void acceptFileChunk(String filename, int part, byte[] data){
        try {
            File file = new File(".\\Peer"+this.getId()+"-parts\\"+filename);
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file,false);
            out.write(data,0,data.length);
            out.flush();
            out.close();

            System.out.println("File accepted");

            filesParts.put(filename,new Integer(part));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public byte[] getFileChunk(String filename){

        byte[] data = new byte[0];

        return data;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JFrame frame = new JFrame();
                frame.setTitle("Peer");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                PeerPanel peerPanel = new PeerPanel();
                frame.setContentPane(peerPanel);
                frame.setVisible(true);
                frame.pack();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void createDirectories() {
        File file = new File(".\\Peer"+this.getId()+"-files");
        if (!file.exists()) {
            file.mkdir();
        }
        file = new File(".\\Peer"+this.getId()+"-parts");
        if (!file.exists()) {
            file.mkdir();
        }
    }
}
