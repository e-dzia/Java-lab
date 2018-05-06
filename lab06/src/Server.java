import sun.rmi.transport.proxy.RMIDirectSocketFactory;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RMISocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Server implements ServerInterface{
    Vector<PeerInterface> peers = new Vector<>();
    Vector<Vector<Object>> files = new Vector<>();

    static final int PORT = 12312;
    static final String HOST = "localhost";

    ServerInterface serverInterface;// namiastka przekazywana do metody

    private Registry registry;

    private int registeredPeers = 0;

    public Server(){
        super();
        try {
            RMISocketFactory.setSocketFactory(new RMIDirectSocketFactory());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            registry = LocateRegistry.createRegistry(PORT);
        } catch (RemoteException e) {
            System.out.println("Remote exception");
        }

        registerInterface();
    }

    public int registerNode(PeerInterface peer){
        try {
            peer.setId(registeredPeers++);
            peers.add(peer);
            return peer.getId();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean uploadFile(String filename, byte[] data){
        Vector<Object> file = new Vector<>();
        file.add(filename);

        if (peers.size()<3) {return false;}
        try{
            int length = data.length/3;
            Integer[] peersIDs = new Integer[3];
            peersIDs = get3RandomPeers().toArray(peersIDs);
            System.out.println("Length to send: " + data.length);

            byte[] part1 = new byte[length];
            for(int i = 0; i < length; i++) {part1[i] = data[i];}
            peers.elementAt(peersIDs[0]).acceptFileChunk(filename,1,part1);
            System.out.println("First part sent, length: " + length);
            file.add(peersIDs[0]);

            byte[] part2 = new byte[length];
            for (int i = length, j = 0; i < 2*length; i++, j++) {part2[j] = data[i];}
            peers.elementAt(peersIDs[1]).acceptFileChunk(filename,2,part2);
            System.out.println("Second part sent, length: " + length);
            file.add(peersIDs[1]);

            byte[] part3 = new byte[data.length-2*length];
            for (int i = 2*length, j = 0; i < data.length; i++, j++) {part3[j] = data[i];}
            peers.elementAt(peersIDs[2]).acceptFileChunk(filename,3,part3);
            System.out.println("Third part sent, length: " + (data.length-2*length));
            file.add(peersIDs[2]);
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
        files.add(file);
        return true;
    }

    public Vector<PeerInterface> getPeersForFile(String filename){
        if (!findFile(filename)) return null;
        Vector<PeerInterface> peersForFile = new Vector<>();
        for (Vector<Object> vec : files){
            if (vec.elementAt(0).equals(filename)) {
                for (int i = 1; i <= 3; i++){
                    peersForFile.add(peers.elementAt((Integer) vec.elementAt(i)));
                }
                break;
            }
        }
        return peersForFile;
    }

    private boolean findFile(String filename) {
        boolean isFound = false;
        for (Vector<Object> vec : files){
            if (vec.elementAt(0).equals(filename)) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

    private void registerInterface(){
        try {
            String name = "Server1";
            serverInterface = (ServerInterface) UnicastRemoteObject.exportObject(this, 0);
            registry.rebind(name, serverInterface);
            System.out.println("Server bound");
        } catch (RemoteException e1) {
            e1.printStackTrace();
        }
    }

    private SortedSet<Integer> get3RandomPeers() {
        SortedSet<Integer> peersIDs = new TreeSet<>();
        Random random = new Random();
        while(peersIDs.size() < 3){
            peersIDs.add(random.nextInt(peers.size()));
        }
        return peersIDs;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JFrame frame = new JFrame();
                frame.setTitle("Server");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                ServerPanel serverPanel = new ServerPanel();
                frame.setContentPane(serverPanel);
                frame.setVisible(true);
                frame.pack();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

}
