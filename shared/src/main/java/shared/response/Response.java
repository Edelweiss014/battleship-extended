package shared.response;

import java.io.Serializable;

/**
 * This is a response interface. It should be sent
 * from the server to the client
 */
public interface Response extends Serializable {
    /**
     * Get the type of the response
     * @return a string representing the response type
     */
    String getResponseType();
}
