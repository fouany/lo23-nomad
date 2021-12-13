package nomad.data.client;

import nomad.common.data_structure.*;
import nomad.common.interfaces.data.DataToIhmGameInterface;

import java.io.IOException;

public class DataToGameConcrete implements DataToIhmGameInterface  {

    DataClientController dataClientControllerGame;

    public void setDataClientController(DataClientController dataClientController) {
        this.dataClientControllerGame = dataClientController;
    }

    public User getUser(){
        return dataClientControllerGame.getUserController().getUser();
    }

    public Player getPlayer(){
        return dataClientControllerGame.getUserController().getPlayer();
    }

    public UserLight getUserLight(){
        return dataClientControllerGame.getUserController().getUserLight();
    }

    public Game getGame(){
        return dataClientControllerGame.getGameController().getGame();
    }

    public GameLight getGameLight(){
        return dataClientControllerGame.getGameLight();
    }

    public void setProfile(User user){
        dataClientControllerGame.getUserController().setUser(user);
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

}
