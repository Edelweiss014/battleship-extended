package shared.request;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import shared.gamesetting.GameType;

public class CreateGameJoinRequestTest {
	
	@Test
	public void test_create_and_get() {
		CreateGameJoinRequest cgr = new CreateGameJoinRequest(GameType.WITH_HUMAN, "1");
		assertEquals(RequestType.CREATE_GAME_JOIN, cgr.getRequestType());
		assertEquals(GameType.WITH_HUMAN, cgr.getGameType());
		assertEquals("1", cgr.getPlayerName());
	}

}
