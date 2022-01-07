package nomad.common.data_structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests the Tower class.
 */

class TowerTest {
    @Test


    void constTower() {

        Tower t1 = new Tower(0, 1);
        Tower t2 = new Tower(0, 1);

        Assertions.assertEquals(0, t1.getX());
        Assertions.assertEquals(1, t1.getY());

        Assertions.assertEquals(0, t2.getX());
        Assertions.assertEquals(1, t2.getY());

    }
    @Test
    void setTest() {
        Tower t3 = new Tower(0, 1);
        t3.setX(0);
        t3.setY(1);
        Assertions.assertEquals(0, t3.getX());
        Assertions.assertEquals(1, t3.getY());
    }
}
