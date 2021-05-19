package shared;

/**
 * This is a interface for ship display
 */
public interface ShipDisplayInfo<T> {
    /**
     * Get the display info of one coordinate in a ship
     */
    public T getInfo(Coordinate where, boolean hit);
}
