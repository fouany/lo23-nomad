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
    public Game createGame(String name, UserLight host, int nbOfTowers, boolean spectAllowed, boolean spectChatAllowed, boolean hostColour) {
        Player player = new Player(host.getId(), host.getLogin(), null); // profile picture is null ?
        Game game = new Game(player, nbOfTowers, name, new GameParameters(spectAllowed, spectChatAllowed, hostColour));
        dataServerController.getGamesController().setGame(game);
        return game;
    }

    @Override
    public void joinGameRequest(Player player, GameLight game) {
        // Does nothing because not developped yet
    }

    @Override
    public void guestAccepted(GameLight game) {
        // Does nothing because not developped yet
    }

    @Override
    public void guestRefused(Player player) {
        // Does nothing because not developped yet
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
        // Does nothing because not developped yet
    }

    @Override
    public void saveTower(Tower t) {
        //TODO
    }

    @Override
    public void saveTile(Tile t) {
        //TODO
    }

    @Override
    public void saveMove(UserLight user, Move m) {
        //TODO
    }

    @Override
    public void saveSkip(Skip s) {
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
    public List<GameLight> requestGameList() {
        return dataServerController.getGamesController().getGameLightList();
    }

    @Override
    public void updateUserListAdd(User newUser) {
        dataServerController.getUserController().setUser(newUser);
    }

    /**
     * Removes a Player from the connected Users List
     * @param userId
     */

    @Override
    public User updateUserListRemove(UUID userId) {
        return dataServerController.getUserController().removeUser(userId);
    }
    /**
     * Removes all Games initiated by user in the GameList
     * @param oldUser
     */

    @Override
    public void updateListGamesRemove(User oldUser){
        for (GameLight gl : dataServerController.getGamesController().getGameLightList()){
            if (gl.getHost().getId()==oldUser.getUserId()){
                dataServerController.getGamesController().getGameLightList().remove(gl);
            }
        }
    }

    @Override
    public User getUserProfile(UUID idUser) {
        return dataServerController.getUser(idUser);
    }

    @Override
    public void addOpponent(UUID gameId, UUID userId) {
        //TODO
    }
}
