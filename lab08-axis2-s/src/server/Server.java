package server;

import java.util.ArrayList;

public class Server {
    
    private final ArrayList<Node> nodeList;
    private final String MESSAGE_FORWARDED = "(Forwarded)";
    private final String BROADCAST = "BROADCAST";
    private final String LAYER_BROADCAST = "LAYER BROADCAST:";
    
    public Server() {
        this.nodeList = new ArrayList<>();
    }
    
    public boolean addNode(String name) {
        name = name.trim();
        name = name.toUpperCase();
        Node node = findNode(name);
        
        synchronized (nodeList) {
            if (node != null || name.equals(BROADCAST) || name.startsWith(LAYER_BROADCAST) || name.length() < 1){
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
                Message message = new Message(sender, BROADCAST, messageText.trim());
                forwardBroadcast(message, senderNode.name);
                return true;
            } else {
                return false;
            }
        }
    }
    
    public boolean layerBroadcast(String sender, String layer, String messageText) {
        Node senderNode = findNode(sender);
        synchronized (nodeList) {
            if (senderNode != null && messageText.length() > 0){
                Message message = new Message(sender, LAYER_BROADCAST+layer, messageText.trim());
                forwardLayer(message, senderNode.name);
                return true;
            }
            return false;
        }
    }
    
    public boolean sendMessage(String sender, String receiver, String messageText) {
        Node senderNode = findNode(sender);
        Node receiverNode = findNode(receiver);
        
        synchronized (nodeList) {
            if (senderNode == null || receiverNode == null || messageText.length() <= 0){
                return false;
            } else {
                Message message = new Message(sender, receiver, messageText.trim());
                //senderNode.addMessage(message);
                forwardMessage(message, senderNode.name);
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
                ArrayList<Message> messages = (ArrayList<Message>) node.getMessages().clone();
                ArrayList<String> messagesString = new ArrayList <>();
                for (Message message : messages){
                    messagesString.add(message.toString());
                }
                node.getMessages().clear();
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
                    break;
                case 'B':
                    nodes.add(nodeA);
                    nodes.add(nodeC);
                    break;
                case 'C':
                    nodes.add(nodeB);
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
            newname.append('1');
            if (!newname.toString().equals(name)){
                nodeSameLayer = findNode(newname.toString());
                nodes.add(nodeSameLayer);
            }
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
    
    private boolean forwardMessage(Message message, String current){
        synchronized (nodeList) {
            Node currentNode = findNode(current);
            if (currentNode.findID(message.id)){
                return false;
            }
            if (currentNode.name.equals(message.receiver)){
                currentNode.addMessage(message);
                return true;
            } else {
                ArrayList <Node> nextNodes = getNextNodes(current);
                if (nextNodes != null){
                    if (nextNodes.size() > 1){ //czyli A1, B1, C1
                        for (Node node : nextNodes) {
                            if (message.receiver.charAt(0) == node.name.charAt(0)){
                                forward(message, currentNode, node, 'u');
                                return true;
                            }
                        }
                        for (Node node : nextNodes) {
                            if (node.name.charAt(0) == 'B'){
                                forward(message, currentNode, node, 'u');
                                return true;
                            }
                        }
                        return false;
                    } else {
                        forward(message, currentNode, nextNodes.get(0), 'u');
                        return true;
                    }
                }
                else {
                    return false;
                }
            }
        }
    }
    
    private void forward(Message message, Node currentNode, Node nextNode, char type){
        Message tmp = new Message(message.sender, message.receiver, MESSAGE_FORWARDED, message.id);
        currentNode.addMessage(tmp);
        switch(type){
            case 'u':
                forwardMessage(message, nextNode.name);
                break;
            case 'b':
                forwardBroadcast(message, nextNode.name);
                break;
            case 'l':
                forwardLayer(message, nextNode.name);
                break;
        }
    }
    
    private boolean forwardLayer(Message message, String current) {
        synchronized (nodeList) {
            Node currentNode = findNode(current);
            if (currentNode.findID(message.id)){
                return false;
            }
            if (currentNode.name.charAt(0) == message.receiver.split(":")[1].charAt(0)){
                currentNode.addMessage(message);
            }
            ArrayList <Node> nextNodes = getNextNodes(current);
            if (nextNodes == null){
                return false;
            }
            for (Node node : nextNodes) {
                if (node.name.charAt(0) == message.receiver.split(":")[1].charAt(0)){
                    forward(message, currentNode, node, 'l');
                    return true;
                }
            }
            for (Node node : nextNodes) {
                forward(message, currentNode, node, 'l');
            }
            return true;
        }
    }
    
    private boolean forwardBroadcast(Message message, String current){
        synchronized (nodeList) {
            Node currentNode = findNode(current);
            if (currentNode.findID(message.id)){
                return false;
            }
            currentNode.addMessage(message);
            ArrayList <Node> nextNodes = getNextNodes(current);
            if (nextNodes == null){
                return false;
            }
            for (Node node : nextNodes) {
                forward(message, currentNode, node, 'b');
            }
            return true;
        }
    }
}
