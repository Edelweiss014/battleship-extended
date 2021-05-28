package shared.checker;

import shared.Board;
import shared.ship.Ship;

/**
 * This class is the parent class of all rule checkers. It 
 * implements the "chain of responsiblities" pattern
 */
public abstract class PlacementRuleChecker<T> {

    /**
     * Each rule checker is the node on a chain; this
     *      is its next node
     */
    private final PlacementRuleChecker<T> next;

    /**
     * This is the constructor; it initializes the node with its
     *      next node
     * @param _next is the rule checker after this
     */
    public PlacementRuleChecker(PlacementRuleChecker<T> _next) {
        this.next = _next;
    }

    /**
     * An abstract function for each node to check its own part
     * @param theShip is the ship to place
     * @param theBoard is the board to place the ship
     * @return the check result; null for no errors
     */
    protected abstract String checkMyRule(Ship<T> theShip, Board<T> theBoard);

    /**
     * The function to start from this node and check all rules after it; 
     *      this function actually starts from a node and finishes all
     *      remaining checkings
     * @param theShip is the ship to place
     * @param theBoard is the board to place the ship
     * @return the check result; null for no errors
     */
    public String checkPlacement(Ship<T> theShip, Board<T> theBoard) {
        //if we fail our own rule: stop, because the placement is not legal
        if (checkMyRule(theShip, theBoard) != null) {
            return checkMyRule(theShip, theBoard);
        }
        //other wise, ask the rest of the chain
        if (next != null) {
            return next.checkPlacement(theShip, theBoard);
        }
        //if there are no more rules, then the placement is legal
        return null;
    }
}
