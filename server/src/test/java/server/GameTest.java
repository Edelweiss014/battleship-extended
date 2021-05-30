package server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GameTest {

	@Test
	public void test_create_add_player() {
		Game g = new Game(2);
		assertEquals(2, g.getId());
		assertEquals(false, g.containsPlayer("player1"));
		assertEquals(null, g.acceptPlayer("player1"));
		assertEquals(true, g.containsPlayer("player1"));
		assertEquals(false, g.isFull());
		assertEquals("The player is already in the game", g.acceptPlayer("player1"));
		assertEquals(null, g.acceptPlayer("player2"));
		assertEquals(true, g.isFull());
		assertEquals("The game room is already full", g.acceptPlayer("player3"));
	}

}
