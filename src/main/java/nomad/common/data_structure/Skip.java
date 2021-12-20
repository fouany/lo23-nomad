package nomad.common.data_structure;

/**
 * Represents a move
 * When a player skips
 */
public class Skip extends Move {

    /**
     * if the player move is skipped
     * True if it is skipped
     */
    private boolean isSkipped;

    /**
     * Skip constructor
     * @param isSkipped - if the player move is skipped
     */
    public Skip(boolean isSkipped) {
        this.isSkipped = isSkipped;
    }

    /**
     * Returns a boolean depending on if the player move is skipped
     * @return Boolean - true if it is skipped
     */
    public boolean isSkipped() {
        return isSkipped;
    }

    /**
     * Sets a boolean depending on if the player move is skipped
     * @param skipped - Boolean : true if it is skipped
     */
    public void setSkipped(boolean skipped) {
        isSkipped = skipped;
    }

    /**
     * Returns a string representation of the object
     * @return String
     */
    @Override
    public String toString() {
        return "Skip{" +
                "isSkipped=" + isSkipped +
                '}';
    }
}
