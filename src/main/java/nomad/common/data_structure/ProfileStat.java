package nomad.common.data_structure;

import java.io.Serializable;

/**
 * Represents the statistics of a player
 */
public class ProfileStat implements Serializable {

    /**
     * Number of games played
     */
    private int gamesPlayed;

    /**
     * Number of games won
     */
    private int gamesWon;

    /**
     * Number of games lost
     */
    private int gamesLost;

    /**
     * ProfileStat constructor
     * @param gamesPlayed - Number of games played
     * @param gamesWon - Number of games won
     * @param gamesLost - Number of games lost
     */
    public ProfileStat(int gamesPlayed, int gamesWon, int gamesLost) {
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
    }

    /**
     * Gets the number of games played
     * @return the number of games played as an int
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Sets the number of games played
     * @param gamesPlayed - Number of games played as an int
     */
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    /**
     * Gets the number of games won
     * @return the number of games won as an int
     */
    public int getGamesWon() {
        return gamesWon;
    }

    /**
     * Sets the number of games won
     * @param gamesWon - Number of games won as an int
     */
    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    /**
     * Gets the number of games lost
     * @return the number of games lost as an int
     */
    public int getGamesLost() {
        return gamesLost;
    }

    /**
     * Sets the number of games lost
     * @param gamesLost - Number of games lost as an int
     */
    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    /**
     * Returns a string representation of the object
     * @return String
     */
    @Override
    public String toString() {
        return "model.ProfileStat{" +
                "gamesPlayed=" + gamesPlayed +
                ", gamesWon=" + gamesWon +
                ", gamesLost=" + gamesLost +
                '}';
    }
}
