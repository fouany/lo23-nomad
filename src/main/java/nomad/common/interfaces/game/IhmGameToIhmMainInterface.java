package nomad.common.interfaces.game;

/**
 * Expose methods of the {@code nomad.game} package to {@code nomad.main}.
 *
 * <br>Allow the main module to switch the display to the game UI for players, spectators and for replaying a game.
 * <br>Replace the active JavaFX scene in the application stage.
 */
public interface IhmGameToIhmMainInterface {

    /**
     * Display the game UI with player layout.
     */
    void displayGameAsPlayer();

    /**
     * Display the game UI with spectator layout.
     */
    void displayGameAsSpect();

    /**
     * Display the game UI with replay layout.
     */
    void displaySavedGame();
}