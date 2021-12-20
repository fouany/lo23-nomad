package nomad.data.client;

import nomad.common.data_structure.*;
import nomad.common.interfaces.data.DataToIhmGameInterface;

import java.io.IOException;
import java.util.UUID;

/**
 * Concretion of the game Interface.
 */
public class DataToGameConcrete implements DataToIhmGameInterface  {

    /**
     * The main class of the client package
     */
    DataClientController dataClientControllerGame;

    /**
     * sets the controller
     * @param dataClientController - DataClientController : controller
     */
    public void setDataClientController(DataClientController dataClientController) {
        this.dataClientControllerGame = dataClientController;
    }

    /**
     * gets the current user
     * @return User
     */
    @Override
    public User getUser(){
        return dataClientControllerGame.getUserController().getUser();
    }

    /**
     * gets the current user in Player format
     * @return Player
     */
    @Override
    public Player getPlayer(){
        return dataClientControllerGame.getUserController().getPlayer();
    }

    /**
     * gets the current user in UserLight format
     * @return UserLight
     */
    @Override
    public UserLight getUserLight(){
        return dataClientControllerGame.getUserController().getUserLight();
    }

    /**
     * gets the current Game
     * @return Game
     */
    @Override
    public Game getGame(){
        return dataClientControllerGame.getGameController().getGame();
    }

    /**
     * gets the current Game in GameLight format
     * @return GameLight
     */
    @Override
    public GameLight getGameLight(){
        return dataClientControllerGame.getGameLight();
    }

    /**
     * sets the profile of the current user
     * @param user - User : new profile of the user
     */
    @Override
    public void setProfile(User user){
        dataClientControllerGame.getUserController().setUser(user);
    }

    @Override
    public void getProfilInfos(UUID userId) {
        dataClientControllerGame.getComToDataInterface().getProfileGame(userId);
    }

    /**
     * save the current game
     * @throws IOException error in file writing
     */
    @Override
    public void saveCurrentGame() throws IOException {
        //Modify the user file : add the game
        try{
                //add the game to the game saved
                dataClientControllerGame.getUserController().addSavedGame(getGame());

                //write changes in the local file
                dataClientControllerGame.write(dataClientControllerGame.getUserController().getUser());
            } catch (IOException e) {
                //error : game no saved
                dataClientControllerGame.getUserController().removeSavedGame(getGame());
                throw e;
            }
        }

    @Override
    public boolean getUserColor(UUID userID){
        Game game = dataClientControllerGame.getGameController().getGame();
        if(game.getHost().getId().equals(userID))
            return game.getGameParameters().isHostColor();
        else
            return !game.getGameParameters().isHostColor();
    }
}
