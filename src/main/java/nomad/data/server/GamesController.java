package nomad.data.server;

import nomad.common.data_structure.Game;
import nomad.common.data_structure.GameLight;

import java.util.*;

/**
 * Controller for the Ihm Game.
 */
public class GamesController {
    private Map<UUID, Game> games;

    public GamesController() {
        games = new HashMap<>();
    }

    /**
     * Sets the current game.
     * @param game
     */
    public void setGame(Game game){
        games.put(game.getGameId(), game);
    }

    /**
     * Returns the current game.
     * @return game
     */
    public Game getGame(UUID gameId){
        return games.get(gameId);
    }

    /**
     * Returns the game list in GameLight format
     */
    public List<GameLight> getGameLightList(){
        List<GameLight> list = new ArrayList<>();
        for(Game game : games.values()) {
            list.add(game.createGameLight());
        }
        return list;
    }
}