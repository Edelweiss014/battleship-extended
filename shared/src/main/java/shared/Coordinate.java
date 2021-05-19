package shared;

/**
 * Coordinate: the position on the board
 */
public class Coordinate {
    private final int row;
    private final int column;

    /**
     * Basic constructor with row and col taken
     */
    public Coordinate(int _row, int _column) {
        this.row = _row;
        this.column = _column;
    }

    /**
     * This constructor convert a string
     *      coordinate to a coordinate object
     * @param descr is the string representation
     *      of the coordinate
     */
    public Coordinate(String descr) {
        if (descr.length() != 2) {
            throw new IllegalArgumentException("The descr must contain 2 chars.");
        }
        char rowLetter = descr.charAt(0);
        char columnLetter = descr.charAt(1);
        rowLetter = Character.toUpperCase(rowLetter);
        if (rowLetter < 'A' || rowLetter > 'Z') {
            throw new IllegalArgumentException("The row letter must be between A and Z.");
        }
        if (columnLetter < '0' || columnLetter > '9') {
            throw new IllegalArgumentException("The row letter must be between 0 and 9.");
        }
        this.row = (int)(rowLetter - 'A');
        this.column = (int)(columnLetter - '0');
    }

    /**
     * Getters to get the row and col
     */
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass().equals(getClass())) {
            Coordinate c = (Coordinate) o;
            return row == c.row && column == c.column;
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ")";
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
