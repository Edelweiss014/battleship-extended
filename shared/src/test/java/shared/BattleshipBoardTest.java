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
	public void test_add_ship_and_check() {
		CharShipFactory f = new CharShipFactory();
		PlacementRuleChecker<Character> pc1 = new NoCollisionRuleChecker<>(null);
		PlacementRuleChecker<Character> pc2 = new InBoundsRuleChecker<>(pc1);
		Board<Character> b = new BattleshipBoard<Character>(10, 20, pc2, 'X');
		Coordinate c1 = new Coordinate(1, 4);
		Ship<Character> rs1 = f.makeBattleship(new Placement(c1, 'V'));
		assertEquals(null, b.tryAddShip(rs1));
		Coordinate c2 = new Coordinate(3, 4);
		Ship<Character> rs2 = f.makeCarrier(new Placement(c2, 'H'));
		assertEquals("That placement is invalid: the ship overlaps another ship.", b.tryAddShip(rs2));
		Coordinate c3 = new Coordinate(6, 6);
		Ship<Character> rs3 = f.makeSubmarine(new Placement(c3, 'h'));
		assertEquals(null, b.tryAddShip(rs3));
		Coordinate c4 = new Coordinate(8, 18);
		Ship<Character> rs4 = f.makeDestroyer(new Placement(c4, 'V'));
		assertEquals("That placement is invalid: the ship goes off the right of the board.", b.tryAddShip(rs4));
		Coordinate c5 = new Coordinate(-1, 3);
		Ship<Character> rs5 = f.makeDestroyer(new Placement(c5, 'V'));
		assertEquals("That placement is invalid: the ship goes off the top of the board.", b.tryAddShip(rs5));
		Coordinate c6 = new Coordinate(18, 3);
		Ship<Character> rs6 = f.makeDestroyer(new Placement(c6, 'V'));
		assertEquals("That placement is invalid: the ship goes off the bottom of the board.", b.tryAddShip(rs6));
		Coordinate c7 = new Coordinate(7, -1);
		Ship<Character> rs7 = f.makeDestroyer(new Placement(c7, 'V'));
		assertEquals("That placement is invalid: the ship goes off the left of the board.", b.tryAddShip(rs7));
	}

	@Test
	public void test_fire_at() {
		CharShipFactory f = new CharShipFactory();
		Board<Character> b = new BattleshipBoard<>(20, 20, new NoCollisionRuleChecker<>(null), 'X');
		Coordinate c1 = new Coordinate(1, 4);
		Coordinate c2 = new Coordinate(2, 6);
		Coordinate c3 = new Coordinate(5, 5);
		Coordinate c4 = new Coordinate(4, 4);
		Coordinate c5 = new Coordinate(2, 8);
		Coordinate c6 = new Coordinate(2, 4);
		Coordinate c7 = new Coordinate(1, 4);
		Coordinate c8 = new Coordinate(3, 4);
		Coordinate c9 = new Coordinate(1, 10);
		Ship<Character> s1 = f.makeBattleship(new Placement(c1, 'V'));
		Ship<Character> s2 = f.makeCarrier(new Placement(c2, 'H'));
		b.tryAddShip(s1);
		b.tryAddShip(s2);
		b.fireAt(c4);
		assertEquals(null, b.fireAt(c3));
		assertEquals('X', b.whatIsAtForEnemy(c3));
		assertSame(s1, b.fireAt(c4));
		assertSame(s2, b.fireAt(c5));
		assertEquals(false, s1.isSunk());
		b.fireAt(c6);
		b.fireAt(c7);
		b.fireAt(c8);
		assertEquals(true, s1.isSunk());
		assertEquals(null, b.whatIsAtForSelf(c9));
		assertEquals(null, b.whatIsAtForEnemy(c9));
		assertEquals('c', b.whatIsAtForEnemy(c5));
	}

	@Test
	public void test_all_sunk() {
		CharShipFactory f = new CharShipFactory();
		Board<Character> b = new BattleshipBoard<>(10, 20, new NoCollisionRuleChecker<>(null), 'X');
		Coordinate c1 = new Coordinate(1, 4);
		Coordinate c2 = new Coordinate(2, 6);
		Coordinate c3 = new Coordinate(5, 5);
		Coordinate c4 = new Coordinate(4, 4);
		Coordinate c5 = new Coordinate(2, 8);
		Coordinate c6 = new Coordinate(2, 4);
		Coordinate c7 = new Coordinate(1, 4);
		Coordinate c8 = new Coordinate(3, 4);
		Coordinate c9 = new Coordinate(2, 7);
		Ship<Character> s1 = f.makeBattleship(new Placement(c1, 'V'));
		Ship<Character> s2 = f.makeDestroyer(new Placement(c2, 'H'));
		b.tryAddShip(s1);
		b.tryAddShip(s2);
		b.fireAt(c4);
		assertEquals(false, b.isAllShipSunk());
		b.fireAt(c6);
		b.fireAt(c7);
		b.fireAt(c8);
		assertEquals(false, b.isAllShipSunk());
		b.fireAt(c3);
		assertEquals(false, b.isAllShipSunk());
		b.fireAt(c2);
		b.fireAt(c5);
		b.fireAt(c9);
		assertEquals(true, b.isAllShipSunk());
	}

}
