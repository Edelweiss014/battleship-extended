package shared.response;

/**
 * This is the response for CreateGameJoinRequest
 */
public class CreateGameJoinResponse extends BattleshipResponse {

    /**
     * The id of the created game
     */
    private final int gameId;

    /**
     * Basic constructor
     */
    public CreateGameJoinResponse(String _responseResult, String _responseMsg, int _gameId) {
        super(ResponseType.CREATE_GAME_JOIN, _responseResult, _responseMsg);
        this.gameId = _gameId;
    }

    /**
     * Get the id of the created game
     * @return the game id (for failure, the id would be -1)
     */
    public int getGameId() {
        return this.gameId;
    }
}
