package nomad.data.server;

import nomad.common.data_structure.*;
import nomad.common.interfaces.data.DataToComServerInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Concretization of the Ihm Com interface.
 */
public class DataToComConcrete implements DataToComServerInterface {

    private DataServerController dataServerController;

    public DataToComConcrete(DataServerController dataServerController){
        this.dataServerController = dataServerController;
    }

    @Override
    //Create a Game
    public Game createGame(String name, UserLight host, int nbOfTowers, boolean spectAllowed, boolean spectChatAllowed, boolean hostColour) {
        //Create a Player
        Player player = new Player(host.getId(), host.getLogin(), null); // profile picture is null ?

        //Create a Game
        Game game = new Game(player, nbOfTowers, name, new GameParameters(spectAllowed, spectChatAllowed, hostColour));

        //Add game to game In lobby
        dataServerController.getGamesController().setGame(game);

        return game;
    }

    @Override
    public void joinGameRequest(Player player, GameLight game) {
        if (dataServerController.getGamesController().getGame(game.getGameId()).getOpponent() == null){
            dataServerController.getGamesController().getGame(game.getGameId()).setOpponent(player);
            dataServerController.getComOfferedInterface().requestHost(game, player);
        }
    }

    @Override
    public Game guestAccepted(UUID gameId, UUID opponentID) {
        dataServerController.getGamesController().setGame(dataServerController.getGamesController().getGame(gameId));

        Player p = new Player(opponentID, dataServerController.getUser(opponentID).getLogin(), dataServerController.getUser(opponentID).getProfilePicture());
        dataServerController.getGamesController().getGame(gameId).setOpponent(p);
        return dataServerController.getGamesController().getGame(gameId);
    }
    /*Checks that no opponent is associated with the player*/
    @Override
    public void guestRefused(Player player) {
        for (Game g : dataServerController.getGamesController().getAllGames().values()) {
            if (g.getOpponent()==player){
                dataServerController.getGamesController().getGame(g.getGameId()).removeOpponent();
            }
        }
    }

    @Override
    public void addSpecInGame(UserLight user, GameLight game) {
        dataServerController.getGamesController().getGame(game.getGameId()).addSpec(user);
    }

    @Override
    public List<User> getUserList(GameLight game) {
        return new ArrayList<>();
    }

    @Override
    public void launchGame(UUID gameId) {
        dataServerController.getGamesController().getGame(gameId).setGameLaunched(true);
    }

    @Override
    public void saveTower(Tower t) throws TowerException {
        UUID gameID = t.getGameId();
        boolean bool = dataServerController.getGamesController().getGame(gameID).getBoard().getGameBoard()[t.getX()][t.getY()].isTower();
        if (bool) {
            // There is already a tower, we throw an exception
            throw new TowerException("A tower is already registered at those coordinates");
        }else{
            dataServerController.getGamesController().getGame(gameID).getBoard().getGameBoard()[t.getX()][t.getY()].setTower(true);
            dataServerController.getGamesController().getGame(t.getGameId()).addMove(t);
            dataServerController.getComOfferedInterface().towerValid(t, dataServerController.getGamesController().getGame(t.getGameId()).getListOther());
        }
    }

    @Override
    public void saveTile(Tile t) throws TileException {
        UUID gameID = t.getGameId();
        boolean color;
        if (t.getUserId().equals(dataServerController.getGamesController().getGame(gameID).getHost().getId())){
            //current player is the host
            color = dataServerController.getGamesController().getGame(gameID).isHostColor();
        }
        else{
            color = !dataServerController.getGamesController().getGame(gameID).isHostColor();
        }
        boolean isTower = dataServerController.getGamesController().getGame(gameID).getBoard().getGameBoard()[t.getX()][t.getY()].isTower();
        int height = dataServerController.getGamesController().getGame(gameID).getBoard().getGameBoard()[t.getX()][t.getY()].getHeight();
        //we check if there is an adjacent pile at least as high as this one (ans owned by the current player)
        boolean nearPileOK = checkPile(t, height, color);
        if (isTower || !nearPileOK) {
            // There is a problem, so we throw an exception
            throw new TileException("Tile not valid");
        }else{
            dataServerController.getGamesController().getGame(gameID).getBoard().getGameBoard()[t.getX()][t.getY()].setColor(color);
            dataServerController.getGamesController().getGame(gameID).getBoard().getGameBoard()[t.getX()][t.getY()].setHeight(height+1);
            dataServerController.getGamesController().getGame(t.getGameId()).addMove(t);
            dataServerController.getComOfferedInterface().tileValid(t, dataServerController.getGamesController().getGame(t.getGameId()).getListOther());
        }
    }

