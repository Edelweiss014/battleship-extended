package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import shared.response.Response;

/**
 * This is the server class to deal with every request
 * from the client side
 */
public class Server {
    /**
     * The server socket to accept requests
     */
    protected ServerSocket serverSocket;

    /**
     * The ArrayList that stores all client sockets
     */
    protected ArrayList<Socket> clientSockets;

    /**
     * The ID of the next game (which starts from 0)
     */
    private int nextGameId;

    /**
     * The hash map to store games
     */
    private HashMap<Integer, Game> games;

    /**
     * The list of player handler
     */
    private ArrayList<PlayerHandler> playerHandlers;

    /**
     * Given the port, construct a game server
     * @param port is the port to start the server
     * @throws IOException
     */
    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.clientSockets = new ArrayList<>();
        this.playerHandlers = new ArrayList<>();
        this.nextGameId = 0;
        this.games = new HashMap<>();
    }

    /**
     * Accept a connection from a client and add it to the 
     *      socket list
     * @return the accepted client
     * @throws IOException
     */
    public Socket acceptOneClient() throws IOException {
        Socket socket = serverSocket.accept();
        clientSockets.add(socket);
        return socket;
    }

    /**
     * Broadcast a message to all players
     * @param r is the response to send
     */
    public void sendResponseToAll(Response r) {
        for (PlayerHandler ph : playerHandlers) {
            ph.sendResponse(r);
        }
    }

    /**
     * Check whether a certain game exists
     * @param gameId is the game id
     */
    private void checkGameExist(int gameId) {
        if (!games.containsKey(gameId)) {
            throw new IllegalArgumentException("Game does not exist");
        }
    }

    /**
     * Given the id, get the required game
     * @param id is the game id to get
     * @return the corresponding game
     */
    public Game getGameById(int gameId) {
        checkGameExist(gameId);
        return games.get(gameId);
    }

    /**
     * Broadcase a message to all players that are in
     *      a gme
     * @param r is the response to send
     * @param gameId is the game to send the message
     */
    public void sendResponseToAllInGame(Response r, int gameId) {
        Game g = games.get(gameId);
        for (PlayerHandler p : playerHandlers) {
            if (g.containsPlayer(p.getCurrentUsername())) {
                p.sendResponse(r);
            }
        }
    }

    /**
     * Create a new game with a new id
     * @return the game id
     */
    public int createNewGameAndAdd1Player(String gameType, String playerName) {
        Game game = new Game(nextGameId);
        games.put(nextGameId, game);
        String addResult = game.acceptPlayer(playerName);
        if (addResult != null) {
            throw new IllegalArgumentException(addResult);
        }
        nextGameId++;
        return game.getId();
    }

    /**
     * The workflow of the server: accept a client, start a
     * new thread, and communicate with it
     */
    public void run() {
        while(true) {
            try {
                Socket clientSocket = this.acceptOneClient();
                PlayerHandler p = new PlayerHandler(clientSocket, this);
                playerHandlers.add(p);
                p.start();
            }
            catch (Exception e) {
                continue;
            }
        }
    }
}
