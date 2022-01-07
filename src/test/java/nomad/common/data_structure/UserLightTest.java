package nomad.common.data_structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * Tests the UserLight class.
 */

class UserLightTest {
    @Test

    void consTest(){
        UUID testId = UUID.randomUUID();
        UserLight ul1 = new UserLight(testId, "login");
        UserLight ul2 = new UserLight(testId, "login_2");

        Player p = new Player(testId, "login_3", "pic");
        UserLight ul3 = new UserLight(p);

        Assertions.assertEquals(testId, ul1.getId());
        Assertions.assertEquals("login", ul1.getLogin());

        Assertions.assertEquals(testId, ul2.getId());
        Assertions.assertEquals("login_2", ul2.getLogin());

        Assertions.assertEquals(testId, ul3.getId());
        Assertions.assertEquals("login_3", ul3.getLogin());
    }


    @Test
    void setTest(){
        UUID testId = UUID.randomUUID();
        UUID testId2 = UUID.randomUUID();
        UserLight ul1 = new UserLight(testId, "login");
        UserLight ul2 = new UserLight(testId2, "login_2");
        Player p1 = new Player(testId, "login_3", "pic");
        Player p2 = new Player(testId2, "login_4", "pic");
        UserLight ul3 = new UserLight(p1);
        UserLight ul4 = new UserLight(p2);
        ul1.setId(testId);
        ul1.setLogin(ul1.getLogin());
        Assertions.assertEquals(testId, ul1.getId());
        Assertions.assertEquals("login", ul1.getLogin());
        Assertions.assertEquals(testId2, ul2.getId());
        Assertions.assertEquals("login_2", ul2.getLogin());
        Assertions.assertEquals(testId, ul3.getId());
        Assertions.assertEquals("login_3", ul3.getLogin());
        Assertions.assertEquals(testId2, ul4.getId());
        Assertions.assertEquals("login_4", ul4.getLogin());

    }
}