package shared;

/**
 * This is the ship factory for this battleship game
 */

public class CharShipFactory implements AbstractShipFactory<Character> {
    /**
     * This is a helper function to help create different ships
     * @param where is the coordinate to place
     * @param w is the width of the ship
     * @param h is the height of the ship
     * @param letter is the letter representation of the ship
     * @param name is the name of the ship
     * @return the newly created ship
     */
    protected Ship<Character> createShip(Placement where, int w, int h, char letter, String name) {
        char orientation = where.getOrientation();
        if (orientation == 'V') {
            return new RectangleShip<Character>(name, where.getWhere(), w, h, letter, '*');
        }
        else {
            return new RectangleShip<Character>(name, where.getWhere(), h, w, letter, '*');
        }
    }

    /**
     * Make a submarine
     * @param where specifies the location and orientation
     *      of the ship to make
     * @return the Ship created for the submarine
     */
    @Override
    public Ship<Character> makeSubmarine(Placement where) {
        return createShip(where, 1, 2, 's', "submarine");
    }

    /**
     * Make a battleship
     * @param where specifies the location and orientation of the 
     *      ship to make
     * @return the Ship created for the battleship
     */
    @Override
    public Ship<Character> makeBattleship(Placement where) {
        return createShip(where, 1, 4, 'b', "battleship");
    }

    /**
     * Make a carrier
     * @param where where specifies the location and orientation of the 
     *      ship to make
     * @return the Ship created for the carrier
     */
    @Override
    public Ship<Character> makeCarrier(Placement where) {
        return createShip(where, 1, 6, 'c', "carrier");
    }

    /**
     * Make a destroyer
     * @param where where specifies the location and orientation of the 
     *      ship to make
     * @return the Ship created for the destroyer
     */
    @Override
    public Ship<Character> makeDestroyer(Placement where) {
        return createShip(where, 1, 3, 'd', "destroyer");
    }
}
