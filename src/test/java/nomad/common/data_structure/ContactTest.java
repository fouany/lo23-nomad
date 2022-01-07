package nomad.common.data_structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * Tests the Contact class.
 */
class ContactTest {

    /**
     * Tests an instance of the Contact class.
     */
    @Test
    void constructorTest(){
        UUID testId = UUID.randomUUID();
        UserLight u = new UserLight(testId, "test");
        Category c = new Category("testCategory", false, true, false, true);
        Contact contact = new Contact(u, c);

        Assertions.assertEquals(testId, contact.getUser().getId());
        Assertions.assertEquals("test", contact.getUser().getLogin());
        Assertions.assertEquals("testCategory", contact.getCategory().getName());
        Assertions.assertFalse(contact.getCategory().isCheckInfos());
        Assertions.assertFalse(contact.getCategory().isRightToParticipate());
        Assertions.assertTrue(contact.getCategory().isRightToWatch());
        Assertions.assertTrue(contact.getCategory().isAccessAllowed());
    }

    /**
     * Tests the setters of the Contact class.
     */
    @Test
    void settersTests(){
        UUID testId = UUID.randomUUID();
        UUID testId2 = UUID.randomUUID();
        UserLight u = new UserLight(testId, "test");
        UserLight u2 = new UserLight(testId2, "test 2");
        Category c = new Category("testCategory", false, true, false, true);
        Category c2 = new Category("testCategory 2", true, false, true, false);
        Contact contact = new Contact(u, c);
        contact.setCategory(c2);
        contact.setUser(u2);
        Assertions.assertEquals("test 2", contact.getUser().getLogin());
        Assertions.assertEquals(testId2, contact.getUser().getId());
        Assertions.assertEquals("testCategory 2", contact.getCategory().getName());
        Assertions.assertTrue(contact.getCategory().isCheckInfos());
        Assertions.assertTrue(contact.getCategory().isRightToParticipate());
        Assertions.assertFalse(contact.getCategory().isRightToWatch());
        Assertions.assertFalse(contact.getCategory().isAccessAllowed());
    }
}