    private boolean checkPile(Tile t, int height, boolean color){
        UUID gameID = t.getGameId();
        boolean nearPileOK = false;
        for (int x = -1; x < 2; x++){
            for(int y = -1; y < 2; y++){
                if(!(x == 0 && y == 0)){
                    int height2 = dataServerController.getGamesController().getGame(gameID).getBoard().getGameBoard()[t.getX() + x][t.getY() + y].getHeight();
                    boolean color2 = dataServerController.getGamesController().getGame(gameID).getBoard().getGameBoard()[t.getX() + x][t.getY() + y].isColor();
                    if(height2 >= height && color == color2){
                        nearPileOK = true;
                    }
                }
            }
        }
        return nearPileOK;
    }
    @Override
    public void saveSkip(Skip s) {
        dataServerController.getGamesController().getGame(s.getGameId()).addMove(s);
        dataServerController.getComOfferedInterface().skipValid(s, dataServerController.getGamesController().getGame(s.getGameId()).getListOther());
    }

    @Override
    public void saveMove(UserLight user, Move m) {
        //TODO
    }

    @Override
    public boolean checkGameEnded(GameLight game) {
        return false;
    }

    @Override
    public Game getStoredGame(UUID gameId) {
        return dataServerController.getGamesController().getGame(gameId);
    }

    @Override
    public void storeMessage(UUID gameId, Message message) {
        dataServerController.getGamesController().getGame(gameId).addMessage(message);
    }

    @Override
    public List<Player> requestConnectedUserList() {
        List<Player> players = new ArrayList<>();
        for(User u : dataServerController.getUsers()){
            Player p = new Player(u.getUserId(), u.getLogin(), u.getProfilePicture());
            players.add(p);
        }
        return players;
    }

    @Override
    public List<GameLight> requestGameListInLobby() {
        return dataServerController.getGamesController().getGameLightListInLobby();
    }

    @Override
    public List<GameLight> requestGameListInPlay() {
        return dataServerController.getGamesController().getGameLightListInPlay();
    }

    @Override
    public void updateUserListAdd(User newUser) {
        dataServerController.getUserController().setUser(newUser);
    }

    /**
     * Removes a Player from the connected Users List and removes all Games in Lobby he created
     * @param userId
     */

    //TODO Removes all game from the User or just the first?
    @Override
    public User updateUserListRemove(UUID userId) {
        for (GameLight gl : dataServerController.getGamesController().getGameLightListInLobby()){
            if (gl.getHost().getId().equals(userId)){
                dataServerController.getGamesController().removeGame(gl.getGameId());
            }
        }
        return dataServerController.getUserController().removeUser(userId);
    }
    /**
     * Removes all Games initiated by user in the GameList
     * @param oldUser
     */

    //TODO marche surement pas, cf méthode updateListRemove
    @Override
    public void updateListGamesRemove(User oldUser){
        for (Game gl : dataServerController.getGamesController().getAllGames().values()){
            if (gl.getHost().getId().equals(oldUser.getUserId())){
                dataServerController.getGamesController().getAllGames().remove(gl.getGameId());
            }
        }
    }

    @Override
    public User getUserProfile(UUID idUser) {
        return dataServerController.getUser(idUser);
    }

    @Override
    public void addOpponent(UUID gameId, UUID userId) {
        Player p = new Player(userId, dataServerController.getUser(userId).getLogin(),  dataServerController.getUser(userId).getProfilePicture());
        dataServerController.getGamesController().getGame(gameId).setOpponent(p);
    }
}
