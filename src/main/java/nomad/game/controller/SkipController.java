package nomad.game.controller;


import javafx.fxml.FXML;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.Move;
import nomad.common.data_structure.Skip;
import nomad.common.ihm.IhmScreenController;
import nomad.game.IhmGameScreenController;

public class SkipController extends GameControllerAbstract {

    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */
    public SkipController(IhmScreenController screen) {
        super(screen) ;
    }

    @Override
    public void init() {}

    public void update(String type) {}

    @FXML
    private void skipMove() {
        Move move = new Skip(true);
        move.setGameId(getGameController().getCurrentGame().getGameId());
        move.setUserId(getGameController().getCurrentGame().getCurrentPlayerUUID());
        ((IhmGameScreenController) screenControl).getComInterface().playMove(move);
    }

}
