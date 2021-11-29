package nomad.data.client;

import nomad.common.data_structure.*;
import nomad.common.interfaces.data.DataToIhmGameInterface;

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

    public void saveCurrentGame(){
        dataClientControllerGame.getUserController().addSavedGame(getGame());
    }

}
