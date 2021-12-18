package nomad.data.server;

import nomad.common.data_structure.*;
import nomad.common.interfaces.com.ComToDataServerInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Controller for the data server.
 */
public class DataServerController {
    private UserController userController;
    private ComToDataServerInterface comOfferedInterface;
    private GamesController gamesController;

    public DataServerController(UserController userController,
                                GamesController gamesController, ComToDataServerInterface comToData) {
        this.userController = userController;
        this.gamesController = gamesController;
        this.comOfferedInterface = comToData;
    }

    /**
     * Sets the users at the specified index.
     *
     * @param user
     */
    public void setUser(User user) {
        userController.setUser(user);
    }

    /**
     * Returns the list of users.
     *
     * @return users
     */
    public List<User> getUsers() {
        return userController.getAllUsers();
    }

    /**
     * Returns the list of users UUIDS.
     *
     * @return users UUIDs
     */
    public List<UUID> getUsersUUIDs(UUID gameId) {
        List<UUID> idList = new ArrayList<>();
        Game g = gamesController.getGame(gameId);
        idList.add(g.getHost().getId());
        idList.add(g.getOpponent().getId());
        idList.addAll(g.getListOther());
        return idList;
    }

    /**
     * Returns the user at the specified index.
     *
     * @param userId
     * @return the user at the specified index.
     */
    public User getUser(UUID userId) {
        return userController.getUser(userId);
    }

    /**
     * Returns the dataToCom interface.
     *
     * @return dataToCom
     */
    public ComToDataServerInterface getComOfferedInterface() {
        return comOfferedInterface;
    }

    /**
     * Gets the user controller.
     *
     * @return userController
     */
    public UserController getUserController() {
        return userController;
    }

    /**
     * Sets tue user controller.
     *
     * @param userController
     */
    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    /**
     * Gets the games controller.
     *
     * @return gameController
     */
    public GamesController getGamesController() {
        return gamesController;
    }

    /**
     * Sets the games controller.
     *
     * @param gamesController
     */
    public void setGamesController(GamesController gamesController) {
        this.gamesController = gamesController;
    }



    /**
     * Checks if a tower is connected to another one.
     * @param g
     * @param visitedCases
     * @param currentCase
     * @param color
     * @return
     */
    private boolean towerConnected(Game g, List<Case> visitedCases, Case currentCase, boolean color, boolean testingConnection){

        if(currentCase.isTower() && !visitedCases.isEmpty()) return true;
        visitedCases.add(currentCase);

        Case[][] board = g.getBoard().getGameBoard();
        int x = currentCase.getX();
        int y = currentCase.getY();

        if(testConnectionValidity(currentCase, board, testingConnection)){
            if(x != 0 && !visitedCases.contains(board[x-1][y]) && ((board[x-1][y].isColor() == color && board[x-1][y].getHeight() > 0) || board[x-1][y].isTower())){
                if(towerConnected(g, visitedCases, board[x-1][y], color, testingConnection))
                    return true;
            }
            if(x != 12 && !visitedCases.contains(board[x+1][y]) && ((board[x+1][y].isColor() == color && board[x+1][y].getHeight() > 0) || board[x+1][y].isTower())){
                if(towerConnected(g, visitedCases, board[x+1][y], color, testingConnection))
                    return true;
            }
            if(y != 0 && !visitedCases.contains(board[x][y-1]) && ((board[x][y-1].isColor() == color && board[x][y-1].getHeight() > 0) || board[x][y-1].isTower())){
                if(towerConnected(g, visitedCases, board[x][y-1], color, testingConnection) )
                    return true;
            }
            if(y != 12 && !visitedCases.contains(board[x][y+1]) && ((board[x][y+1].isColor() == color && board[x][y+1].getHeight() > 0) || board[x][y+1].isTower())){
                if(towerConnected(g, visitedCases, board[x][y+1], color, testingConnection) )
                    return true;
            }
        }

        return false;
    }

    /**
     * Tests if all towers are connected.
     * It tests for each tower, if there is a connection.
     * @return
     */
    private int numberOfTowersConnected(Game g, boolean color, boolean testingConnection){

        int nbTowersConnected = 0;
        Case[][] board = g.getBoard().getGameBoard();

        for(Tower t : g.getTowers()){
            if(towerConnected(g, new ArrayList<>(), board[t.getX()][t.getY()], color, testingConnection))
                nbTowersConnected++;
        }

        return nbTowersConnected;
    }

