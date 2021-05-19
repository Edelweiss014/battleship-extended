package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BoardTextViewTest {
	@Test
	public void test_() {
		Board<Character> b1 = new BattleshipBoard<>(2, 2, 'X');
		BoardTextView view = new BoardTextView(b1);
		String expectedHeader = "  0|1\n";
		assertEquals(expectedHeader, view.makeHeader());
		String expected = 
			expectedHeader + 
			"A  |  A\n" +
			"B  |  B\n" + 
			expectedHeader;
		assertEquals(expected, view.displayMyOwnBoard());
	}

	@Test
	public void test_invalid_board_size() {
		Board<Character> wideBoard = new BattleshipBoard<>(11, 20, 'X');
		Board<Character> tallBoard = new BattleshipBoard<>(10, 27, 'X');
		assertThrows(IllegalArgumentException.class, () -> new BoardTextView(wideBoard));
		assertThrows(IllegalArgumentException.class, () -> new BoardTextView(tallBoard));
	}
}
