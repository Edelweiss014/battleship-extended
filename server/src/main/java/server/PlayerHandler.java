package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.function.Consumer;

import shared.request.CreateGameJoinRequest;
import shared.request.EndSocketRequest;
import shared.request.Request;
import shared.request.RequestType;
import shared.response.CreateGameJoinResponse;
import shared.response.Response;
import shared.response.ResponseResult;

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
        actionFns.put(RequestType.CREATE_GAME_JOIN, (r) -> workWithCreateGameJoin(r));
    }

    /**
     * Get the current player that the thread is handling
     * @return the current username in this thread
     */
    public String getCurrentUsername() {
        return currentUsername;
    }

    /**
     * Work with CreateGameJoinRequest and send corresponding
     *      response
     * @param r is the request
     */
    public void workWithCreateGameJoin(Request r) {
        if (r.getRequestType().equals(RequestType.CREATE_GAME_JOIN)) {
            CreateGameJoinRequest cgr = (CreateGameJoinRequest) r;
            String playerName = cgr.getPlayerName();
            String gameType = cgr.getGameType();
            try {
                int gameId = server.createNewGameAndAdd1Player(gameType, playerName);
                CreateGameJoinResponse res = new CreateGameJoinResponse(ResponseResult.SUCCESS, "Success", gameId);
                sendResponse(res);
            }
            catch (Exception e) {
                CreateGameJoinResponse res = new CreateGameJoinResponse(ResponseResult.FAILURE, e.getMessage(), -1);
                sendResponse(res);
            }
        }
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

    /**
     * Map the request into its corresponding "workWith" function
     * @param r is the received request
     */
    private void workWithRequest(Request r) {
        actionFns.get(r.getRequestType()).accept(r);
    }

    /**
     * The override run() function of the thread
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Request r = receiveRequest();
                workWithRequest(r);
                if (r instanceof EndSocketRequest) {
                    clientSocket.close();
                    break;
                }
            }
            catch (Exception e) {
                continue;
            }
        }
    }
}