    /**
     * Checks connection validity between towers for one case.
     * @param currentCase
     * @param board
     * @return
     */
    private boolean testConnectionValidity(Case currentCase, Case[][] board, boolean testingConnection){
        if(!testingConnection) return true;
        int x = currentCase.getX();
        int y = currentCase.getY();
        boolean color = currentCase.isColor();
        int height = currentCase.getHeight();

        if(x == 0){
            if(y == 0){
                if(color != board[x+1][y].isColor() && height < board[x+1][y].getHeight()){
                    return false;
                } else if(color != board[x][y+1].isColor() && height < board[x][y+1].getHeight()){
                    return false;
                }
                return true;
            } else if(y == 12){
                if(color != board[x+1][y].isColor() && height < board[x+1][y].getHeight()){
                    return false;
                } else if(color != board[x][y-1].isColor() && height < board[x][y-1].getHeight()){
                    return false;
                }
                return true;
            } else {
                if(color != board[x+1][y].isColor() && height < board[x+1][y].getHeight()){
                    return false;
                } else if(color != board[x][y+1].isColor() && height < board[x][y+1].getHeight()){
                    return false;
                } else if(color != board[x][y-1].isColor() && height < board[x][y-1].getHeight()){
                    return false;
                }
                return true;
            }
        }
        else if(y == 0){
            if(x == 12){
                if(color != board[x-1][y].isColor() && height < board[x-1][y].getHeight()){
                    return false;
                } else if(color != board[x][y+1].isColor() && height < board[x][y+1].getHeight()){
                    return false;
                }
                return true;
            } else {
                if(color != board[x+1][y].isColor() && height < board[x+1][y].getHeight()){
                    return false;
                } else if(color != board[x-1][y].isColor() && height < board[x-1][y].getHeight()){
                    return false;
                } else if(color != board[x][y+1].isColor() && height < board[x][y+1].getHeight()){
                    return false;
                }
                return true;
            }
        }
        else if(x == 12){
            if(y == 12){
                if(color != board[x-1][y].isColor() && height < board[x-1][y].getHeight()){
                    return false;
                } else if(color != board[x][y-1].isColor() && height < board[x][y-1].getHeight()){
                    return false;
                }
                return true;
            } else {
                if(color != board[x-1][y].isColor() && height < board[x-1][y].getHeight()){
                    return false;
                } else if(color != board[x][y-1].isColor() && height < board[x][y-1].getHeight()){
                    return false;
                } else if(color != board[x][y+1].isColor() && height < board[x][y+1].getHeight()){
                    return false;
                }
                return true;
            }
        }
        else if(y == 12){
            if(color != board[x-1][y].isColor() && height < board[x-1][y].getHeight()){
                return false;
            } else if(color != board[x][y-1].isColor() && height < board[x][y-1].getHeight()){
                return false;
            } else if(color != board[x+1][y].isColor() && height < board[x+1][y].getHeight()){
                return false;
            }
        }

        return true;
    }

    private UUID checkWinner(Game g, UUID host, UUID opponent){
        int nbTowersHost = numberOfTowersConnected(g, g.isHostColor(), false);
        int nbTowersOpponent = numberOfTowersConnected(g, !g.isHostColor(), false);
        if(nbTowersHost > nbTowersOpponent)
            return host;
        else if(nbTowersHost < nbTowersOpponent)
            return opponent;

        return null;
    }

    /**
     * Checks if the game is ended in case of a skip.
     * @param g - the game
     * @return the winner
     */
    public UUID checkGameEndedAfterSkip(Game g){

        int movesSize = g.getMoves().size();

        if(g.getMoves().size() > 2 && g.getMoves().get(movesSize - 1) instanceof Skip && g.getMoves().get(movesSize - 2) instanceof Skip) {
            return checkWinner(g, g.getHost().getId(), g.getOpponent().getId());
        }

        return null;
    }

    /**
     * Checks if the game is ended for a given color after a tile is placed.
     * @param g - the game
     * @param player - the player UUID
     * @return - the winner
     */
    public UUID checkGameEndedAfterTile(Game g, UUID player) {

        boolean color;
        if(g.getHost().getId().equals(player))
            color = g.isHostColor();
        else
            color = !g.isHostColor();

        if(g.getNbOfTilesPlayed() == 165){
            return checkWinner(g, g.getHost().getId(), g.getOpponent().getId());
        } else if(numberOfTowersConnected(g, color, true) == 5){
            return player;
        }

        return null;
    }
}
