package nomad.common.data_structure;

import java.io.Serializable;
import java.util.Arrays;

public class Board implements Serializable {

    private final int BOARD_DIMENSIONS = 13;
    private Case[][] gameBoard;

    public Board(){
        gameBoard = new Case[13][13];
        for(int i = 0; i < BOARD_DIMENSIONS; i++){
            for(int j = 0; j < BOARD_DIMENSIONS; j++){
                gameBoard[i][j] = new Case(i, j, 0, false, false);
            }
        }
    }

    public Board(Case[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Case[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Case[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public String toString() {
        return "Board{" +
                "gameBoard=" + Arrays.toString(gameBoard) +
                '}';
    }
}
