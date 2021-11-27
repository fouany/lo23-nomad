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
     * Returns the game in Lobby in GameLight format :
     */
    public List<GameLight> getGameLightListInLobby(){
        List<GameLight> listInLobby= new ArrayList<>();

        for(Game game : games.values()) {
            //add to game In lobby
            if (!game.isGameEnded() && !game.isGameLaunched()) {
                listInLobby.add(game.createGameLight());
            }

        }
        return listInLobby;
    }

    /**
     * Returns the game in Play in GameLight format :
     */
    public List<GameLight> getGameLightListInPlay(){
        List<GameLight> listInPlay= new ArrayList<>();

        for(Game game : games.values()) {
            //add to game In Play
            if (!game.isGameEnded() && game.isGameLaunched()) {
                listInPlay.add(game.createGameLight());
            }

        }
        return listInPlay;
    }


    /**
     * @return all the game (in play and in lobby)
     */
    public Map<UUID,Game> getAllGames(){
        return games;
    }
}