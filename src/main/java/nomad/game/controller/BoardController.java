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

import java.util.UUID;

public class BoardController extends GameComponentsAbstract {
    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */
    protected BoardController(IhmScreenController screen) {
        super(screen);
    }

    @FXML
    private GridPane gameBoard ;

    @FXML
    private int[] getCoordinates(MouseEvent event) {
        int[] coordinates = new int[2] ;
        Node source = (Node) event.getTarget() ;
        coordinates[0] = GridPane.getColumnIndex(source) ;
        coordinates[1] = GridPane.getRowIndex(source) ;
        return coordinates ;
    }

    @Override
    public void init() {
        for (int i = 0 ; i < 13 ; i++) {
            for (int j = 0 ; j < 13 ; j++) {
                Pane p = new Pane() ;
                gameBoard.add(p, i, j) ;
            }
        }
    }

    @Override
    public void update(String type) {}

    private void playMove(MouseEvent event) {

        /** ----- 1. IS MOVE VALID ??? ----- **/

        // GET CLICK COORDINATES
        int[] coordinates ;
        coordinates = getCoordinates(event) ;
        int i = coordinates[0] ;
        int j = coordinates[1] ;

        // GET CURRENT GAME
        GameController gameController = getGameController() ;
        Game currentGame = gameController.getCurrentGame() ;
        Case[][] currentGameBoard = currentGame.getBoard().getGameBoard() ;

        // GET CURRENT PLAYER COLOR
        UUID hostUUID = currentGame.getHost().getId() ;
        UUID currentPlayerUUID = currentGame.getCurrentPlayerUUID() ;
        boolean isCurrentPlayerColor ;
        if (hostUUID == currentPlayerUUID) { isCurrentPlayerColor = currentGame.isHostColor() ; }
        else { isCurrentPlayerColor = !currentGame.isHostColor() ; }

        boolean isValidMove = false ;
        boolean placeTowerOrTile = currentGame.getMoves().size() < 2 * currentGame.getNbOfTowers() ;

        // PLACE TILE
        if (!placeTowerOrTile) {

            int adjMaxHeight = 0, caseHeight = currentGameBoard[i][j].getHeight() ;

            if (i > 0) {
                if (currentGameBoard[i - 1][j].isColor() == isCurrentPlayerColor &&
                        currentGameBoard[i - 1][j].getHeight() > adjMaxHeight) {
                    adjMaxHeight = currentGameBoard[i - 1][j].getHeight();
                }
            }

            if (i < 12) {
                if (currentGameBoard[i + 1][j].isColor() == isCurrentPlayerColor &&
                        currentGameBoard[i + 1][j].getHeight() > adjMaxHeight) {
                    adjMaxHeight = currentGameBoard[i + 1][j].getHeight();
                }
            }

            if (j > 0) {
                if (currentGameBoard[i][j - 1].isColor() == isCurrentPlayerColor &&
                        currentGameBoard[i][j - 1].getHeight() > adjMaxHeight) {
                    adjMaxHeight = currentGameBoard[i][j - 1].getHeight();
                }
            }

            if (j < 12) {
                if (currentGameBoard[i][j + 1].isColor() == isCurrentPlayerColor &&
                        currentGameBoard[i][j + 1].getHeight() > adjMaxHeight) {
                    adjMaxHeight = currentGameBoard[i][j + 1].getHeight();
                }
            }

            if (caseHeight == 0 || caseHeight <= adjMaxHeight) { isValidMove = true ; }

        } else { // PLACE TOWER
            // VALID MOVE UNLESS THERE IS A TOWER AT LESS THAN 3 SQUARES
            isValidMove = true ;
            outer :
            for (int x = 0 ; x < 12 ; x++) {
                for (int y = 0 ; y < 12 ; y++) {
                    if (Math.abs(x - i) + Math.abs(y - j) <= 3 && currentGameBoard[i][j].isTower()) {
                        isValidMove = false ;
                        break outer ;
                    }
                }
            }
        }

        // SQUARE UNOCCUPIED BY TOWER
        if (currentGameBoard[i][j].isTower()) { isValidMove = false ; } ;

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
                Tile tile = new Tile(i, j, isCurrentPlayerColor) ;
                currentGame.getMoves().add(tile) ;
            } else {
                Tower tower = new Tower(i, j) ;
                currentGame.getMoves().add(tower) ;
            }
        }
    }

}
