package nomad.common.data_structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Date;

/**
 * Tests the UserInfo class.
 */

class UserInfoTest {
    @Test

    void constTest (){
        Date now = new Date();

        UserInfo ui_1 = new UserInfo("user", "pass", "name", "pict", now);

        Assertions.assertEquals("user", ui_1.getLogin());
        Assertions.assertEquals("pass", ui_1.getPassword());
        Assertions.assertEquals("name", ui_1.getName());
        Assertions.assertEquals("pict", ui_1.getProfilePicture());
        Assertions.assertEquals(now, ui_1.getBirthDate());
    }
    @Test
    void setTest (){
        Date now = new Date();
        UserInfo ui_2 = new UserInfo("user", "pass", "name", "pict",  now);
        ui_2.setLogin("user");
        ui_2.setPassword("pass");
        ui_2.setName("name");
        ui_2.setProfilePicture("pict");
        ui_2.setBirthDate(now);

        Assertions.assertEquals("user", ui_2.getLogin());
        Assertions.assertEquals("pass", ui_2.getPassword());
        Assertions.assertEquals("name", ui_2.getName());
        Assertions.assertEquals("pict", ui_2.getProfilePicture());
        Assertions.assertEquals(now, ui_2.getBirthDate());


    }
}
