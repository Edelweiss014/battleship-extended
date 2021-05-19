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
}
