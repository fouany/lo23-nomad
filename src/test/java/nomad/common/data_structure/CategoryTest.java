package nomad.common.data_structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests the Category class.
 */
class CategoryTest {

    /**
     * Tests an instance of the Category class where attributes are false.
     */
    @Test
    void test1(){
        Category c = new Category("test 1", false, false, false, false);
        Assertions.assertEquals("test 1", c.getName());
        Assertions.assertFalse(c.isAccessAllowed());
        Assertions.assertFalse(c.isCheckInfos());
        Assertions.assertFalse(c.isRightToParticipate());
        Assertions.assertFalse(c.isRightToWatch());
    }

    /**
     * Tests an instance of the Category class where attributes are true.
     */
    @Test
    void test2(){
        Category c = new Category("test 2", true, true, true, true);
        Assertions.assertEquals("test 2", c.getName());
        Assertions.assertTrue(c.isAccessAllowed());
        Assertions.assertTrue(c.isCheckInfos());
        Assertions.assertTrue(c.isRightToParticipate());
        Assertions.assertTrue(c.isRightToWatch());
    }

    /**
     * Tests the setters of the Category class.
     */
    @Test
    void settersTest(){
        Category c = new Category("test 1", false, false, false, false);
        c.setName("test 2");
        c.setAccessAllowed(true);
        c.setCheckInfos(true);
        c.setRightToParticipate(true);
        c.setRightToWatch(true);
        Assertions.assertEquals("test 2", c.getName());
        Assertions.assertTrue(c.isAccessAllowed());
        Assertions.assertTrue(c.isCheckInfos());
        Assertions.assertTrue(c.isRightToWatch());
        Assertions.assertTrue(c.isRightToParticipate());
    }
}
