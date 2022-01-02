package nomad.game;

import nomad.common.data_structure.Game;
import nomad.common.data_structure.Move;
import nomad.common.interfaces.game.IhmGameToDataInterface;
import nomad.game.controller.GameController;

import java.io.IOException;
import java.util.UUID;

public class IhmGameToDataConcrete implements IhmGameToDataInterface {

    GameController gameController;

    @Override
    public void updateObservable(Game game) {
    }

    @Override
    public void gameEnded(UUID winner, Move lastMove) {
        System.out.println("End game");
        try{
            gameController.showPopUpEnd();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void setGameController(GameController gameController){
        this.gameController = gameController;
    }

}
