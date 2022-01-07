package nomad.common.data_structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests the Board class.
 */
class BoardTest {

    /**
     * Tests the Case table in the Board class.
     */
    @Test
    void constructorTest(){
        Board b = new Board();
        for(int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                Assertions.assertEquals(i, b.getGameBoard()[i][j].getX());
                Assertions.assertEquals(j, b.getGameBoard()[i][j].getY());
            }
        }
    }

    /**
     * Tests the setters of the Board class.
     */
    @Test
    void settersTest(){
        Board b = new Board();
        Board b2 = new Board();
        b2.getGameBoard()[1][1].setHeight(3);
        b.setGameBoard(b2.getGameBoard());
        Assertions.assertEquals(3, b.getGameBoard()[1][1].getHeight());
    }
}
