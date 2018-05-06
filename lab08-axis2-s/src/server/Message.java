package server;

public class Message {
    public String sender;
    public String receiver;
    public String message;
    public int id;
    static int numberOfMessages = 0;
    
    public Message(String sender, String receiver, String message){
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        System.out.println("Message nr " + this.numberOfMessages);
        this.id = this.numberOfMessages++;
    }
    
    public Message(String sender, String receiver, String message, int id){
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "from: " + sender + ", to: " + receiver + ", message: " + message;
    }
}
