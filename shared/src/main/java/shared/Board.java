package shared;

/**
 * This is a board interface
 */
public interface Board<T> {
    /**
     * Get the width and height of the board
     * @return width or height
     */
    public int getWidth();
    public int getHeight();
    /**
     * Display info of a certain coordinate
     */
    public T whatIsAtForSelf(Coordinate where);
    public T whatIsAtForEnemy(Coordinate where);
    /**
     * Try to add ships on to the board
     */
    public String tryAddShip(Ship<T> toAdd);
    /**
     * Fire at the board, get the ship that is fired
     */
    public Ship<T> fireAt(Coordinate c);
    /**
     * Check whether all ships are sunk
     */
    public boolean isAllShipSunk();
}
