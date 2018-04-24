package server;

public class Message {
    public String sender;
    public String receiver;
    public String message;
    
    @Override
    public String toString() {
        return "from: " + sender + ", to: " + receiver + ", message: " + message;
    }
}
