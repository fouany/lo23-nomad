package nomad.data.server;

import nomad.common.data_structure.Game;
import nomad.common.data_structure.GameLight;

import java.util.*;

/**
 * Controller for the Ihm Game.
 */
public class GamesController {

    /**
     * The map of Games currently created
     */
    private Map<UUID, Game> games;

    /**
     * Constructor of the class
     */
    public GamesController() {
        games = new HashMap<>();
    }

    /**
     * Sets the current game.
     * @param game - Game : the current Game
     */
    public void setGame(Game game){
        games.put(game.getGameId(), game);
    }

    /**
     * Returns the current game.
     * @return game game - Game : the current Game
     */
    public Game getGame(UUID gameId){
        return games.get(gameId);
    }

    /**
     * Returns the game in Lobby in GameLight format
     * @return List<GameLight> : games in Lobby
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
     * Returns the games in Play in GameLight format
     * @return List<GameLight> : games in Play in GameLight format
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
     * Returns all the game (in play and in lobby)
     * @return Map<UUID,Game> : all the game (in play and in lobby)
     */
    public Map<UUID,Game> getAllGames(){
        return games;
    }

    /**
     * Removes a game from the list
     * @param gameId - UUID: the game to be removed
     */
    public void removeGame(UUID gameId){
        this.games.remove(gameId);
    }
}