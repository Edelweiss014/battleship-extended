package shared;

/**
 * This class is used for actions to place a ship on the 
 * board. A placement includes a coordinate and a orientation
 * (horizontal or vertical). 
 */
public class Placement {
    /**
     * Position to place a ship
     */
    private final Coordinate where;
    /**
     * The orientation of the ship
     */
    private final char orientation;

    /**
     * Basic constructor to build a placement
     * @param _where is the position to put the ship
     * @param _orientation is the orientation of the ship
     * @throws IllegalArgumentException if the orientation is
     *      neither H nor V
     */
    public Placement(Coordinate _where, char _orientation) {
        this.where = _where;
        this.orientation = getOrientation(_orientation);
    }

    /**
     * A helper function to check illegal orientation
     *      and return the upper case version
     * @param _orientation is the original orientation char
     * @throws IllegalArgumentException if the orientation is
     *      neither H nor V
     * @return the parsed orientation char
     */
    public char getOrientation(char _orientation) throws IllegalArgumentException {
        _orientation = Character.toUpperCase(_orientation);
        if (_orientation != 'V' && _orientation != 'H') {
            throw new IllegalArgumentException("Orientation should be V or H (case insensitive)");
        }
        return _orientation;
    }

    /**
     * This constructor reads a string of placement
     *      and construct an object
     * @param descr is the placement string
     */
    public Placement(String descr) {
        if (descr.length() != 3) {
            throw new IllegalArgumentException("Invalid placement");
        }
        String descrUpper = descr.toUpperCase();
        this.where = new Coordinate(descrUpper.substring(0, 2));
        this.orientation = getOrientation(descrUpper.charAt(2));
    }

    /**
     * Getters of where and orientation
     * @return where or orientation
     */
    public Coordinate getWhere() {
        return this.where;
    }

    public char getOrientation() {
        return this.orientation;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass().equals(getClass())) {
            Placement p = (Placement) o;
            return this.where.equals(p.where) && this.orientation == p.orientation;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder(this.where.toString());
        ans.append(this.orientation);
        return ans.toString();
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

}
