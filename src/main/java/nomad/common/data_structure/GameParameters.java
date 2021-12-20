package nomad.common.data_structure;

import java.io.Serializable;

/**
 * Encapsulates game settings for:
 * - Allowing spectators
 * - Allowing spectators chat
 * - The host color
 */
public class GameParameters implements Serializable {
    /**
     * Right for the spectators to join and watch the game
     */
    private boolean spectAllowed;
    /**
     * Right for the spectators to chat during the game
     */
    private boolean spectChatAllowed;
    /**
     * Defines the host color
     * (0 = black, 1 = red)
     */
    private boolean hostColor;

    /**
     * GameParamaters constructor
     * @param spectAllowed - Right for the spectators to join and watch the game
     * @param spectChatAllowed - Right for the spectators to chat during the game
     * @param hostColor - Defines the host color (0 = black, 1 = red)
     */
    public GameParameters(boolean spectAllowed, boolean spectChatAllowed, boolean hostColor) {
        this.spectAllowed = spectAllowed;
        this.spectChatAllowed = spectChatAllowed;
        this.hostColor = hostColor;
    }

    /**
     * Returns true if spectators are allowed to join and watch the game
     * @return - Right as a boolean
     */
    public boolean isSpectAllowed() {
        return spectAllowed;
    }

    /**
     * Sets a boolean to say if spectators are allowed to join and watch the game
     * @param spectAllowed - Right as a boolean
     */
    public void setSpectAllowed(boolean spectAllowed) {
        this.spectAllowed = spectAllowed;
    }

    /**
     * Returns true if spectators are allowed to chat
     * @return - Right as a boolean
     */
    public boolean isSpectChatAllowed() {
        return spectChatAllowed;
    }

    /**
     * Sets a boolean to say if spectators are allowed to chat
     * @param spectChatAllowed - Right as a boolean
     */
    public void setSpectChatAllowed(boolean spectChatAllowed) {
        this.spectChatAllowed = spectChatAllowed;
    }

    /**
     * Returns the color of the host
     * @return the color of the host as a boolean
     */
    public boolean isHostColor() {
        return hostColor;
    }

    /**
     * Sets the color of the host
     * @param hostColor - color of the host as a boolean
     */
    public void setHostColor(boolean hostColor) {
        this.hostColor = hostColor;
    }
}
