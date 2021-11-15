package nomad.data.client;

import nomad.common.data_structure.*;
import nomad.common.interfaces.data.DataToIhmGameInterface;

public class DataToGameConcrete implements DataToIhmGameInterface  {

    DataClientController dataClientController;

    public User getUser(){
        return dataClientController.getUserController().getUser();
    }

    public Player getPlayer(){
        return dataClientController.getUserController().getPlayer();
    }

    public UserLight getUserLight(){
        return dataClientController.getUserController().getUserLight();
    }

    public Game getGame(){
        return dataClientController.getGameController().getGame();
    }

    public GameLight getGameLight(){
        return dataClientController.getGameLight();
    }

    public void setProfile(User user){
        dataClientController.getUserController().setUser(user);
    }

    public void saveCurrentGame(){
        dataClientController.getUserController().addSavedGame(getGame());
    }

}
