package shared.request;

/**
 * This is an abstract class for requests in battleship
 * game; all requests should extend it
 */
public abstract class BattleshipRequest implements Request {

    private static final long serialVersionUID = 1L;

    /**
     * The request type
     */
    private final String requestType;

    /**
     * The constructor
     * @param _requestType is the request type
     */
    public BattleshipRequest(String _requestType) {
        this.requestType = _requestType;
    }

    /**
     * Get the request type
     */
    @Override
    public String getRequestType() {
        return requestType;
    }
}
