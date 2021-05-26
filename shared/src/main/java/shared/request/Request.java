package shared.request;

import java.io.Serializable;

/**
 * This is a request interface. It should be sent
 * from the client to the server
 */
public interface Request extends Serializable {
    /**
     * Get the type of the request
     * @return a string representing the request type
     */
    String getRequestType();
}
