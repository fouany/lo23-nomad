package nomad.game.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import nomad.common.data_structure.Game;
import nomad.common.ihm.IhmScreenController;

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
    public void init(Game game) {
        for (int i = 0 ; i < 13 ; i++) {
            for (int j = 0 ; j < 13 ; j++) {
                Pane p = new Pane() ;
                gameBoard.add(p, i, j) ;
            }
        }
    }

    @Override
    public void update() {}

    private void playMove(MouseEvent event) {
        int[] coordinates ;
        coordinates = getCoordinates(event) ;
    }

}
