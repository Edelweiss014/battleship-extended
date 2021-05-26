package shared;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class RectangleShipTest {
	@Test
	public void test_hash_set() {
		HashSet<Coordinate> expected = new HashSet<>();
		Coordinate c1 = new Coordinate(1, 4);
		Coordinate c2 = new Coordinate(1, 5);
		Coordinate c3 = new Coordinate(1, 6);
		Coordinate c4 = new Coordinate(2, 4);
		Coordinate c5 = new Coordinate(2, 5);
		Coordinate c6 = new Coordinate(2, 6);
		expected.add(c1);
		expected.add(c2);
		expected.add(c3);
		expected.add(c4);
		expected.add(c5);
		expected.add(c6);
		HashSet<Coordinate> actual = RectangleShip.makeCoords(c1, 3, 2);
		assertEquals(expected, actual);
	}

	@Test
	public void test_ship_creation() {
		Coordinate c1 = new Coordinate(1, 4);
		Coordinate c2 = new Coordinate(1, 5);
		Coordinate c3 = new Coordinate(1, 6);
		Coordinate c4 = new Coordinate(2, 4);
		Coordinate c5 = new Coordinate(2, 5);
		Coordinate c6 = new Coordinate(2, 6);
		HashSet<Coordinate> toBuild = new HashSet<>();
		toBuild.add(c1);
		toBuild.add(c2);
		toBuild.add(c3);
		toBuild.add(c4);
		toBuild.add(c5);
		toBuild.add(c6);
		RectangleShip<Character> rs1 = new RectangleShip<Character>("submarine", c1, 3, 2, 's', '*');
		assertEquals(rs1.occupiesCoordinates(c1), true);
		assertEquals(rs1.occupiesCoordinates(c2), true);
		assertEquals(rs1.occupiesCoordinates(c3), true);
		assertEquals(rs1.occupiesCoordinates(c4), true);
		assertEquals(rs1.occupiesCoordinates(c5), true);
		assertEquals(rs1.occupiesCoordinates(c6), true);
		assertEquals(rs1.occupiesCoordinates(new Coordinate(3,3)), false);
		assertEquals(rs1.getName(), "submarine");
	}

	@Test
	public void test_error_fire() {
		Coordinate c1 = new Coordinate(1, 4);
		RectangleShip<Character> rs1 = new RectangleShip<Character>("submarine", c1, 3, 2, 's', '*');
		assertThrows(IllegalArgumentException.class, () -> rs1.recordHitAt(new Coordinate(0, 0)));
	}

}
