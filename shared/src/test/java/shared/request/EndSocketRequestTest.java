package shared.request;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class EndSocketRequestTest {
	@Test
	public void test_creation_and_get_type() {
		EndSocketRequest esr = new EndSocketRequest(RequestType.END_SOCKET);
		assertEquals(RequestType.END_SOCKET, esr.getRequestType());
	}

}
