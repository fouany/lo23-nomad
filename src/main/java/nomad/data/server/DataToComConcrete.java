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

    public DataToComConcrete(DataServerController dataServerController) {
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
        if (dataServerController.getGamesController().getGame(game.getGameId()).getOpponent() == null) {
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
            if (g.getOpponent() == player) {
                dataServerController.getGamesController().getGame(g.getGameId()).removeOpponent();
            }
        }
    }

    @Override
    public void addSpecInGame(UserLight user, GameLight game) {
        dataServerController.getGamesController().getGame(game.getGameId()).addSpec(user);
    }

    @Override
    public List<UUID> getUserList(GameLight game) {
        Game g = dataServerController.getGamesController().getGame(game.getGameId());
        List<UUID> users = new ArrayList<>();
        users.add(g.getHost().getId());
        users.add(g.getOpponent().getId());
        for (UserLight ul : g.getSpect()) {
            users.add(ul.getId());
        }
        return users;
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
        } else {
            dataServerController.getGamesController().getGame(gameID).getBoard().getGameBoard()[t.getX()][t.getY()].setTower(true);
            dataServerController.getGamesController().getGame(t.getGameId()).addMove(t);
            dataServerController.getComOfferedInterface().towerValid(t, dataServerController.getGamesController().getGame(t.getGameId()).getListOther());
            dataServerController.getGamesController().getGame(gameID).changeCurrentPlayer();
        }
    }

    @Override
    public void saveTile(Tile t) throws TileException {
        UUID gameID = t.getGameId();
        boolean color = t.getUserId().equals(dataServerController.getGamesController().getGame(gameID).getHost().getId());

        boolean isTower = dataServerController.getGamesController().getGame(gameID).getBoard().getGameBoard()[t.getX()][t.getY()].isTower();
        int height = dataServerController.getGamesController().getGame(gameID).getBoard().getGameBoard()[t.getX()][t.getY()].getHeight();
        //we check if there is an adjacent pile at least as high as this one (ans owned by the current player)
        boolean nearPileOK = checkPile(t, height, color);
        if (isTower || !nearPileOK) {
            // There is a problem, so we throw an exception
            throw new TileException("Tile not valid");
        } else {
            dataServerController.getGamesController().getGame(gameID).getBoard().getGameBoard()[t.getX()][t.getY()].setColor(color);
            dataServerController.getGamesController().getGame(t.getGameId()).addMove(t);
            dataServerController.getComOfferedInterface().tileValid(t, dataServerController.getGamesController().getGame(t.getGameId()).getListOther());
            dataServerController.getGamesController().getGame(gameID).changeCurrentPlayer();
        }

        UUID winner = dataServerController.checkGameEndedAfterTile(dataServerController.getGamesController().getGame(gameID), t.getUserId());
        if (winner != null) {
            dataServerController.getComOfferedInterface().gameOver(gameID, dataServerController.getUsersUUIDs(gameID), t, winner);
            dataServerController.getGamesController().getAllGames().remove(gameID);
        }
    }

    /**
     * Check if tile is in the bounds of the array and if height is positive or null
     *
     * @param t      Tile to check
     * @param height Height to check
     * @return True if tile and height are in bounds, else false
     */
    private boolean checkBounds(Tile t, int height) {
        return checkPosition(t.getX(), t.getY())
                && height >= 0;
    }

    /**
     * Check if position (x, y) is inside of board
     *
     * @param x X position
     * @param y Y position
     * @return True if position is in board, else false
     */
    private boolean checkPosition(int x, int y) {
        return x < Board.BOARDDIMENSIONS
                && y < Board.BOARDDIMENSIONS
                && x >= 0
                && y >= 0;
    }

    /**
     * Get a list of cases adjacent to the given tile (diagonal excluded).
     * Warning, no bound checks are done on parameters.
     *
     * @param t     Reference tile, must be bound checked before calling this method
     * @param board Board in the form of a 2D Case array
     * @return A list containing all adjacent cases
     */
    private List<Case> getAdjacentCases(Tile t, Case[][] board) {
        ArrayList<Case> adjacentCases = new ArrayList<>();
        int x = t.getX();
        int y = t.getY();

        if (checkPosition(x - 1, y)) {
            adjacentCases.add(board[x - 1][y]);
        }

        if (checkPosition(x + 1, y)) {
            adjacentCases.add(board[x + 1][y]);
        }

        if (checkPosition(x, y - 1)) {
            adjacentCases.add(board[x][y - 1]);
        }

        if (checkPosition(x, y + 1)) {
            adjacentCases.add(board[x][y + 1]);
        }

        return adjacentCases;
    }

    /**
     * Get the size of the highest neighbour pile of tile of the given color
     *
     * @param adjacentCases List of neighbours
     * @param color Color of the piles to consider
     * @return Size of the highest pile of given color, 0 if none was found
     */
    private int highestNeighbourOfColor(List<Case> adjacentCases, boolean color) {
        int highest = 0;
        for (Case c : adjacentCases) { // Iterate over neighboors
            if (!c.isTower()
                    && c.isColor() == color
                    && c.getHeight() > highest)
            { // Only consider tiles of same color
                highest = c.getHeight();
            }
        }

        return highest;
    }

    /**
     * Check if tile can be placed depending on nearby piles and bounds
     *
     * @param t      Tile to check
     * @param height Height of the pile before adding the tile
     * @param color  Color of the placed tile
     * @return True if the move is allowed and the tile can be placed, else false
     */
    private boolean checkPile(Tile t, int height, boolean color) {
        if (!checkBounds(t, height)) { // Invalid bounds or height
            return false;
        }

        UUID gameID = t.getGameId();
        Case[][] board = dataServerController.getGamesController().getGame(gameID).getBoard().getGameBoard();

        List<Case> adjacentList = getAdjacentCases(t, board);
        int highestNeighbour = highestNeighbourOfColor(adjacentList, color);

        return height <= highestNeighbour;
    }

    @Override
    public void saveSkip(Skip s) {
        Game g = dataServerController.getGamesController().getGame(s.getGameId());
        g.addMove(s);
        dataServerController.getComOfferedInterface().skipValid(s, dataServerController.getGamesController().getGame(s.getGameId()).getListOther());
        UUID potentialWinner = dataServerController.checkGameEndedAfterSkip(g);
        if (potentialWinner != null) {
            dataServerController.getComOfferedInterface().gameOver(g.getGameId(), dataServerController.getUsersUUIDs(g.getGameId()), s, potentialWinner);
            dataServerController.getGamesController().getAllGames().remove(g.getGameId());
        }
    }

    @Override
    public void saveMove(UserLight user, Move m) {
        //TODO
    }

    @Override
    public Game getStoredGame(UUID gameId) {
        return dataServerController.getGamesController().getGame(gameId);
    }

    @Override
    /**
     * store a message
     * @param message message to store
     */
    public void storeMessage(Message message) throws MessageException {
        int gamePresent = 0;
        for (GameLight gl : dataServerController.getGamesController().getGameLightListInPlay()) {
            if (gl.getGameId().equals(message.getGameId())) {
                gamePresent = 1;
            }
        }
        if (gamePresent == 0) {
            throw new MessageException("The message was sent to a game that doesn't exist anymore");
        }
        dataServerController.getGamesController().getGame(message.getGameId()).addMessage(message);
    }

    @Override
    public List<Player> requestConnectedUserList() {
        List<Player> players = new ArrayList<>();
        for (User u : dataServerController.getUsers()) {
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
     *
     * @param userId
     */

    //TODO Removes all game from the User or just the first?
    @Override
    public User updateUserListRemove(UUID userId) {
        for (GameLight gl : dataServerController.getGamesController().getGameLightListInLobby()) {
            if (gl.getHost().getId().equals(userId)) {
                dataServerController.getGamesController().removeGame(gl.getGameId());
            }
        }
        return dataServerController.getUserController().removeUser(userId);
    }

    /**
     * Removes all Games initiated by user in the GameList
     *
     * @param oldUser
     */

    //TODO marche surement pas, cf méthode updateListRemove
    @Override
    public void updateListGamesRemove(User oldUser) {
        for (Game gl : dataServerController.getGamesController().getAllGames().values()) {
            if (gl.getHost().getId().equals(oldUser.getUserId())) {
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
        Player p = new Player(userId, dataServerController.getUser(userId).getLogin(), dataServerController.getUser(userId).getProfilePicture());
        dataServerController.getGamesController().getGame(gameId).setOpponent(p);
    }
}
