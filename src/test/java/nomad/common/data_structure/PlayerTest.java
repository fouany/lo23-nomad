package nomad.common.data_structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * Tests the Player class.
 */
class PlayerTest {

    /**
     * Tests the constructor of the Player class.
     */
    @Test
    void constructorPlayer(){

        UUID testId = UUID.randomUUID();
        Player p = new Player(testId, "test", "testPicture");
        Assertions.assertEquals(testId, p.getId());
        Assertions.assertEquals("test", p.getLogin());
        Assertions.assertEquals("testPicture", p.getProfilePicture());
    }

    /**
     * Tests the setters of the Player class.
     */
    @Test
    void settersTest(){

        UUID testId = UUID.randomUUID();
        Player p = new Player(testId, "test", "testPicture");

        UUID testId2 = UUID.randomUUID();
        p.setId(testId2);
        p.setLogin("test2");
        p.setProfilePicture("testPicture2");

        Assertions.assertEquals(testId2, p.getId());
        Assertions.assertEquals("test2", p.getLogin());
        Assertions.assertEquals("testPicture2", p.getProfilePicture());
    }
}
