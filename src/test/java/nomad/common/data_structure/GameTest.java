package nomad.common.data_structure;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Tests the Game class.
 */
class GameTest {

    Game game;
    UUID testId;

    /**
     * Initializes the Game object before each test.
     */
    @BeforeEach
    void initTest(){
        testId = UUID.randomUUID();
        Player host = new Player(testId, "testLoginHost", "testProfilePicture");
        GameParameters gameParameters = new GameParameters(false, false, true);
        game = new Game(host, 5, "testGame", gameParameters);
        game.setCurrentPlayer(testId);
    }

    /**
     * Tests the Game constructor.
     */
    @Test
    void constructorTest(){
        Assertions.assertEquals(game.getHost().getId(), testId);
        Assertions.assertEquals("testLoginHost", game.getHost().getLogin());
        Assertions.assertEquals("testProfilePicture", game.getHost().getProfilePicture());
        Assertions.assertFalse(game.isSpectAllowed());
        Assertions.assertFalse(game.isSpectChatAllowed());
        Assertions.assertTrue(game.isHostColor());
        Assertions.assertEquals(5, game.getNbOfTowers());
        Assertions.assertEquals("testGame", game.getName());
    }

    /**
     * Tests most of the setters.
     */
    @Test
    void setterTests(){
        Assertions.assertFalse(game.isGameLaunched());
        game.setGameLaunched(true);
        Assertions.assertTrue(game.isGameLaunched());

        Assertions.assertFalse(game.isGameEnded());
        game.setGameEnded(true);
        Assertions.assertTrue(game.isGameLaunched());

        UUID testId2 = UUID.randomUUID();
        game.setOpponent(new Player(testId2, "testLogin2", "testProfilePicture2"));
        Assertions.assertEquals(testId2, game.getOpponent().getId());
        Assertions.assertEquals("testLogin2", game.getOpponent().getLogin());
        Assertions.assertEquals("testProfilePicture2", game.getOpponent().getProfilePicture());

        Assertions.assertEquals(testId, game.getCurrentPlayerUUID());
        game.setCurrentPlayer(testId2);
        Assertions.assertEquals(testId2, game.getCurrentPlayerUUID());

        game.changeCurrentPlayer();
        Assertions.assertEquals(testId, game.getCurrentPlayerUUID());

        game.setHostColor(false);
        Assertions.assertFalse(game.isHostColor());

        game.setGameLaunched(false);
        Assertions.assertFalse(game.isGameLaunched());
        game.setGameEnded(true);
        Assertions.assertTrue(game.isGameEnded());
    }

    /**
     * Tests the GameLight object creation from the Game object.
     */
    @Test
    void createGameLightTest(){
        GameLight gameLight = game.createGameLight();
        Assertions.assertEquals(game.getGameId(), gameLight.getGameId());
        Assertions.assertEquals(game.getHost(), gameLight.getHost());
        Assertions.assertEquals(game.getNbOfTowers(), gameLight.getNbOfTowers());
    }
}
