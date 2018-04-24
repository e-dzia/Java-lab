package server;

import java.util.ArrayList;

public class Server {
    
    private final ArrayList<Node> nodeList;
    
    public Server() {
        this.nodeList = new ArrayList<>();
    }
    
    public boolean addNode(String name) {
        name = name.trim();
        Node node = findNode(name);
        
        synchronized (nodeList) {
            if (node != null || name.equals("Broadcast") || name.startsWith("Layer broadcast:") || name.length() < 1){
                return false;
            } else {
                Node n = new Node();
                n.name = name;
                nodeList.add(n);
                return true;
            }
        }
    }
    
    public boolean broadcast(String sender, String messageText) {
        Node senderNode = findNode(sender);
        synchronized (nodeList) {
            if (senderNode != null && messageText.length() > 0){
                for (Node node : nodeList) {
                    Message message = new Message();
                    message.sender = sender;
                    message.receiver = "Broadcast";
                    message.message = messageText.trim();
                    node.messages.add(message);
                }
                return true;
            } else {
                return false;
            }
        }
    }
    
    public boolean layerBroadcast(String sender, String layer, String messageText) {
        Node senderNode = findNode(sender);
        boolean found = false;
        synchronized (nodeList) {
            if (senderNode != null && messageText.length() > 0){
                for (Node node : nodeList) {
                    if (node.name.startsWith(layer)){
                        Message message = new Message();
                        message.sender = sender;
                        message.receiver = "Layer broadcast:"+layer;
                        message.message = messageText.trim();
                        node.messages.add(message);
                        found = true;
                    }
                }
            }
            return found;
        }
    }
    
    //TODO: zmienić, żeby był ring i warstwy
    public boolean sendMessage(String sender, String receiver, String messageText) {
        Node senderNode = findNode(sender);
        Node receiverNode = findNode(receiver);
        
        synchronized (nodeList) {
            if (senderNode == null || receiverNode == null || messageText.length() < 0){
                return false;
            } else {
                Message message1 = new Message();
                message1.sender = sender;
                message1.receiver = receiver;
                message1.message = messageText.trim();
                
                Message message2 = new Message();
                message2.sender = sender;
                message2.receiver = receiver;
                message2.message = messageText.trim();
                
                senderNode.messages.add(message1);
                receiverNode.messages.add(message2);
                
                return true;
            }
        }
    }
    
    public ArrayList<String> getAllMessages(String name) {
        Node node = findNode(name);
        
        synchronized (nodeList) {
            if (node == null){
                return null;
            } else {
                ArrayList<Message> msgs = (ArrayList<Message>) node.messages.clone();
                ArrayList<String> messagesString = new ArrayList <>();
                for (Message message : msgs){
                    messagesString.add(message.toString());
                }
                node.messages.clear();
                return messagesString;
            }
        }
    }
    
    public void removeNode(String name) {
        Node node = findNode(name);
        
        synchronized (nodeList) {
            if (node != null){
                nodeList.remove(node);
            }
        }
    }
    
    public ArrayList<String> connectedNodes() {
        ArrayList<String> nodes = new ArrayList <>();
        synchronized (nodeList) {
            for (Node node : nodeList){
                nodes.add(node.name);
            }
        }
        return nodes;
    }
    
    //TODO: znalezn Node, do ktorych mozna wyslac
    private ArrayList<Node> getNextNodes(String name){
        ArrayList<Node> nodes = new ArrayList <>();
        name = name.trim();
        if (name.length() != 2){
            return null;
        }
        char number = name.charAt(1);
        
        
        return nodes;
    }
    
    private Node findNode(String name) {
        Node foundNode = null;
        
        synchronized (nodeList) {
            for (Node u : nodeList) {
                if ((u.name).equals(name)){
                    foundNode = u;
                }
            }
        }
        return foundNode;
    }
}
