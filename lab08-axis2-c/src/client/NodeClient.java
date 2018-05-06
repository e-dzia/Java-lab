package client;

import com.doszhan.ServerStub;
import org.apache.axis2.AxisFault;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

public class NodeClient {
    private String name;
    private final static String END_POINT = "http://localhost:8080/axis2/services/Server?wsdl";
    
    ServerStub stub = null;

    public NodeClient(String name){
        this.name = name;
        try {
            stub = new ServerStub(END_POINT);
            
            
            ServerStub.AddNode addNode = new ServerStub.AddNode();
            addNode.setName(this.name);

            ServerStub.AddNodeResponse response = stub.addNode(addNode);
            boolean returnedWorker = response.get_return();
        } catch (AxisFault axisFault) {
            axisFault.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    
    }

    public String getName() {
        return name;
    }
    
    public boolean sendMessage(String receiverType, String messageText) throws RemoteException {
        if (receiverType.equals("Broadcast")){
            ServerStub.Broadcast broadcast = new ServerStub.Broadcast();
            broadcast.setSender(this.getName());
            broadcast.setMessageText(messageText);
            ServerStub.BroadcastResponse response = stub.broadcast(broadcast);
            return response.get_return();
        }
        else if (receiverType.startsWith("Layer broadcast:")){
            String[] parts = receiverType.split(":");
            if (parts.length > 1){
//                service.layerBroadcast(this.getName(), parts[1], messageText);
                ServerStub.LayerBroadcast layerBroadcast = new ServerStub.LayerBroadcast();
                layerBroadcast.setSender(this.getName());
                layerBroadcast.setLayer(parts[1]);
                layerBroadcast.setMessageText(messageText);
                ServerStub.LayerBroadcastResponse response = stub.layerBroadcast(layerBroadcast);
                return response.get_return();
            }
            else {
                return false;
            }
        }
        else if (receiverType.startsWith("A") || receiverType.startsWith("B") || receiverType.startsWith("C")){
            if (!receiverType.equals(name)){
//                 service.sendMessage(this.getName(), parts[1], messageText);
                ServerStub.SendMessage sendMessage = new ServerStub.SendMessage();
                sendMessage.setSender(this.getName());
                sendMessage.setReceiver(receiverType);
                sendMessage.setMessageText(messageText);
    
                ServerStub.SendMessageResponse response = stub.sendMessage(sendMessage);
                return response.get_return();
            } else {
                return false;
            }
        }
        return false;
    }

    public ArrayList<String> receiveMessages() throws RemoteException {
        ServerStub.GetAllMessages getAllMessages = new ServerStub.GetAllMessages();
        getAllMessages.setName(this.getName());
        
        ServerStub.GetAllMessagesResponse response = stub.getAllMessages(getAllMessages);
        
        String[] strings = response.get_return();
        if (strings != null && strings.length > 0){
            ArrayList<String> messagesList = new ArrayList<>(Arrays.asList(strings));
            return messagesList;
        }
        return null;
    }
    
    public ArrayList<String> connectedNodes() throws RemoteException {
        String[] connectedNodes;
    
        ServerStub.ConnectedNodes connectedNodes1 = new ServerStub.ConnectedNodes();
        ServerStub.ConnectedNodesResponse response = stub.connectedNodes(connectedNodes1);
        
        connectedNodes = response.get_return();
    
        if (connectedNodes != null && connectedNodes.length > 0){
            ArrayList<String> nodes = new ArrayList<>(Arrays.asList(connectedNodes));
            nodes.add("Broadcast");
            if (nodes.contains("A1")){
                nodes.add("Layer broadcast:A");
            }
            if (nodes.contains("B1")){
                nodes.add("Layer broadcast:B");
            }
            if (nodes.contains("C1")){
                nodes.add("Layer broadcast:C");
            }
            return nodes;
        }
        return null;
    }
}
