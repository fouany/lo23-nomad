package nomad.common.data_structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests the ProfileStat class.
 */
class ProfileStatTest {

    /**
     * Tests the constructor of the ProfileStat class.
     */
    @Test
    void constructorTest(){
        ProfileStat ps = new ProfileStat(1, 2, 3);
        Assertions.assertEquals(1, ps.getGamesPlayed());
        Assertions.assertEquals(2, ps.getGamesWon());
        Assertions.assertEquals(3, ps.getGamesLost());
    }

    /**
     * Tests the setters of the ProfileStat class.
     */
    @Test
    void settersTest(){
        ProfileStat ps = new ProfileStat(1, 2, 3);
        ps.setGamesLost(0);
        ps.setGamesPlayed(5);
        ps.setGamesWon(6);
        Assertions.assertEquals(0, ps.getGamesLost());
        Assertions.assertEquals(5, ps.getGamesPlayed());
        Assertions.assertEquals(6, ps.getGamesWon());
    }
}
