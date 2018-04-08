import java.rmi.Remote;
import java.util.Vector;

public interface ServerInterface extends Remote {
    int registerNode(PeerInterface peer) throws java.rmi.RemoteException;
    boolean uploadFile(String filename, byte[] data) throws java.rmi.RemoteException;
    Vector<Peer> getPeersForFile(String filename) throws java.rmi.RemoteException;
}
