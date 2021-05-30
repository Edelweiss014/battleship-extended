package shared.request;

/**
 * This request is used to enable the first player to create
 * a new game and directly join it
 */
public class CreateGameJoinRequest extends BattleshipRequest {

    /**
     * The game type which indicates whether the player
     * wants to play with a human or a computer
     */
    private final String gameType;

    /**
     * The player name 
     */
    private final String playerName;

    /**
     * Constructor: initializes the type and 
     */
    public CreateGameJoinRequest(String _gameType, String _playerName) {
        super(RequestType.CREATE_GAME_JOIN);
        this.gameType = _gameType;
        this.playerName = _playerName;
    }

    /**
     * Get the type of the game
     * @return the type of the game
     */
    public String getGameType() {
        return this.gameType;
    }

    /**
     * Get the player's name
     * @return the name of the player
     */
    public String getPlayerName() {
        return this.playerName;
    }
}
