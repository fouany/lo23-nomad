package nomad.common.data_structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Tests the Session class.
 */

public class SessionTest {

    @Test
    void tests(){

        UUID testId = UUID.randomUUID();
        UUID testId2 = UUID.randomUUID();
        UserLight ul1 = new UserLight(testId, "login");
        Player host = new Player(testId2,"test","testPicture");
        Player host2 = new Player(testId2,"test","testPicture");
        GameLight gameLightLobby = new GameLight(testId, host, 5);
        GameLight gameLightPlay = new GameLight(testId, host2, 5);

        List<UserLight> ulList = new ArrayList<>();
        ulList.add(ul1);

        List<GameLight> gameLightLobbyList = new ArrayList<>();
        gameLightLobbyList.add(gameLightLobby);

        List<GameLight> gameLightPlayList = new ArrayList<>();
        gameLightPlayList.add(gameLightPlay);

        Session session = new Session(ulList, gameLightLobbyList, gameLightPlayList);


        Assertions.assertEquals(ulList, session.getConnectedUsers());
        Assertions.assertEquals(gameLightLobbyList, session.getGamesInLobby());
        Assertions.assertEquals(gameLightPlayList, session.getGamesInPlay());
        // Assertions.assertEquals(gameLightLobbyList, session.getGameInLobbyById(testId));
        // Assertions.assertEquals(gameLightPlayList, session.getGameInPlayById(testId));



    }
}
