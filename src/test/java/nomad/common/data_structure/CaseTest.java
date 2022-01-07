package nomad.common.data_structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests the Case class.
 */
class CaseTest {

    /**
     * Tests two instances of the Case class.
     */
    @Test
    void constructorTest(){
        Case c1 = new Case(0, 0, 0, false, false);
        Case c2 = new Case(15, 12, 3, true, true);

        Assertions.assertEquals(0, c1.getX());
        Assertions.assertEquals(0, c1.getY());
        Assertions.assertEquals(0, c1.getHeight());
        Assertions.assertFalse(c1.isTower());
        Assertions.assertFalse(c1.isColor());


        Assertions.assertEquals(15, c2.getX());
        Assertions.assertEquals(12, c2.getY());
        Assertions.assertEquals(3, c2.getHeight());
        Assertions.assertTrue(c2.isTower());
        Assertions.assertTrue(c2.isColor());
    }

    /**
     * Tests the setters of the Case class.
     */
    @Test
    void settersTest(){
        Case c1 = new Case(0, 0, 0, false, false);
        c1.setHeight(3);
        c1.setX(2);
        c1.setY(1);
        c1.setColor(true);
        c1.setTower(true);
        Assertions.assertEquals(3, c1.getHeight());
        Assertions.assertEquals(2, c1.getX());
        Assertions.assertEquals(1, c1.getY());
        Assertions.assertTrue(c1.isColor());
        Assertions.assertTrue(c1.isTower());
    }
}
