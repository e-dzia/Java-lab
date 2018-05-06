package server;

import java.util.ArrayList;
import java.util.HashSet;

public class Node {
    public String name;
    private ArrayList<Message> messages = new ArrayList<>();
    private HashSet<Integer> messagesIds = new HashSet <>();
    
    public void addMessage(Message message){
        messages.add(message);
        messagesIds.add(message.id);
    }
    
    public boolean findID(int id){
        return messagesIds.contains(id);
    }
    
    public ArrayList<Message> getMessages(){
        return messages;
    }
}
