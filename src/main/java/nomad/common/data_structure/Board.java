package nomad.common.data_structure;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Represents the board of the game
 */
public class Board implements Serializable {

    /**
     * constant : size of the board (13x13)
     */
    public static final int BOARDDIMENSIONS = 13;

    /**
     * 2-D array of Case object : represents the board
     */
    private Case[][] gameBoard;

    /**
     * Board constructor
     * Initiates an empty 2-D array and each case of the array
     */
    public Board(){
        gameBoard = new Case[13][13];
        for(int i = 0; i < BOARDDIMENSIONS; i++){
            for(int j = 0; j < BOARDDIMENSIONS; j++){
                gameBoard[i][j] = new Case(i, j, 0, false, false);
            }
        }
    }

    /**
     * Initiates a Board with a board already initialized
     * @param gameBoard
     */
    public Board(Case[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Case getCase(int x, int y) {
        if(x < BOARDDIMENSIONS && y < BOARDDIMENSIONS && x >= 0 && y >= 0) {
            return gameBoard[x][y];
        }

        return null;
    }

    /**
     * Returns the gameBoard
     * @return gameBoard - Case[][]
     */
    public Case[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Case[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * Updates the board by adding a tower
     * @param t - Tower object
     */
    public void updateBoard(Tower t){
        gameBoard[t.getX()][t.getY()].setTower(true);
    }

    /**
     * Updates the board by adding a tile
     * @param t - Tile object
     */
    public void updateBoard(Tile t){
        gameBoard[t.getX()][t.getY()].incrementHeight();
        gameBoard[t.getX()][t.getY()].setColor(t.isColor());
    }


    /**
     * Returns a string representation of the object
     * @return String
     */
    @Override
    public String toString() {
        return "Board{" +
                "gameBoard=" + Arrays.toString(gameBoard) +
                '}';
    }
}
