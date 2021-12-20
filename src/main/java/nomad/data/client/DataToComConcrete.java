package nomad.data.client;

import nomad.common.data_structure.*;
import nomad.common.interfaces.data.DataToComClientInterface;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataToComConcrete implements DataToComClientInterface {

    DataClientController dataClientController;

    public void setController(DataClientController dataClientController) {
        this.dataClientController = dataClientController;
    }

    /**
     * adds a new game in the lobby
     * @param gameLight
     */
    //TODO : Modifier diagramme de sequence : a quoi servent les paramètre name, spect et spectatorChat
    public void updateSession(GameLight gameLight) {
        dataClientController.getSession().getGamesInLobby().add(gameLight);
    }

    /**
     * Change the State of Game in Session
     * @param gameId
     * @param gameLaunched
     */
    //
    public void updateSessionGameState(UUID gameId, boolean gameLaunched) {
        //if the game is launched : remove from gameInLobby and add to GameInPlays
        if (gameLaunched) {
            //1- Remove the game from game in lobby
            GameLight game = dataClientController.getSession().getGameInLobbyById(gameId);
            dataClientController.getSession().getGamesInLobby().remove(game);

            //2- Add Game to game in Play
            dataClientController.getSession().getGamesInPlay().add(game);
        } else { //the game is ended
            //remove the game from game in Play
            GameLight game = dataClientController.getSession().getGameInPlayById(gameId);
            dataClientController.getSession().getGamesInPlay().remove(game);
        }
    }

    /**
     * adds a user light to the connected user of the session object
     * @param player
     * @param connected
     */
    // TODO : pas besoin du booleen !
    public void updateUserSession(Player player, boolean connected) {
        if (player.getId().equals(dataClientController.getUserController().getUser().getUserId())) {
            return;
        }
        dataClientController.getSession().getConnectedUsers().add(new UserLight(player.getId(), player.getLogin()));
    }

    /**
     * updates the opponent of the game
     * @param player
     * @param gameLight
     * @throws GameException
     */
    public void updateOpponent(Player player, GameLight gameLight) throws GameException {
        dataClientController.getSession().getGameInLobbyById(gameLight.getGameId());
        if (gameLight.getGameId().equals(dataClientController.getGameController().getGame().getGameId())) {
            dataClientController.getGameController().getGame().setOpponent(player);
        } else {
            throw new GameException("Game Id does not exist");
        }
    }

    /**
     * adds a user light to the spectator's list of the game
     * @param userLight
     * @param isAdded
     */
    // TODO : pas besoin du booleen
    public void handleSpectator(UserLight userLight, boolean isAdded) {
        dataClientController.getGameController().getGame().getSpect().add(userLight);
    }

    // TODO: game is already set to "launched" in updateSessionGameState,
    //  this method might only need to update the observable

    /**
     * sets the game as launched and adds the observable to the game
     */
    public void gameLaunchEvent() {
        dataClientController.getGameController().getGame().setGameLaunched(true);
    }

    /**
     * adds the move to the list of moves of the game and changes the current player
     * @param tower
     * @param valid
     */
    public void towerValid(Tower tower, boolean valid) {
        dataClientController.getGameController().getGame().addTowerPlayed();
        dataClientController.getGameController().getGame().getBoard().updateBoard(tower);
        dataClientController.getGameController().getGame().changeCurrentPlayer();
        dataClientController.getGameController().getGame().addMove(tower);
        dataClientController.getIhmGameToDataInterface().updateObservable(dataClientController.getGameController().getGame());
    }

    /**
     * adds the move to the list of moves of the game and changes the current player
     * @param tile
     * @param valid
     */
    public void tileValid(Tile tile, boolean valid) throws TileException {
        if (valid) {
            dataClientController.getGameController().getGame().getBoard().updateBoard(tile);
            dataClientController.getGameController().getGame().changeCurrentPlayer();
            dataClientController.getGameController().getGame().addMove(tile);
            dataClientController.getIhmGameToDataInterface().updateObservable(dataClientController.getGameController().getGame());
        } else {
            dataClientController.getIhmGameToDataInterface().updateObservable(dataClientController.getGameController().getGame());
            throw new TileException("Tile Placement not valid");
        }
    }

    /**
     * adds the move to the list of moves of the game and changes the current player
     * @param skip
     * @param valid
     */
    public void skipValidation(Skip skip, boolean valid) throws SkipException {
        if (valid) {
            dataClientController.getGameController().getGame().changeCurrentPlayer();
            dataClientController.getGameController().getGame().addMove(skip);
            dataClientController.getIhmGameToDataInterface().updateObservable(dataClientController.getGameController().getGame());
        } else {
            dataClientController.getIhmGameToDataInterface().updateObservable(dataClientController.getGameController().getGame());
            throw new SkipException("Skip not valid");
        }
    }

    /**
     * adds the move to the list of moves, changes the current player, and updates the observable
     * @param move
     */
    @Override
    public void moveReceived(Move move) {
        if (move.idMove()=="Tower"){
            dataClientController.getGameController().getGame().getBoard().updateBoard((Tower)move);
        }else if (move.idMove()=="Tile"){
            dataClientController.getGameController().getGame().getBoard().updateBoard((Tile)move);
        }
        dataClientController.getGameController().getGame().changeCurrentPlayer();
        dataClientController.getGameController().getGame().addMove(move);
        dataClientController.getIhmGameToDataInterface().updateObservable(dataClientController.getGameController().getGame());
    }

    @Override
    public void endGame(UUID gameId, UUID winner, Move lastMove) {
        GameLight gameToRemove = dataClientController.getSession().getGameInPlayById(gameId);
        dataClientController.getSession().getGamesInPlay().remove(gameToRemove);
        dataClientController.getGameController().getGame().setGameEnded(true);
        UUID loserID = dataClientController.getGameController().getGame().getOpponent().getId();
        //Checking if the user was a player in the game or just a spectator
        if (dataClientController.getUserController().getUser().getUserId().equals(winner) || dataClientController.getUserController().getUser().getUserId().equals(loserID)) {
            int gamesAlreadyPlayed = dataClientController.getUserController().getUser().getProfileStat().getGamesPlayed();
            dataClientController.getUserController().getUser().getProfileStat().setGamesPlayed(gamesAlreadyPlayed + 1);
            if (dataClientController.getUserController().getUser().getUserId().equals(winner)) {
                int gamesAlreadyWon = dataClientController.getUserController().getUser().getProfileStat().getGamesWon();
                dataClientController.getUserController().getUser().getProfileStat().setGamesWon(gamesAlreadyWon + 1);
            } else {
                int gamesAlreadyLost = dataClientController.getUserController().getUser().getProfileStat().getGamesLost();
                dataClientController.getUserController().getUser().getProfileStat().setGamesLost(gamesAlreadyLost + 1);
            }
        }
        dataClientController.getIhmGameToDataInterface().gameEnded(winner, lastMove);
    }

    @Override
    public void removeFinishedGame(UUID gameId) {
        GameLight gameToRemove = dataClientController.getSession().getGameInPlayById(gameId);
        dataClientController.getSession().getGamesInPlay().remove(gameToRemove);
        dataClientController.getGameController().getGame().setGameEnded(true);
        dataClientController.getIhmGameToDataInterface().updateObservable(dataClientController.getGameController().getGame());
    }

    /**
     * adds the game to the saved games of the user
     * @param game
     */
    public void transferSavedGame(Game game) {
        dataClientController.getUserController().addSavedGame(game);
    }

    /**
     * adds the message to the chat of the game
     * @param message
     */
    public void storeNewMessage(Message message) {
        dataClientController.getGameController().getGame().getChat().add(message);
    }

    @Override
    /**
     * adds the connected user, and updates the according lists of connected user to the games in lobby and in play
     */
    public void addConnectedUserProfile(List<Player> players, List<GameLight> gamesInLobby, List<GameLight> gamesInPlay) {
        for (Player p : players) {
            dataClientController.getSession().getConnectedUsers().add(new UserLight(p));
        }

        for (GameLight gl : gamesInLobby) {
            dataClientController.getSession().getGamesInLobby().add(gl);
        }

        for (GameLight glp : gamesInPlay) {
            dataClientController.getSession().getGamesInPlay().add(glp);
        }
    }

    /**
     * Remove an user from the connected users of the session object
     * @param userId UID of the user to remove
     * @param isDeconnected Not used
     */
    // TODO : changer signature : pas besoin du booleen
    public void isDisconnected(UUID userId, boolean isDeconnected) {
        List<UserLight> connectedUsers = dataClientController.getSession().getConnectedUsers();
        connectedUsers.removeIf((UserLight u) -> u.getId().equals(userId));
    }

    /**
     * Retrieve all the online users
     * @return List of connected users
     */
    public List<UserLight> getOnlineUsers() {
        return dataClientController.getSession().getConnectedUsers();
    }

    /**
     * Set the game (created by the server) in the GameController.
     * @param game
     */
    public void gameCreated(Game game) {
        dataClientController.getGameController().setGame(game);
        dataClientController.getIhmMainToDataInterface().updateGameCreated(dataClientController.getGameController().getGame());
    }


    /**
     * Adds an user to the game. (as spectator or opponent)
     * @param gameID
     * @param p
     */
    public void newPlayer(UUID gameID, Player p) throws GameException {
        Game game = dataClientController.getGameController().getGame();
        if (game.getGameId().equals(gameID)) {
            game.setOpponent(p);
            dataClientController.getIhmMainToDataInterface().askApproval(dataClientController.getGameController().getGame());
            /*todo use custom method*/
        } else {
            throw new GameException("Game created does not exists");
        }
    }

    @Override
    public void newSpectator(UUID gameId, Player spec) throws GameException {
        //TODO
    }

    //TODO : V3 ajouter prise en compte de bool==FALSE
    @Override
    public void addedPlayerInGame(Game game, boolean isAdded) throws GameException {
        if (isAdded) {
            dataClientController.getGameController().setGame(game);
            dataClientController.getIhmMainToDataInterface().updateAcceptOpponent(dataClientController.getGameController().getGame());
        }else{
            dataClientController.getIhmMainToDataInterface().updateRejectOpponent(dataClientController.getGameController().getGame());
            throw new GameException("Player was refused from game");
        }
    }


    /**
     * Retrieves the saved games of the user.
     * @return
     */
    public List<Game> getStoredAvailableGames() {
        return dataClientController.getUserController().getUser().getSavedGames();
    }

    /**
     * returns the UUID of the player currently playing
     * @return
     */
    //TODO : modifier diag : type retour UUID et non pas booleen
    @Override
    public UUID currentUserIsPlayer() {
        return dataClientController.getGameController().getGame().getCurrentPlayerUUID();
    }

    @Override
    /**
     * get profile of an user
     * @param idUser : id of the user
     * @return User
     */
    public UserLight getUserLight() {
        return dataClientController.getUserController().getUserLight();
    }

    @Override
    public void addedSpecInGame(Game game, boolean isAdded) {
        if (isAdded) {
            dataClientController.getGameController().setGame(game);
            dataClientController.getIhmMainToDataInterface().updateObservable(dataClientController.getGameController().getGame());
        }
    }

}
