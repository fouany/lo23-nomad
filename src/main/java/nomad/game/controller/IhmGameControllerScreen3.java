package nomad.game.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import nomad.common.ihm.IhmControllerComponent;
import nomad.common.ihm.IhmScreenController;

public class IhmGameControllerScreen3 extends IhmControllerComponent {
    public IhmGameControllerScreen3(IhmScreenController screen) {
        super(screen) ;
    }

    @FXML
    GridPane gameBoard ;

    @FXML
    private void placeTower(MouseEvent event) {
        Node source = (Node)event.getSource() ;
        Integer colIndex = GridPane.getColumnIndex(source) ;
        Integer rowIndex = GridPane.getRowIndex(source)  ;
        if (colIndex != null && rowIndex != null) {
            gameBoard.add(new ImageView("../../../resources/nomad/images/sand-castle.png"), colIndex.intValue(), rowIndex.intValue()) ;
        }
    }
}


