package nomad.game.controller;

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

    @FXML
    public void getCoordinates(MouseEvent event) {
        Node source = (Node) event.getTarget();

        Game currentGame = getGameController().getCurrentGame();
        Move move;
        if (currentGame.getMoves().size() < currentGame.getNbOfTowers()) { // Place a tower
            move = new Tower(GridPane.getColumnIndex(source), GridPane.getRowIndex(source));
        } else { // Place a tile
            move = new Tile(GridPane.getColumnIndex(source), GridPane.getRowIndex(source), true);
        }
        ((IhmGameScreenController) screenControl).getComInterface().playMove(move);
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

    @Override
    public void update(String type) {
        System.out.println(type);
        // TODO : implement board update
    }

    private void playMove(MouseEvent event) {

        /** ----- 1. IS MOVE VALID ??? ----- **/

        // GET CLICK COORDINATES
        int[] coordinates = {0, 0};
        //coordinates = getCoordinates(event) ;
        int i = coordinates[0];
        int j = coordinates[1];

        // GET CURRENT GAME
        GameController gameController = getGameController();
        Game currentGame = gameController.getCurrentGame();
        Case[][] currentGameBoard = currentGame.getBoard().getGameBoard();

        // GET CURRENT PLAYER COLOR
        UUID hostUUID = currentGame.getHost().getId();
        UUID currentPlayerUUID = currentGame.getCurrentPlayerUUID();
        boolean isCurrentPlayerColor;
        if (hostUUID == currentPlayerUUID) {
            isCurrentPlayerColor = currentGame.isHostColor();
        } else {
            isCurrentPlayerColor = !currentGame.isHostColor();
        }

        boolean isValidMove = false;
        boolean placeTowerOrTile = currentGame.getMoves().size() < 2 * currentGame.getNbOfTowers();


        // SQUARE UNOCCUPIED BY TOWER
        if (currentGameBoard[i][j].isTower()) {
            isValidMove = false;
        }
        ;

        /** ----- 2. PLAY MOVE OR DISPLAY ERROR MESSAGE ----- **/

        if (!isValidMove) { // ERROR
            final Stage dialog = new Stage();
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Invalid move, you may play again."));
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        } else { // VALID
            if (placeTowerOrTile) {
                Tile tile = new Tile(i, j, isCurrentPlayerColor);
                currentGame.getMoves().add(tile);
            } else {
                Tower tower = new Tower(i, j);
                currentGame.getMoves().add(tower);
            }
        }
    }
}
