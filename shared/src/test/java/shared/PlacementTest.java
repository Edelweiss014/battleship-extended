package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlacementTest {
	@Test
	public void test_placement_valid() {
		Coordinate c1 = new Coordinate(1, 2);
		Coordinate c3 = new Coordinate(1, 3);
		Placement p1 = new Placement(c1, 'v');
		assertEquals(p1.getWhere(), c1);
		assertEquals(p1.getOrientation(), 'V');
		Placement p3 = new Placement(c3, 'V');
		assertEquals(p3.getWhere(), c3);
		assertEquals(p3.getOrientation(), 'V');
	}

	@Test
	public void test_placement_invalid() {
		Coordinate c1 = new Coordinate(1, 2);
		assertThrows(IllegalArgumentException.class, () -> new Placement(c1, '0'));
		assertThrows(IllegalArgumentException.class, () -> new Placement("b3%"));
		assertThrows(IllegalArgumentException.class, () -> new Placement("b33m"));
	}

	@Test
	public void test_equals() {
		Coordinate c1 = new Coordinate(1, 2);
		Coordinate c2 = new Coordinate(1, 2);
		Placement p1 = new Placement(c1, 'v');
		Placement p2 = new Placement(c2, 'V');
		assertEquals(p1, p2);
		assertNotEquals(p1, "(1, 2)V");
	}

	@Test
	public void test_to_string() {
		Coordinate c1 = new Coordinate(1, 2);
		Placement p1 = new Placement(c1, 'v');
		assertEquals(p1.toString(), "(1, 2)V");
	}

	@Test
	public void test_hashcode() {
		Coordinate c1 = new Coordinate(1, 2);
		Placement p1 = new Placement(c1, 'v');
		Placement p2 = new Placement(c1, 'V');
		assertEquals(p1.hashCode(), p2.hashCode());
	}

}
