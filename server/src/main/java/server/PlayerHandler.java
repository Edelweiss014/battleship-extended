package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.function.Consumer;

import shared.request.Request;
import shared.response.Response;

public class PlayerHandler extends Thread {
    /**
     * The socket of this connection
     */
    private final Socket clientSocket;

    /**
     * The server which the game connects to
     */
    private final Server server;

    /**
     * The object output stream of this handler
     */
    private ObjectOutputStream objectOut;

    /**
     * The object input stream of this handler
     */
    private ObjectInputStream objectIn;

    /**
     * The mapping from the request type to the action
     */
    private HashMap<String, Consumer<Request>> actionFns;
    
    /**
     * The current user name
     */
    private String currentUsername;

    /**
     * Construct a playerhandler with corresponding socket and
     *      the server
     * @param _clientSocket
     * @param _server
     * @throws IOException
     */
    public PlayerHandler(Socket _clientSocket, Server _server) throws IOException {
        this.clientSocket = _clientSocket;
        this.server = _server;
        this.objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
        this.objectIn = new ObjectInputStream(clientSocket.getInputStream());
        this.currentUsername = "";
        this.actionFns = new HashMap<>();
        initActions();
    }

    /**
     * This function is used to build a mapping from
     * the request type to the action of the PlayerHandler;
     * it works by adding pairs to the HashMap
     */
    public void initActions() {

    }

    /**
     * Read a request from the object input stream
     * @return the received request
     * @throws ClassNotFoundException
     * @throws IOException
     */
    Request receiveRequest() throws ClassNotFoundException, IOException {
        Request r = (Request) objectIn.readObject();
        return r;
    }

    /**
     * Send the response to the client
     * @param r
     */
    public void sendResponse(Response r) {
        System.out.println(r.getClass().toString());
        try {
            objectOut.writeObject(r);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
