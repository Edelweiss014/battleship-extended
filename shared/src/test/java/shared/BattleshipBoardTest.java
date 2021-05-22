package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleshipBoardTest {
	@Test
	public void test_width_height() {
		Board<Character> b = new BattleshipBoard<>(8, 10, 'X');
		assertEquals(8, b.getWidth());
		assertEquals(10, b.getHeight());
	}

	@Test
	public void test_invalid_dimensions() {
		assertThrows(IllegalArgumentException.class, () -> new BattleshipBoard<Character>(10, 0, 'X'));
		assertThrows(IllegalArgumentException.class, () -> new BattleshipBoard<Character>(0, 20, 'X'));
		assertThrows(IllegalArgumentException.class, () -> new BattleshipBoard<Character>(10, -5, 'X'));
		assertThrows(IllegalArgumentException.class, () -> new BattleshipBoard<Character>(-8, 20, 'X'));
	}

	private <T> void checkWhatIsAtBoard(BattleshipBoard<T> b, T[][] expected) {
		int width = b.getWidth();
		int height = b.getHeight();
		for (int i = 0; i < height - 1; i++) {
			for (int j = 0; j < width - 1; j++) {
				Coordinate c = new Coordinate(i, j);
				T hereInfo = b.whatIsAtForSelf(c);
				assertEquals(hereInfo, expected[i][j]);
			}
		}
	}

	@Test
	public void test_display_info() {
		BattleshipBoard<Character> b = new BattleshipBoard<Character>(12, 9, 'X');
		Character [][] ansBoard = new Character[9][12];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 11; j++) {
				ansBoard[i][j] = null;
			}
		}
		checkWhatIsAtBoard(b, ansBoard);
		Coordinate c1 = new Coordinate(1, 4);
		Coordinate c2 = new Coordinate(7, 3);
		RectangleShip<Character> s1 = new RectangleShip<Character>(c1, 's', '*');
		RectangleShip<Character> s2 = new RectangleShip<Character>(c2, 's', '*');
		b.tryAddShip(s1);
		b.tryAddShip(s2);
		ansBoard[1][4] = 's';
		ansBoard[7][3] = 's';
		checkWhatIsAtBoard(b, ansBoard);
	}

	@Test
	public void test_fire_at() {
		CharShipFactory f = new CharShipFactory();
		
	}
}
