package nomad.data.client;

import nomad.common.data_structure.*;
import nomad.common.interfaces.data.DataToComClientInterface;

import java.util.List;
import java.util.UUID;

/**
 * Concretion of the com Interface.
 */
public class DataToComConcrete implements DataToComClientInterface {

    /**
     * The main class of the client package
     */
    DataClientController dataClientController;

    /**
     * sets the controller
     * @param dataClientController - DataClientController : controller
     */
    public void setController(DataClientController dataClientController) {
        this.dataClientController = dataClientController;
    }

    /**
     * adds a new game in the lobby
     *
     * @param gameLight
     */
    @Override
    public void updateSession(GameLight gameLight){
        dataClientController.getSession().getGamesInLobby().add(gameLight);
    }

    /**
     * Change the State of Game in Session
     *
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
     *
     * @param player
     * @param connected
     */
    // TODO : pas besoin du booleen !
    public void updateUserSession(Player player, boolean connected) {
        if (player.getId().equals(dataClientController.getUserController().getUser().getUserId())) {
            return;
        }

        //if user is connected add user to the Session
        if (connected){
            dataClientController.getSession().getConnectedUsers().add(new UserLight(player.getId(), player.getLogin()));
        }
    }

    /**
     * updates the opponent of the game
     *
     * @param player
     * @param gameLight
     * @throws GameException
     */
    @Override
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
     *
     * @param userLight
     * @param isAdded
     */
    @Override
    // TODO : pas besoin du booleen
    public void handleSpectator(UserLight userLight, boolean isAdded) {
        dataClientController.getGameController().getGame().getSpect().add(userLight);
    }

    /**
     * sets the game as launched
     */
    @Override
    public void gameLaunchEvent(){
        dataClientController.getGameController().getGame().setGameLaunched(true);
    }

    /**
     * adds the tower move to the list of moves of the game, changes the current player
     * @param tower - Tower : move to add
     * @param valid - boolean : to know if the move is valid
     * @throws TowerException :  Tower Placement not valid
     */
    @Override
    public void towerValid(Tower tower, boolean valid) throws TowerException {
        if (valid){ //if the move is valid
            dataClientController.getGameController().getGame().addMove(tower);
            dataClientController.getGameController().getGame().changeCurrentPlayer();
            dataClientController.getIhmGameToDataInterface().updateObservable(dataClientController.getGameController().getGame());
        }else{ //move invalid => throw exception
            dataClientController.getIhmGameToDataInterface().updateObservable(dataClientController.getGameController().getGame());
            throw new TowerException("Tower Placement not valid");
        }
    }

    /**
     * adds the tile move to the list of moves of the game and changes the current player
     * @param tile - Tile : move to add
     * @param valid - boolean : to know if the move is valid
     * @throws TileException : Tile Placement not valid
     */
    @Override
    public void tileValid(Tile tile, boolean valid) throws TileException {
        if (valid){
            dataClientController.getGameController().getGame().changeCurrentPlayer();
            dataClientController.getGameController().getGame().addMove(tile);
            dataClientController.getIhmGameToDataInterface().updateObservable(dataClientController.getGameController().getGame());
        }else{ //move invalid => throw exception
            dataClientController.getIhmGameToDataInterface().updateObservable(dataClientController.getGameController().getGame());
            throw new TileException("Tile Placement not valid");
        }
    }

    /**
     * adds the skip move to the list of moves of the game and changes the current player
     * @param skip - Skip : move to add
     * @param valid - boolean : to know if the move is valid
     * @throws SkipException : Skip not valid
     */
    @Override
    public void skipValidation(Skip skip, boolean valid) throws SkipException {
        if (valid){
            dataClientController.getGameController().getGame().addMove(skip);
            dataClientController.getGameController().getGame().changeCurrentPlayer();
            dataClientController.getIhmGameToDataInterface().updateObservable(dataClientController.getGameController().getGame());
        }else{ //move invalid => throw exception
            dataClientController.getIhmGameToDataInterface().updateObservable(dataClientController.getGameController().getGame());
            throw new SkipException("Skip not valid");
        }
    }

    /**
     * adds the move to the list of moves, changes the current player, and updates the observable
     * uses for the spectators and the opponent
     * @param move - Move : move to add
     */
    @Override
    public void moveReceived(Move move) {
        dataClientController.getGameController().getGame().changeCurrentPlayer();
        dataClientController.getGameController().getGame().addMove(move);
        dataClientController.getIhmGameToDataInterface().updateObservable(dataClientController.getGameController().getGame());
    }

