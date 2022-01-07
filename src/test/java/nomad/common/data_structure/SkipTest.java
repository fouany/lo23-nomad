package nomad.common.data_structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests the Skip class.
 */

class SkipTest {
    @Test

    void constTest(){
        Skip d = new Skip(false);
        Assertions.assertFalse(d.isSkipped());
    }

    @Test
    void setTest(){
        Skip e = new Skip(true);
        e.setSkipped(true);
        Assertions.assertTrue(e.isSkipped());
    }
}
