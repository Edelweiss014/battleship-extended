package shared;

import java.util.function.Function;

/**
 * This class handles textual display of
 * a Board (i.e., converting it to a string to show
 * to the user).
 * It supports two ways to display the Board:
 * one for the player's own board, and one for the 
 * enemy's board.
 */
public class BoardTextView {
    /**
     * The board to display on the screen
     */
    private final Board<Character> toDisplay;

    /**
     * Constructs a BoardView, given the board it will display.
     * @param _toDisplay is the board to display
     * @throws IllegalArgumentException if the board is too large to display
     */
    public BoardTextView(Board<Character> _toDisplay) {
        this.toDisplay = _toDisplay;
        if (toDisplay.getWidth() > 10 || toDisplay.getHeight() > 26) {
            throw new IllegalArgumentException("Board must be no larger than 10x26, but is " 
                + toDisplay.getWidth() + "x" + toDisplay.getHeight());
        }
    }

    /**
     * Display the player's/enemy's board
     * @param getSquareFn is the function to get the content
     * @return String formed display message
     */
    protected String displayAnyBoard(Function<Coordinate, Character> getSquareFn) {
        StringBuilder boardView = new StringBuilder("");
        boardView.append(makeHeader());
        boardView.append(makeBody(getSquareFn));
        boardView.append(makeHeader());
        return boardView.toString();
    }

    /**
     * This function creates the header line,
     *      e.g. 0|1|2|3|4\n
     * @return the String that is the header 
     *      line of the given board
     */
    String makeHeader() {
        StringBuilder ans = new StringBuilder("  ");
        String sep = "";
        for (int i = 0; i < toDisplay.getWidth(); i++) {
            ans.append(sep);
            ans.append(i);
            sep = "|";
        }
        ans.append("\n");
        return ans.toString();
    }

    /**
     * This function creates the body line,
     *      e.g. 0|1|2|3|4\n
     * @return the String that is the body 
     *      line of the given board
     */
    String makeBodyLine(int i, Function<Coordinate, Character> getSquareFn) {
        Character lineNum = (char)(65 + i);
        StringBuilder ans = new StringBuilder(lineNum + " ");
        String sep = "";
        for (int j = 0; j < toDisplay.getWidth(); j++) {
            ans.append(sep);
            Coordinate here = new Coordinate(i, j);
            Character hereInfo = getSquareFn.apply(here);
            if (hereInfo == null) {
                ans.append(" ");
            }
            else {
                ans.append(hereInfo);
            }
            sep = "|";
        }
        ans.append(" " + lineNum + "\n");
        return ans.toString();
    }
    
    /**
     * This function makes the body of the board
     * @param getSquareFn is the function to get the square content
     * @return the string-formed body of the board
     */
    String makeBody(Function<Coordinate, Character> getSquareFn) {
        StringBuilder ans = new StringBuilder("");
        for (int i = 0; i < toDisplay.getHeight(); i++) {
            ans.append(makeBodyLine(i, getSquareFn));
        }
        return ans.toString();
    }

    /**
     * This function display player's own board
     * @return the string representation of the player's own board
     */
    public String displayMyOwnBoard() {
        return displayAnyBoard((c)->toDisplay.whatIsAtForSelf(c));
    }

    /**
     * This function display enemy's board
     * @return the string representation of the enemy's board
     */
    public String displayEnemyBoard() {
        return displayAnyBoard((c)->toDisplay.whatIsAtForEnemy(c));
    }

    /**
     * This function handles the final game display with both
     *      player's board and enemy's board
     * @param enemyView is the enemy's board text view
     * @param myHeader is the current player's header
     * @param enemyHeader is the enemy's header
     * @return the string representation of the 2 board's view
     */
    public String displayMyBoardWithEnemyNextToIt(BoardTextView enemyView, String myHeader, String enemyHeader) {
        int firstStart_h = 5;
        int secondStart_h = toDisplay.getWidth() * 2 + 22;
        int secondStart_b = toDisplay.getWidth() * 2 + 19;
        String myTextBoard = displayMyOwnBoard();
        String enemyTextBoard = enemyView.displayEnemyBoard();
        StringBuilder result = new StringBuilder("");
        // append the header
        result.append(" ".repeat(firstStart_h));
        result.append(myHeader);
        result.append(" ".repeat(secondStart_h - firstStart_h - myHeader.length()));
        result.append(enemyHeader);
        result.append("\n");
        
        // append the board
        String [] myTextLines = myTextBoard.split("\n");
        String [] enemyTextLines = enemyTextBoard.split("\n");
        for (int i = 0; i < toDisplay.getHeight() + 2; i++) {
            result.append(myTextLines[i]);
            result.append(" ".repeat(secondStart_b - myTextLines[i].length()));
            result.append(enemyTextLines[i]);
            result.append("\n");
        }
        return result.toString();
    }


}