    /**
     * removes the game from the gamesInPlay from the session object and sets the game to "ended" state
     * @param gameId - UUID : the game which has just ended
     * @param winner - UUID : the winner of the game
     * @param lastMove - Move : The Move that ended the game
     */
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
     * @param game - Game : game to add
     */
    public void transferSavedGame(Game game) {
        dataClientController.getUserController().addSavedGame(game);
    }

    /**
     * adds the message to the chat of the game
     * @param message - Message : message to add
     */
    @Override
    public void storeNewMessage(Message message){
        dataClientController.getGameController().getGame().getChat().add(message);
    }


    /**
     * adds the connected user, and updates the according lists of connected user to Session
     * @param players - List<Player> : connected users to add
     * @param gamesInLobby - List<GameLight> : games in Lobby to add
     * @param gamesInPlay - List<GameLight> : games in Play to add
     */
    @Override
    public void addConnectedUserProfile(List<Player> players, List<GameLight> gamesInLobby, List<GameLight> gamesInPlay) {
       //add connected user to Session
        for (Player p : players){
            dataClientController.getSession().getConnectedUsers().add(new UserLight(p));
        }

        //add game in Lobby to Session
        for (GameLight gl : gamesInLobby){
            dataClientController.getSession().getGamesInLobby().add(gl);
        }

        //add game in Play to Session
        for (GameLight glp : gamesInPlay){
            dataClientController.getSession().getGamesInPlay().add(glp);
        }
    }

    /**
     * Remove an user from the connected users of the session object
     *
     * @param userId        UID of the user to remove
     * @param isDeconnected Not used
     */
    // TODO : changer signature : pas besoin du booleen
    @Override
    public void isDisconnected(UUID userId, boolean isDeconnected){
        List<UserLight> connectedUsers = dataClientController.getSession().getConnectedUsers();
        connectedUsers.removeIf((UserLight u) -> u.getId().equals(userId));
    }

    /**
     * Retrieve all the online users
     *
     * @return List of connected users
     */
    @Override
    public List<UserLight> getOnlineUsers(){
        return dataClientController.getSession().getConnectedUsers();
    }

    /**
     * sets the game (created by the server) in the GameController.
     * @param game - Game : game created
     */
    @Override
    public void gameCreated(Game game){
        dataClientController.getGameController().setGame(game);
        dataClientController.getIhmMainToDataInterface().updateGameCreated(dataClientController.getGameController().getGame());
    }


    /**
     * adds a user to the game as opponent
     * @param gameID - UUID : id of the game
     * @param p - Player : user to add
     * @throws GameException : Exception if the game doesn't exist
     */
    @Override
    public void newPlayer(UUID gameID, Player p) throws GameException {
        Game game = dataClientController.getGameController().getGame();
        //verify the game exists
        if (game.getGameId().equals(gameID)){
            game.setOpponent(p);
            dataClientController.getIhmMainToDataInterface().askApproval(dataClientController.getGameController().getGame());
            /*todo use custom method*/
        } else {
            throw new GameException("Game created does not exists");
        }
    }


    /**
     * adds a user to the game as spectator
     * @param gameID - UUID : id of the game
     * @param spec - Player : user to add
     * @throws GameException : Exception if the game doesn't exist
     */
    @Override
    public void newSpectator(UUID gameID, Player spec) throws GameException {
        //TODO
    }

    //TODO : V3 ajouter prise en compte de bool==FALSE
    /**
     * adds or refused an opponent in a game
     * @param game - Game : game to add/refuse the opponent
     * @param isAdded - boolean : to know if the opponent is added or refused
     * @throws GameException : Exception if the opponent is refused
     */
    @Override
    public void addedPlayerInGame(Game game, boolean isAdded) throws GameException {
        if (isAdded) {
            dataClientController.getGameController().setGame(game);
            dataClientController.getIhmMainToDataInterface().updateAcceptOpponent(dataClientController.getGameController().getGame());
        } else {
            dataClientController.getIhmMainToDataInterface().updateRejectOpponent(dataClientController.getGameController().getGame());
            throw new GameException("Player was refused from game");
        }
    }


    /**
     * retrieves all the saved games of the user.
     * @return List<Game> : list of the game saved
     */
    public List<Game> getStoredAvailableGames() {
        return dataClientController.getUserController().getUser().getSavedGames();
    }

    /**
     * returns the UUID of the player currently playing
     * @return UUID
     */
    @Override
    public UUID currentUserIsPlayer(){
        return dataClientController.getGameController().getGame().getCurrentPlayerUUID();
    }

    /**
     * get profile of an user
     * @return User
     */
    @Override
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
