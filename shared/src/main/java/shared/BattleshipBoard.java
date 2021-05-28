package shared;

import java.util.ArrayList;
import java.util.HashSet;

import shared.checker.InBoundsRuleChecker;
import shared.checker.PlacementRuleChecker;
import shared.ship.Ship;

/**
 * This is a battleship board for game playing
 */
public class BattleshipBoard<T> implements Board<T> {

    /**
     * The width of the board
     */
    private final int width;
    /**
     * The height of the board
     */
    private final int height;
    /**
     * The coordinates that enemy misses
     */
    private HashSet<Coordinate> enemyMisses;
    /**
     * Ships on the board
     */
    private final ArrayList<Ship<T>> myShips;
    /**
     * The head of the rule checker chain
     */
    private final PlacementRuleChecker<T> placementChecker;
    /**
     * The display info for the missed coords
     */
    private final T missInfo;

    /**
     * The basic constructor with all information provided
     * @param _width is the width of the board
     * @param _height is the height of the board
     * @param _checker is the rule checker of the board
     * @param _missInfo is the missinfo
     */
    public BattleshipBoard(int _width, int _height, PlacementRuleChecker<T> _checker, T _missInfo) {
        if (_width <= 0 || _height <= 0) {
            throw new IllegalArgumentException("Dimensions must not be less or equal to 0");
        }
        this.width = _width;
        this.height = _height;
        this.placementChecker = _checker;
        this.enemyMisses = new HashSet<>();
        this.myShips = new ArrayList<Ship<T>>();
        this.missInfo = _missInfo;
    }

    /**
     * The basic constructor with all information provided
     *      except the rule checker (which means that the rule
     *      checker will be the default value)
     * @param _width is the width of the board
     * @param _height is the height of the board
     * @param _checker is the rule checker of the board
     * @param _missInfo is the missinfo
     */
    public BattleshipBoard(int _width, int _height, T _missInfo) {
        this(_width, _height, new InBoundsRuleChecker<T>(null), _missInfo);
    }

    /**
     * Get the width/height of a board
     */
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public T whatIsAtForSelf(Coordinate where) {
        return whatIsAt(where, true);
    }

    @Override
    public T whatIsAtForEnemy(Coordinate where) {
        return whatIsAt(where, false);
    }

    /**
     * Return the display info of a certain coordinate, depending
     *      on whether self or enemy wants it
     * @param where is the coordinate to check
     * @param isSelf indicates whether the info is asked by self
     * @return missInfo for miss, ship display info for ship, null
     *      if empty
     */
    protected T whatIsAt(Coordinate where, boolean isSelf) {
        for (Ship<T> s: myShips) {
            if (s.occupiesCoordinates(where)) {
                return s.getDisplayInfoAt(where, isSelf);
            }
        }
        if (!isSelf && enemyMisses.contains(where)) {
            return missInfo;
        }
        return null;
    }
    
    /**
     * Try to add ship on the board
     * @param toAdd is the ship to add
     * @return the check result, null for success
     */
    @Override
    public String tryAddShip(Ship<T> toAdd) {
        String checkResult = this.placementChecker.checkPlacement(toAdd, this);
        if (checkResult != null) {
            return checkResult;
        }
        myShips.add(toAdd);
        return null;
    }

    /**
     * Fire at the board, record the result
     * @param c is the coord to be fired
     * @return the ship that is fired, null if no ship fired
     */
    @Override
    public Ship<T> fireAt(Coordinate c) {
        for (Ship<T> s: myShips) {
            if (s.occupiesCoordinates(c)) {
                s.recordHitAt(c);
                return s;
            }
        }
        enemyMisses.add(c);
        return null;
    }

    /**
     * Check whether all ships are sunk
     * @return true if all sunk, false otherwise
     */
    @Override
    public boolean isAllShipSunk() {
        for (Ship<T> s: myShips) {
            if (!s.isSunk()) {
                return false;
            }
        }
        return true;
    }
}
