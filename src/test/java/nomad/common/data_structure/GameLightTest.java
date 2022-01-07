package nomad.common.data_structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * Tests the GameLight class.
 */
class GameLightTest {

    /**
     * Tests the constructor of the GameLight class.
     */
    @Test
    void constructorTest(){
        UUID testId = UUID.randomUUID();
        UUID testId2 = UUID.randomUUID();
        Player host = new Player(testId2,"test","testPicture");
        GameLight gameLight = new GameLight(testId, host, 5);
        Assertions.assertEquals(testId2, gameLight.getHost().getId());
        Assertions.assertEquals("test", gameLight.getHost().getLogin());
        Assertions.assertEquals("testPicture", gameLight.getHost().getProfilePicture());
    }

    /**
     * Tests the setters of the GameLight class.
     */
    @Test
    void settersTest(){
        UUID testId = UUID.randomUUID();
        UUID testId2 = UUID.randomUUID();
        Player host = new Player(testId2,"test","testPicture");
        GameLight gameLight = new GameLight(testId, host, 5);

        UUID testId3 = UUID.randomUUID();
        gameLight.setGameId(testId3);
        Assertions.assertEquals(testId3, gameLight.getGameId());

        UUID testId4 = UUID.randomUUID();
        Player host2 = new Player(testId4, "test 2", "testPicture 2");
        gameLight.setHost(host2);
        Assertions.assertEquals(testId4, gameLight.getHost().getId());

        gameLight.setNbOfTowers(4);
        Assertions.assertEquals(4, gameLight.getNbOfTowers());
    }
}
