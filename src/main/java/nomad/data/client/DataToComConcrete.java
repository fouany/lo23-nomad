package nomad.data.client;

import nomad.common.data_structure.*;
import nomad.common.interfaces.data.DataToComClientInterface;

import java.util.List;
import java.util.UUID;

public class DataToComConcrete implements DataToComClientInterface {

    DataClientController dataClientController;

    public void setController(DataClientController dataClientController) {
        this.dataClientController = dataClientController;
    }

    /**
     * adds a new game in the lobby and sets the attributes in the game
     * @param gameLight
     * @param name
     * @param spect
     * @param spectatorChat
     */
    public void updateSession(GameLight gameLight, String name, boolean spect, boolean spectatorChat){
        dataClientController.getSession().getGamesInLobby().add(gameLight);
        dataClientController.getGameController().getGame().setName(name);
        dataClientController.getGameController().getGame().setSpectAllowed(spect);
        dataClientController.getGameController().getGame().setSpectChatAllowed(spectatorChat);
    }

    /**
     * removes the game from the lobby and adds the game in the games in play (in the Session object)
     * @param gameId
     * @param gameLaunched
     */
    public void updateSessionGameState(UUID gameId, boolean gameLaunched){
        GameLight gameToRemove = dataClientController.getSession().getGameInLobbyById(gameId);
        dataClientController.getSession().getGamesInLobby().remove(gameToRemove);

        dataClientController.getGameController().getGame().setGameLaunched(gameLaunched);
        // not sure if it's the right game being retrieved below
        GameLight gameToAdd = dataClientController.getGameController().getGameLight();
        dataClientController.getSession().getGamesInPlay().add(gameToAdd);
    }

    /**
     * adds a user light to the connected user of the session object
     * @param player
     * @param connected
     */
    // TODO : pas besoin du booleen !
    public void updateUserSession(Player player, boolean connected){
        if (player.getId() == dataClientController.getUserController().getUser().getUserId()) {
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
        if (gameLight.getGameId() == dataClientController.getGameController().getGame().getGameId()){
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
    public void handleSpectator(UserLight userLight, boolean isAdded){
        dataClientController.getGameController().getGame().getSpect().add(userLight);
    }

    // TODO: game is already set to "launched" in updateSessionGameState,
    //  this method might only need to update the observable
    public void gameLaunchEvent(){
        // TODO implementation
    }

    /**
     * adds the move to the list of moves of the game and changes the current player
     * @param tower
     * @param valid
     */
    public void towerValid(Tower tower, boolean valid) {
        dataClientController.getGameController().getGame().getMoves().add(tower);
        dataClientController.getGameController().getGame().changeCurrentPlayer();
    }

    /**
     * adds the move to the list of moves of the game and changes the current player
     * @param tile
     * @param valid
     */
    public void tileValid(Tile tile, boolean valid){
        dataClientController.getGameController().getGame().getMoves().add(tile);
        dataClientController.getGameController().getGame().changeCurrentPlayer();
    }

    /**
     * adds the move to the list of moves of the game and changes the current player
     * @param skip
     * @param valid
     */
    public void skipValidation(Skip skip, boolean valid){
        dataClientController.getGameController().getGame().getMoves().add(skip);
        dataClientController.getGameController().getGame().changeCurrentPlayer();
    }

    @Override
    // TODO : not necessary, could be removed
    public void moveReceived(Move m, UserLight user) {
        // TODO implementation
    }

    /**
     * removes the game from the gamesInPlay from the session object and sets the game to "ended" state
     * @param gameLight
     * @param gameEnded
     */
    public void endGame(GameLight gameLight, boolean gameEnded){
        GameLight gameToRemove = dataClientController.getSession().getGameInPlayById(gameLight.getGameId());
        dataClientController.getSession().getGamesInPlay().remove(gameToRemove);
        dataClientController.getGameController().getGame().setGameEnded(gameEnded);
    }

    /**
     * adds the game to the saved games of the user
     * @param game
     */
    public void transferSavedGame(Game game){
        dataClientController.getUserController().addSavedGame(game);
    }

    /**
     * adds the message to the chat of the game
     * @param message
     */
    public void storeNewMessage(Message message){
        dataClientController.getGameController().getGame().getChat().add(message);
    }

    @Override
    // TODO : supprimer cette méthode car déjà implémentée dans updateUserSession
    // Non : On rajoute aussi la liste des Games en lobby
    public void addConnectedUserProfile(List<Player> players, List<GameLight> games) {
        for (Player p : players){
            dataClientController.getSession().getConnectedUsers().add(new UserLight(p));
        }

        for (GameLight gl : games){
            dataClientController.getSession().getGamesInLobby().add(gl);
        }
    }

    /**
     * Remove an user from the connected users of the session object
     * @param userId UID of the user to remove
     * @param isDeconnected Not used
     */
    // TODO : changer signature : pas besoin du booleen
    public void isDisconnected(UUID userId, boolean isDeconnected){
        List<UserLight> connectedUsers = dataClientController.getSession().getConnectedUsers();
        connectedUsers.removeIf((UserLight u) -> u.getId() == userId);
    }

    /**
     * Retrieve all the online users
     * @return List of connected users
     */
    public List<UserLight> getOnlineUsers(){
        return dataClientController.getSession().getConnectedUsers();
    }

    /**
     * Sets the game (created by the server) in the GameController.
     * @param game
     */
    public void gameCreated(Game game){
        dataClientController.getGameController().setGame(game);
    }

    /**
     * Adds an user to the game. (as spectator or opponent)
     * @param gameLight
     * @param player
     * @param isPlayer
     */
    public void newUser(GameLight gameLight, Player player, boolean isPlayer) throws GameException {
        Game game = dataClientController.getGameController().getGame();

        if (game.getGameId() == gameLight.getGameId()){
            if (isPlayer) {
                game.setOpponent(player);
            } else {
                game.getSpect().add(new UserLight(player.getId(), player.getLogin()));
            }
        } else{
            throw new GameException("Game created does not exists");
        }
    }

    /**
     * Retrieves the saved games of the user.
     * @return
     */
    public List<Game> getStoredAvailableGames(){
        return dataClientController.getUserController().getUser().getSavedGames();
    }

    /**
     * returns the UUID of the player currently playing
     * @return
     */
    //TODO : modifier diag : type retour UUID et non pas booleen
    @Override
    public UUID currentUserIsPlayer(){
        return dataClientController.getGameController().getGame().getCurrentPlayerUUID();
    }

    @Override
    public void enoughPlayers(GameLight game) {
        //TODO
    }

    @Override
    public void rejectPlayers(GameLight game) {
        //TODO
    }

    @Override
    public UserLight getUserLight() {
        return dataClientController.getUserController().getUserLight();
    }

}
