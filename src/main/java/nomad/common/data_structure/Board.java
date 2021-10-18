package nomad.common.data_structure;

import java.io.Serializable;
import java.util.Arrays;

public class Board implements Serializable {

    private Case[][] gameBoard;

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
