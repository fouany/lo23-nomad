package nomad.data.client;

import nomad.common.data_structure.Game;
import nomad.common.data_structure.GameLight;
import java.util.Observable;

/**
 * Controller for the current game.
 */
public class GameController extends Observable {

    /**
     * The current game
     */
    private Game game;

    /**
     * Constructor
     * @param game - Game : the current game
     */
    public GameController(Game game) {
        this.game = game;
    }

    /**
     * gets the current game
     * @return Game
     */
    public Game getGame() {
        return game;
    }

    /**
     * sets the current game
     * @param game Game : the current game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * sets the current game in GameLight format
     * @return GameLight
     */
    public GameLight getGameLight(){
        return new GameLight(this.game.getGameId(), this.game.getHost(), this.game.getNbOfTowers());
    }

    /**
     * Returns a string representation of the class
     * @return String
     */
    @Override
    public String toString() {
        return "GameController{" +
                "game=" + game +
                '}';
    }
}
