package shared;

/**
 * This is a class of ship display info; it returns how the ship
 * coordinate can be displayed when it is hit or not
 */
public class SimpleShipDisplayInfo<T> implements ShipDisplayInfo<T> {
    /**
     * The way a ship is normally like
     */
    private final T myData;
    /**
     * The display data when the coordinate is hit
     */
    private final T onHit;

    public SimpleShipDisplayInfo(T _myData, T _onHit) {
        this.myData = _myData;
        this .onHit = _onHit;
    }

    /**
     * Get the display information of a certain coordinate
     * in a ship
     * @param where is the coordinate to check
     * @param hit is whether the coordinate is hit
     * @return the display information of the coordinate
     */
    @Override
    public T getInfo(Coordinate where, boolean hit) {
        if (hit) {
            return onHit;
        }
        else {
            return myData;
        }
    }

}
