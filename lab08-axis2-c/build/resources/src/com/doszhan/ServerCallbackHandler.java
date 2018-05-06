/**
 * ServerCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */
package com.doszhan;


/**
 *  ServerCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class ServerCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public ServerCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public ServerCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for connectedNodes method
     * override this method for handling normal response from connectedNodes operation
     */
    public void receiveResultconnectedNodes(
        com.doszhan.ServerStub.ConnectedNodesResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from connectedNodes operation
     */
    public void receiveErrorconnectedNodes(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for sendMessage method
     * override this method for handling normal response from sendMessage operation
     */
    public void receiveResultsendMessage(
        com.doszhan.ServerStub.SendMessageResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from sendMessage operation
     */
    public void receiveErrorsendMessage(java.lang.Exception e) {
    }

    // No methods generated for meps other than in-out

    /**
     * auto generated Axis2 call back method for layerBroadcast method
     * override this method for handling normal response from layerBroadcast operation
     */
    public void receiveResultlayerBroadcast(
        com.doszhan.ServerStub.LayerBroadcastResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from layerBroadcast operation
     */
    public void receiveErrorlayerBroadcast(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getAllMessages method
     * override this method for handling normal response from getAllMessages operation
     */
    public void receiveResultgetAllMessages(
        com.doszhan.ServerStub.GetAllMessagesResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getAllMessages operation
     */
    public void receiveErrorgetAllMessages(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for broadcast method
     * override this method for handling normal response from broadcast operation
     */
    public void receiveResultbroadcast(
        com.doszhan.ServerStub.BroadcastResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from broadcast operation
     */
    public void receiveErrorbroadcast(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for addNode method
     * override this method for handling normal response from addNode operation
     */
    public void receiveResultaddNode(
        com.doszhan.ServerStub.AddNodeResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from addNode operation
     */
    public void receiveErroraddNode(java.lang.Exception e) {
    }
}
