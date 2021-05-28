package shared.checker;

import shared.Board;
import shared.Coordinate;
import shared.ship.Ship;

/**
 * This is a rule checker that checks whether the placement of
 * ship will be on another ship
 */
public class NoCollisionRuleChecker<T> extends PlacementRuleChecker<T> {

    /**
     * A constructor that directly inherits the super
     * @param _next
     */
    public NoCollisionRuleChecker(PlacementRuleChecker<T> _next) {
        super(_next);
    }

    /**
     * Check whether the ship will be on another ship
     * @param theShip is the ship to place
     * @param theBoard is the board to place the ship
     * @return the check result; null for no errors
     */
    @Override
    protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
        for (Coordinate c: theShip.getCoordinates()) {
            if (theBoard.whatIsAtForSelf(c) != null) {
                return "That placement is invalid: the ship overlaps another ship.";
            }
        }
        return null;
    }
    
}
