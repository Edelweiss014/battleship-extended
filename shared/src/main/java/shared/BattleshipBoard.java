package shared;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * This is a battleship board for game playing
 */
public class BattleshipBoard<T> implements Board<T> {

    private final int width;
    private final int height;
    private HashSet<Coordinate> enemyMisses;
    private final ArrayList<Ship<T>> myShips;
    private final T missInfo;

    public BattleshipBoard(int _width, int _height, T _missInfo) {
        this.width = _width;
        this.height = _height;
        this.enemyMisses = new HashSet<>();
        this.myShips = new ArrayList<Ship<T>>();
        this.missInfo = _missInfo;
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

    protected T whatIsAt(Coordinate where, boolean isSelf) {
        if (!isSelf) {
            if (enemyMisses.contains(where)) {
                return missInfo;
            }
        }
        else {
            for (Ship<T> s: myShips) {
                return s.getDisplayInfoAt(where, isSelf);
            }
        }
        return null;
    }
    
}
