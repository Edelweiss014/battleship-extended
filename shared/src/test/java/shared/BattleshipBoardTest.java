package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleshipBoardTest {
	@Test
	public void test_width_height() {
		Board b = new BattleshipBoard(8, 10);
		assertEquals(8, b.getWidth());
		assertEquals(10, b.getHeight());
	}

}
