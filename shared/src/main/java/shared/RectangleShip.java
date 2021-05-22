package shared;

import java.util.HashSet;

public class RectangleShip<T> extends BasicShip<T> {

    /**
     * Name of the ship
     */
    private final String name;

    /**
     * This is a constructor helper function that help to initialize myPieces
     *      in the parent
     * @param upperLeft is the upperleft coordinate of the ship
     * @param width is the width of the ship
     * @param height is the height of the ship
     * @return the hashset of the coordinates
     */
    static HashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height) {
        HashSet<Coordinate> coords = new HashSet<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Coordinate thisCor = new Coordinate(upperLeft.getRow() + i, upperLeft.getColumn() + j);
                coords.add(thisCor);
            }
        }
        return coords;
    }

     /**
     * This is a constructor that will call the basic constructor
     * @param _name is the name of the ship
     * @param _upperLeft is the upperleft coordinate of the ship
     * @param _width is the width of the ship
     * @param _height is the height of the ship
     * @param data shows how to display a ship
     * @param onHit shows how to display a hit point
     */
    public RectangleShip(String _name, Coordinate _upperLeft, int _width, int _height, 
                                ShipDisplayInfo<T> _myDisplayInfo, ShipDisplayInfo<T> _enemyDisplayInfo) {
        super(makeCoords(_upperLeft, _width, _height), _myDisplayInfo, _enemyDisplayInfo);
        this.name = _name;
    }

    /**
     * This is a constructor that will call the basic constructor
     * @param name is the name of the ship
     * @param upperLeft is the upperleft coordinate of the ship
     * @param width is the width of the ship
     * @param height is the height of the ship
     * @param data shows how to display a ship
     * @param onHit shows how to display a hit point
     */
    public RectangleShip(String _name, Coordinate _upperLeft, int _width, int _height, T _data, T _onHit) {
        this(_name, _upperLeft, _width, _height, 
            new SimpleShipDisplayInfo<>(_data, _onHit), new SimpleShipDisplayInfo<>(null, _data));
    }

    /**
     * This is a constructor that news a 1x1 test ship
     * @param upperLeft is the upperleft coordinate of the ship
     * @param data shows how to display a ship
     * @param onHit shows how to display a hit point
     */
    public RectangleShip(Coordinate upperLeft, T data, T onHit) {
        this("testship", upperLeft, 1, 1, data, onHit);
    }

    /**
     * Get the name of the ship
     * @return the name of the ship
     */
    public String getName() {
        return name;
    }

}
