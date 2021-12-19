package nomad.common.data_structure;

import java.io.Serializable;

public class  ProfileStat implements Serializable {

    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;

    public ProfileStat(int gamesPlayed, int gamesWon, int gamesLost) {
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    @Override
    public String toString() {
        return "model.ProfileStat{" +
                "gamesPlayed=" + gamesPlayed +
                ", gamesWon=" + gamesWon +
                ", gamesLost=" + gamesLost +
                '}';
    }
}
