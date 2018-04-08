import java.rmi.Remote;

public interface PeerInterface extends Remote {
    int id = 0;

    void acceptFileChunk(String filename, int part, byte[] data) throws java.rmi.RemoteException;
    byte[] getFileChunk(String filename) throws java.rmi.RemoteException;

    int getId() throws java.rmi.RemoteException;
    void setId(int id) throws java.rmi.RemoteException;
}
