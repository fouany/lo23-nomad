package nomad.common.data_structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

/**
 * Tests the Message class.
 */
class MessageTest {

    /**
     * Tests the constructor of the Message class.
     */
    @Test
    void constructorTest(){

        UUID userId = UUID.randomUUID();
        UserLight user = new UserLight(userId, "test");
        UUID gameId = UUID.randomUUID();
        Timestamp time = Timestamp.from(Instant.now());
        Message m = new Message(user, gameId, "oui", time);
        Assertions.assertEquals(userId, m.getSender().getId());
        Assertions.assertEquals(gameId, m.getGameId());
        Assertions.assertEquals(time, m.getTime());
        Assertions.assertEquals("oui", m.getContent());
    }

    /**
     * Tests the setters of the Message class.
     */
    @Test
    void settersTest(){
        UUID userId = UUID.randomUUID();
        UserLight user = new UserLight(userId, "test");
        UUID gameId = UUID.randomUUID();
        Timestamp time = Timestamp.from(Instant.now());
        Message m = new Message(user, gameId, "oui", time);

        UUID userId2 = UUID.randomUUID();
        UserLight user2 = new UserLight(userId2, "test");
        UUID gameId2 = UUID.randomUUID();

        m.setContent("non");
        Assertions.assertEquals("non", m.getContent());
        m.setGameId(gameId2);
        Assertions.assertEquals(gameId2, m.getGameId());
        m.setSender(user2);
        Assertions.assertEquals(user2, m.getSender());
    }
}

