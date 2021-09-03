package client;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.function.Consumer;

import shared.request.Request;
import shared.response.Response;
import shared.response.ResponseType;

public class Client {
    /**
     * The client socket
     */
    private Socket clientSocket;

    /**
     * The output stream
     */
    private ObjectOutputStream objectOut;

    /**
     * The input stream
     */
    private ObjectInputStream objectIn;

    /**
     * The action function map
     */
    private HashMap<String, Consumer<Response>> actionFns;

    /**
     * The default constructor
     */
    public Client() {
        // init action functions
        this.actionFns = new HashMap<>();
        initActions();
    }

    /**
     * Init the action function map
     */
    private void initActions() {
        actionFns.put(ResponseType.CREATE_GAME_JOIN, (r) -> workWithCreateGameJoinResponse(r));
    }

    /**
     * Connect to server
     * 
     * @param ipAddr is the IP address
     * @param port   is the port number
     * @throws IOException
     * @throws UnknownHostException
     */
    public void connectToServer(String ipAddr, int port) throws UnknownHostException, IOException {
        this.clientSocket = new Socket(ipAddr, port);
        this.objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
        this.objectIn = new ObjectInputStream(clientSocket.getInputStream());
    }

    /**
     * Send request from server
     * 
     * @param r is the request to send
     * @throws IOException
     */
    public void sendRequest(Request r) throws IOException {
        objectOut.writeObject(r);
    }

    /**
     * Receive response from server
     * 
     * @return the response received
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public Response receivResponse() throws ClassNotFoundException, IOException {
        Response r = (Response) objectIn.readObject();
        return r;
    }

    /**
     * Actions when receiving create game join response
     * 
     * @param r is the response
     */
    private void workWithCreateGameJoinResponse(Response r) {
        if (r.getResponseType().equals(ResponseType.CREATE_GAME_JOIN)) {

        }
    }

    /**
     * The entry point function to handle a response
     * 
     * @param r is the response received
     */
    private void workWithResponse(Response r) {
        actionFns.get(r.getResponseType()).accept(r);
    }

    /**
     * Create a new thread and run
     */
    public void run() {
        Thread th = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Response resp = receivResponse();
                        workWithResponse(resp);
                    } catch (Exception e) {
                        if (e.getClass().equals(EOFException.class)) {
                            continue;
                        }
                        e.printStackTrace();
                    }
                }
            }
        };
        th.start();
    }
}
