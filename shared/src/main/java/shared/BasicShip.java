package shared;

import java.util.HashMap;

public abstract class BasicShip<T> implements Ship<T> {
    /**
     * Coordinates within a ship and whether it has been hit
     */
    protected HashMap<Coordinate, Boolean> myPieces;
    /**
     * Display info for self/enemy
     */
    protected ShipDisplayInfo<T> myDisplayInfo;
    protected ShipDisplayInfo<T> enemyDisplayInfo;

    /**
     * This constructor adds coordinates into the ship and initializes
     *      the display info
     * @param where is the coordinates that belong to the ship
     * @param _myDisplayInfo is the diplay info for self
     * @param _enemyDisplayInfo is the display info for ememy
     */
    public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> _myDisplayInfo, ShipDisplayInfo<T> _enemyDisplayInfo) {
        this.myDisplayInfo = _myDisplayInfo;
        this.enemyDisplayInfo = _enemyDisplayInfo;
        myPieces = new HashMap<Coordinate, Boolean>();
        for (Coordinate c: where) {
            myPieces.put(c, false);
        }
    }

    public BasicShip(HashMap<Coordinate, Boolean> myPieces, ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo) {
        this.myPieces = myPieces;
        this.enemyDisplayInfo = enemyDisplayInfo;
    }

    /**
	 * Check whether the coordinate is occupied
	 * 		by this ship
	 * @param where is the coordinate to check
	 * @return true if is, false if not
	 */
	@Override
	public boolean occupiesCoordinates(Coordinate where) {
        return myPieces.containsKey(where);
    }

    /**
	 * Check whether the ship is sunk
	 * @return true if is, false if not
	 */
	@Override
	public boolean isSunk() {
		for (Coordinate c: myPieces.keySet()) {
			if (!myPieces.get(c)) {
				return false;
			}
		}
		return true;
	}

    /**
     * Check whether a coordinate is in this ship
     * @param c is the coordinate to be checked
     * @throws IllegalArgumentException if c is not part of the ship
     */
    protected void checkCoordinateInThisShip(Coordinate c) {
		if (!this.occupiesCoordinates(c)) {
			throw new IllegalArgumentException("The coordinate is not in the ship");
		}
	}

    /**
	 * Record place where the ship is hit
     * @param where is the coordinate to check
	 */
	@Override
	public void recordHitAt(Coordinate where) {
		checkCoordinateInThisShip(where);
		myPieces.put(where, true);
	}

    /**
	 * Check whether the ship is hit somewhere
     * @param where is the coordinate to check
	 * @return true if the coordinate was hit, false otherwise
	 */
	@Override
	public boolean wasHitAt(Coordinate where) {
		checkCoordinateInThisShip(where);
		return myPieces.get(where);
	}

    /**
	 * Show what to display for a coordinate
	 * @return the display info accordint to
	 */
	@Override
	public T getDisplayInfoAt(Coordinate where, boolean myShip) {
		boolean wasHit = wasHitAt(where);
		if (myShip) {
			return myDisplayInfo.getInfo(where, wasHit);
		}
		else {
			return enemyDisplayInfo.getInfo(where, wasHit);
		}
	}

	/**
     * Get all of the Coordinates that this Ship occupies
     * @return an Iterable with the coordinates that this Ship occupies
     */
	@Override
	public Iterable<Coordinate> getCoordinates() {
		return myPieces.keySet();
	}

}
