package shared.checker;

import shared.Board;
import shared.Coordinate;
import shared.ship.Ship;

/**
 * This is a rule checker that checks whether the placement of
 * ship will get out of bound
 */
public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {

    /**
     * A constructor that directly inherits the super
     * @param _next
     */
    public InBoundsRuleChecker(PlacementRuleChecker<T> _next) {
        super(_next);
    }

    /**
     * Check whether the ship will be out of bound
     * @param theShip is the ship to place
     * @param theBoard is the board to place the ship
     * @return the check result; null for no errors
     */
    @Override
    protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
        for (Coordinate c: theShip.getCoordinates()) {
            if (c.getRow() < 0) {
                return "That placement is invalid: the ship goes off the top of the board.";
            }
            if (c.getRow() >= theBoard.getHeight()) {
                return "That placement is invalid: the ship goes off the bottom of the board.";
            }
            if (c.getColumn() < 0) {
                return "That placement is invalid: the ship goes off the left of the board.";
            }
            if (c.getColumn() >= theBoard.getWidth()) {
                return "That placement is invalid: the ship goes off the right of the board.";
            }
        }
        return null;
    }
}
