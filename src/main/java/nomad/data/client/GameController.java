package nomad.data.client;

import nomad.common.data_structure.Game;
import nomad.common.data_structure.GameLight;
import java.util.Observable;

public class GameController extends Observable {

    private Game game;

    public GameController(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public GameLight getGameLight(){
        return new GameLight(this.game.getGameId(), this.game.getHost(), this.game.getNbOfTowers());
    }

    @Override
    public String toString() {
        return "GameController{" +
                "game=" + game +
                '}';
    }
}
