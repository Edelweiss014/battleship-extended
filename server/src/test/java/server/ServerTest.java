package server;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import shared.gamesetting.GameType;

public class ServerTest {

	@Test
	public void test_add_game() throws IOException {
		Server server = new Server(11335);
		int id = server.createNewGameAndAdd1Player(GameType.WITH_COMPUTER, "1");
		assertEquals(0, id);
		assertEquals(0, server.getGameById(0).getId());
		assertThrows(IllegalArgumentException.class, () -> server.getGameById(1));
	}

}
