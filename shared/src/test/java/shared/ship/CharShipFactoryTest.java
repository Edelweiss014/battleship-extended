package shared.ship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import shared.Coordinate;
import shared.Placement;

public class CharShipFactoryTest {

	private void checkShip(Ship<Character> testShip, String expectedName, char expectedLetter, Coordinate... expectedLocs) {
		assertEquals(expectedName, testShip.getName());
		for (Coordinate c: expectedLocs) {
			assertEquals(expectedLetter, testShip.getDisplayInfoAt(c, true));
		}
	}

	@Test
	public void test_ship_creation() {
		CharShipFactory f = new CharShipFactory();
		Coordinate c1 = new Coordinate(1, 3);
		char O1 = 'V';
		char O2 = 'h';
		Placement p1 = new Placement(c1, O1);
		Placement p2 = new Placement(c1, O2);
		Ship<Character> s1 = f.makeSubmarine(p1);
		checkShip(s1, "submarine", 's', c1, new Coordinate(2, 3));
		Ship<Character> s2 = f.makeDestroyer(p2);
		checkShip(s2, "destroyer", 'd', c1, new Coordinate(1, 4), new Coordinate(1, 5));
		Ship<Character> s3 = f.makeBattleship(p1);
		checkShip(s3, "battleship", 'b', c1, new Coordinate(2, 3), new Coordinate(3, 3), new Coordinate(4, 3));
		Ship<Character> s4 = f.makeCarrier(p2);
		checkShip(s4, "carrier", 'c', c1, new Coordinate(1, 4), new Coordinate(1, 5), new Coordinate(1, 6), 
								new Coordinate(1, 7), new Coordinate(1, 8));
	}

}
