package shared.request;

/**
 * This request is used to enable the first player to create
 * a new game and directly join it
 */
public class CreateGameJoinRequest extends BattleshipRequest {

    /**
     * Constructor: initializes the type and 
     */
    public CreateGameJoinRequest() {
        super(RequestType.CREATE_GAME_JOIN);
    }
}
