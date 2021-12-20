package nomad.game.controller;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nomad.common.data_structure.*;
import nomad.common.ihm.IhmScreenController;
import nomad.game.IhmGameScreenController;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoardController extends GameControllerAbstract {
    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */
    public BoardController(IhmScreenController screen) {
        super(screen);
    }

    @FXML
    private GridPane gameBoard;

    private boolean played = false;

    @FXML
    public void getCoordinates(MouseEvent event) {
        Node source = (Node) event.getTarget();

        Game currentGame = getGameController().getCurrentGame();
        User currentUser = getGameController().getGameScreen().getDataInterface().getUser();
        Move move;
        if(!played && currentGame.getCurrentPlayerUUID().equals(currentUser.getUserId())) {
            if (currentGame.getMoves().size() < currentGame.getNbOfTowers()) { // Place a tower
                move = new Tower(GridPane.getColumnIndex(source), GridPane.getRowIndex(source));
            } else { // Place a tile
                move = new Tile(GridPane.getColumnIndex(source), GridPane.getRowIndex(source), true);
            }
            move.setGameId(currentGame.getGameId());
            move.setUserId(currentGame.getCurrentPlayerUUID());
            ((IhmGameScreenController) screenControl).getComInterface().playMove(move);
            played=true;
        }
        else{
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Not your turn!");
        }
    }

    @Override
    public void init() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                Pane p = new Pane();
                gameBoard.add(p, i, j);
            }
        }
    }

    public void update() {
        ObservableList<Move> move = getGameController().getCurrentGame().getMoves();
        played=false;
        for (Move newMove: move){
            if (newMove instanceof Tower){
                //Draw tower
            }else if (newMove instanceof Tile){
                //Draw tile
            }
        }
    }
}
