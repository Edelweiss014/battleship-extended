package shared.response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class EndSocketResponseTest {
	
	@Test
	public void test_create_and_gets() {
		EndSocketResponse esr = new EndSocketResponse(ResponseType.END_SOCKET, ResponseResult.SUCCESS, "Success");
		assertEquals(ResponseType.END_SOCKET, esr.getResponseType());
		assertEquals(ResponseResult.SUCCESS, esr.getResponseResult());
		assertEquals("Success", esr.getResponseMsg());
	}

}
