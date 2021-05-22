package shared;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SimpleShipDisplayInfoTest {
	@Test
	public void test_ship_display() {
		ShipDisplayInfo<Character> info = new SimpleShipDisplayInfo<Character>('s', 'X');
		Coordinate c1 = new Coordinate(1, 2);
		assertEquals('s', info.getInfo(c1, false));
		assertEquals('X', info.getInfo(c1, true));
	}

}
