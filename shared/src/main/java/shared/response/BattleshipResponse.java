package shared.response;

public abstract class BattleshipResponse implements Response {

    private static final long serialVersionUID = 1L;

    /**
     * The response type
     */
    private final String responseType;

    /**
     * The response result
     */
    private final String responseResult;

    /**
     * The response message
     */
    private final String responseMsg;

    /**
     * This is a basic constructor
     * @param _responseType is the response type
     * @param _responseResult is the response result
     * @param _responseMsg is the response message
     */
    public BattleshipResponse(String _responseType, String _responseResult, String _responseMsg) {
        this.responseType = _responseType;
        this.responseResult = _responseResult;
        this.responseMsg = _responseMsg;
    }
    
    /**
     * Get the type of the response
     * @return a string representing the response type
     */
    @Override
    public String getResponseType() {
        return responseType;
    }

    /**
     * Get the response result
     * @return SUCCESS or FAILURE
     */
    @Override
    public String getResponseResult() {
        return responseResult;
    }

    /**
     * Get the response message
     * @return the response message string
     */
    @Override
    public String getResponseMsg() {
        return responseMsg;
    }
}
