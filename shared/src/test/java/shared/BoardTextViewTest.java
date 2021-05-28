package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import shared.ship.CharShipFactory;
import shared.ship.Ship;

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

	@Test
	public void test_display_any() {
		Board<Character> b = new BattleshipBoard<Character>(4, 3, 'X');
		CharShipFactory f = new CharShipFactory();
		Coordinate c1 = new Coordinate(1, 0);
		Coordinate c2 = new Coordinate(0, 3);
		Ship<Character> s1 = f.makeSubmarine(new Placement(c1, 'H'));
		Ship<Character> s2 = f.makeDestroyer(new Placement(c2, 'V'));
		b.tryAddShip(s1);
		b.tryAddShip(s2);
		String myView1 =
			"  0|1|2|3\n" +
			"A  | | |d A\n" +
			"B s|s| |d B\n" +
			"C  | | |d C\n" +
			"  0|1|2|3\n";
		String enView1 =
			"  0|1|2|3\n" +
			"A  | | |  A\n" +
			"B  | | |  B\n" +
			"C  | | |  C\n" +
			"  0|1|2|3\n";
		BoardTextView view = new BoardTextView(b);
		assertEquals(myView1, view.displayMyOwnBoard());
		assertEquals(enView1, view.displayEnemyBoard());
	}

	@Test
	public void test_display_2_boards() {
		CharShipFactory f = new CharShipFactory();
		Board<Character> b = new BattleshipBoard<Character>(4, 3, 'X');
		Coordinate c1 = new Coordinate(1, 0);
		Coordinate c2 = new Coordinate(0, 3);
		Coordinate c3 = new Coordinate(0, 2);
		Ship<Character> s1 = f.makeSubmarine(new Placement(c1, 'H'));
		Ship<Character> s2 = f.makeDestroyer(new Placement(c2, 'V'));
		BoardTextView view = new BoardTextView(b);
		b.tryAddShip(s1);
		b.tryAddShip(s2);
		b.fireAt(c1);
		b.fireAt(c3);
		Board<Character> b2 = new BattleshipBoard<Character>(4, 3, 'X');
		Coordinate c4 = new Coordinate(0, 3);
		Coordinate c5 = new Coordinate(2, 0);
		Coordinate c6 = new Coordinate(1, 1);
		Ship<Character> s3 = f.makeSubmarine(new Placement(c4, 'v'));
		Ship<Character> s4 = f.makeBattleship(new Placement(c5, 'h'));
		BoardTextView view2 = new BoardTextView(b2);
		b2.tryAddShip(s3);
		b2.tryAddShip(s4);
		b2.fireAt(c4);
		b2.fireAt(c6);
		String twoBoards1 =
			"     Your Ocean               Player B's ocean\n" +
			"  0|1|2|3                    0|1|2|3\n" +
			"A  | | |d A                A  | | |s A\n" +
			"B *|s| |d B                B  |X| |  B\n" +
			"C  | | |d C                C  | | |  C\n" +
			"  0|1|2|3                    0|1|2|3\n";
		String twoBoards2 =
			"     Your Ocean               Player A's ocean\n" +
			"  0|1|2|3                    0|1|2|3\n" +
			"A  | | |* A                A  | |X|  A\n" +
			"B  | | |s B                B s| | |  B\n" +
			"C b|b|b|b C                C  | | |  C\n" +
			"  0|1|2|3                    0|1|2|3\n";
		assertEquals(twoBoards1, view.displayMyBoardWithEnemyNextToIt(view2, "Your Ocean", "Player B's ocean"));
		assertEquals(twoBoards2, view2.displayMyBoardWithEnemyNextToIt(view, "Your Ocean", "Player A's ocean"));
	}
}
