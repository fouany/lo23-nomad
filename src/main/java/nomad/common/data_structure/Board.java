package nomad.common.data_structure;

import java.io.Serializable;
import java.util.Arrays;

public class Board implements Serializable {

    public static final int BOARDDIMENSIONS = 13;
    private Case[][] gameBoard;

    public Board(){
        gameBoard = new Case[13][13];
        for(int i = 0; i < BOARDDIMENSIONS; i++){
            for(int j = 0; j < BOARDDIMENSIONS; j++){
                gameBoard[i][j] = new Case(i, j, 0, false, false);
            }
        }
    }

    public Board(Case[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Case getCase(int x, int y) {
        if(x < BOARDDIMENSIONS && y < BOARDDIMENSIONS && x >= 0 && y >= 0) {
            return gameBoard[x][y];
        }

        return null;
    }

    public Case[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Case[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void updateBoard(Tower t){
        gameBoard[t.getX()][t.getY()].setTower(true);
    }

    public void updateBoard(Tile t){
        gameBoard[t.getX()][t.getY()].setColor(t.isColor());
    }

    @Override
    public String toString() {
        return "Board{" +
                "gameBoard=" + Arrays.toString(gameBoard) +
                '}';
    }
}
