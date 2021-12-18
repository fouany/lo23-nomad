package nomad.data.server;

import nomad.common.data_structure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * Tests the checkGameEnded method, called whenever the game wants to know if a game is ended.
 */
public class EndGameTest {

    /**
     * Tests the checkGameEnded method.
     */
    @Test
    public void test(){

        Game g = new Game(new Player(UUID.randomUUID(), "testLogin", "testPicture"), 5, "test", null);
        Board board = new Board();
        Case[][] gb = board.getGameBoard();

        gb[3][11].setTower(true);
        gb[3][6].setTower(true);
        gb[8][5].setTower(true);
        gb[5][7].setTower(true);
        gb[10][1].setTower(true);
        g.getTowers().add(new Tower(3, 11));
        g.getTowers().add(new Tower(3, 6));
        g.getTowers().add(new Tower(8, 5));
        g.getTowers().add(new Tower(5, 7));
        g.getTowers().add(new Tower(10, 1));

        gb[3][10].setColor(true);
        gb[3][9].setColor(true);
        gb[3][8].setColor(true);
        gb[3][7].setColor(true);
        gb[3][10].setHeight(2);
        gb[3][9].setHeight(1);
        gb[3][8].setHeight(2);
        gb[3][7].setHeight(3);

        gb[4][3].setColor(true);
        gb[5][3].setColor(true);
        gb[6][3].setColor(true);
        gb[7][3].setColor(true);
        gb[8][3].setColor(true);
        gb[9][3].setColor(true);
        gb[10][3].setColor(true);
        gb[4][3].setHeight(1);
        gb[5][3].setHeight(2);
        gb[6][3].setHeight(3);
        gb[7][3].setHeight(1);
        gb[8][3].setHeight(2);
        gb[9][3].setHeight(3);
        gb[10][3].setHeight(1);

        gb[5][4].setColor(true);
        gb[5][4].setHeight(1);
        gb[5][5].setColor(true);
        gb[5][5].setHeight(2);
        gb[5][6].setColor(true);
        gb[5][6].setHeight(1);

        gb[8][4].setColor(true);
        gb[8][4].setHeight(1);

        gb[10][2].setColor(true);
        gb[10][2].setHeight(2);

        g.setBoard(board);
        g.addMove(new Tile(0, 1, true));
        g.addMove(new Tile(0, 2, true));

        GamesController gamesController = new GamesController();
        gamesController.setGame(g);
        DataServerController dataServerController = new DataServerController(null, gamesController, null);
        DataToComConcrete dataToComConcrete = new DataToComConcrete(dataServerController);
    }

    /**
     * Tests the checkGameEnded method, when towers are not connected.
     */
    @Test
    public void test2() {

        Game g = new Game(new Player(UUID.randomUUID(), "testLogin", "testPicture"), 5, "test", null);
        Board board = new Board();
        Case[][] gb = board.getGameBoard();

        gb[3][11].setTower(true);
        gb[3][6].setTower(true);
        gb[8][5].setTower(true);
        gb[5][7].setTower(true);
        gb[10][1].setTower(true);
        g.getTowers().add(new Tower(3, 11));
        g.getTowers().add(new Tower(3, 6));
        g.getTowers().add(new Tower(8, 5));
        g.getTowers().add(new Tower(5, 7));
        g.getTowers().add(new Tower(10, 1));

        gb[3][10].setColor(true);
        gb[3][9].setColor(true);
        gb[3][8].setColor(true);
        gb[3][10].setHeight(2);
        gb[3][9].setHeight(1);
        gb[3][8].setHeight(2);

        gb[4][3].setColor(true);
        gb[5][3].setColor(true);
        gb[6][3].setColor(true);
        gb[7][3].setColor(true);
        gb[8][3].setColor(true);
        gb[9][3].setColor(true);
        gb[10][3].setColor(true);
        gb[4][3].setHeight(1);
        gb[5][3].setHeight(2);
        gb[6][3].setHeight(3);
        gb[7][3].setHeight(1);
        gb[8][3].setHeight(2);
        gb[9][3].setHeight(3);
        gb[10][3].setHeight(1);

        gb[5][4].setColor(true);
        gb[5][4].setHeight(1);
        gb[5][5].setColor(true);
        gb[5][5].setHeight(2);
        gb[5][6].setColor(true);
        gb[5][6].setHeight(1);

        gb[8][4].setColor(true);
        gb[8][4].setHeight(1);

        gb[10][2].setColor(true);
        gb[10][2].setHeight(2);

        g.setBoard(board);
        g.addMove(new Tile(0, 1, true));
        g.addMove(new Tile(0, 2, true));

        GamesController gamesController = new GamesController();
        gamesController.setGame(g);
        DataServerController dataServerController = new DataServerController(null, gamesController, null);
        DataToComConcrete dataToComConcrete = new DataToComConcrete(dataServerController);
    }
}
