package nomad.common.data_structure;

import java.io.Serializable;

/**
 * Encapsulates game parameters for:
 * - Allowing spectators
 * - Allowing spectators chat
 * - The host color
 */
public class GameParameters implements Serializable {
    private boolean spectAllowed;
    private boolean spectChatAllowed;
    private boolean hostColor;

    public GameParameters(boolean spectAllowed, boolean spectChatAllowed, boolean hostColor) {
        this.spectAllowed = spectAllowed;
        this.spectChatAllowed = spectChatAllowed;
        this.hostColor = hostColor;
    }

    public boolean isSpectAllowed() {
        return spectAllowed;
    }

    public void setSpectAllowed(boolean spectAllowed) {
        this.spectAllowed = spectAllowed;
    }

    public boolean isSpectChatAllowed() {
        return spectChatAllowed;
    }

    public void setSpectChatAllowed(boolean spectChatAllowed) {
        this.spectChatAllowed = spectChatAllowed;
    }

    public boolean isHostColor() {
        return hostColor;
    }

    public void setHostColor(boolean hostColor) {
        this.hostColor = hostColor;
    }
}
