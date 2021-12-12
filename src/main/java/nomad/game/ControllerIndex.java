package nomad.game;

/**
 * List all game controllers and assign them an index
 */
public enum ControllerIndex {
    GAME(0),
    PLAYER_INFO(1),
    BOARD(2),
    LOG(3),
    SKIP(4),
    CHAT(5);

    public final int index;
    ControllerIndex(int index) {
        this.index = index;
    }
}