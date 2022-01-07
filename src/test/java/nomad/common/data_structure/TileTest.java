package nomad.common.data_structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests the Tile class.
 */

class TileTest {
    @Test


    void constTest() {

        Tile t1 = new Tile(0, 1, false);
        Tile t2 = new Tile(0, 1, true);

        Assertions.assertEquals(0, t1.getX());
        Assertions.assertEquals(1, t1.getY());
        Assertions.assertFalse(t1.isColor());

        Assertions.assertEquals(0, t2.getX());
        Assertions.assertEquals(1, t2.getY());
        Assertions.assertTrue(t2.isColor());

    }
    @Test
    void setTest() {
        Tile t3 = new Tile(0, 1, true);
        t3.setX(0);
        t3.setY(1);
        t3.setColor(true);
        Assertions.assertEquals(0, t3.getX());
        Assertions.assertEquals(1, t3.getY());
        Assertions.assertTrue(t3.isColor());
    }
}
