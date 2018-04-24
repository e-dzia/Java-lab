package server;

import java.util.ArrayList;

public class Server {
    
    private final ArrayList<Node> nodeList;
    
    public Server() {
        this.nodeList = new ArrayList<>();
    }
    
    public boolean addNode(String name) {
        name = name.trim();
        name = name.toUpperCase();
        Node node = findNode(name);
        
        synchronized (nodeList) {
            if (node != null || name.equals("BROADCAST") || name.startsWith("LAYER BROADCAST:") || name.length() < 1){
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
                    message.receiver = "BROADCAST";
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
                        message.receiver = "LAYER BROADCAST:"+layer;
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
    
    //TODO: znalezc Node, do ktorych mozna wyslac
    private ArrayList<Node> getNextNodes(String name){
        ArrayList<Node> nodes = new ArrayList <>();
        name = name.trim();
        name = name.toUpperCase();
        if (name.length() != 2){
            return null;
        }
        char number = name.charAt(1);
        if (number == '1'){
            Node nodeA = findNode("A1");
            Node nodeB = findNode("B1");
            Node nodeC = findNode("C1");
            switch (name.charAt(0)){
                case 'A':
                    nodes.add(nodeB);
                    nodes.add(nodeC);
                    break;
                case 'B':
                    nodes.add(nodeA);
                    nodes.add(nodeC);
                    break;
                case 'C':
                    nodes.add(nodeB);
                    nodes.add(nodeA);
                    break;
            }
        }
        
        StringBuilder newname = new StringBuilder();
        newname.append(name.charAt(0));
        newname.append((number+1-'0'));
        Node nodeSameLayer = findNode(newname.toString());
        
        if(nodeSameLayer == null){
            newname = new StringBuilder();
            newname.append(name.charAt(0));
            newname.append('0');
            nodeSameLayer = findNode(newname.toString());
            nodes.add(nodeSameLayer);
        }
        else {
            nodes.add(nodeSameLayer);
        }
        
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
