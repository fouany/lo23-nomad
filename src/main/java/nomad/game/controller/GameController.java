package nomad.game.controller;

import nomad.common.data_structure.Game;
import nomad.common.ihm.IhmControllerComponent;
import nomad.common.ihm.IhmScreenController;
import nomad.game.IhmGameScreenController;

import java.util.Observable;
import java.util.Observer;

public class GameController extends IhmControllerComponent implements Observer {
    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */
    public GameController(IhmScreenController screen) {
        super(screen);
        Game current = ((IhmGameScreenController) super.screenControl).getLinkedGame();
        current.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Update on game");
        //TODO handle different type of updates
    }
}
