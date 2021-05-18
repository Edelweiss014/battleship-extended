package shared;

/**
 * This is a battleship board for game playing
 */
public class BattleshipBoard implements Board {

    private final int width;
    private final int height;

    public BattleshipBoard(int _width, int _height) {
        this.width = _width;
        this.height = _height;
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
    
}
