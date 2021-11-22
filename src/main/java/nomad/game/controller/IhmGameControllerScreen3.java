package nomad.game.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import nomad.common.ihm.IhmControllerComponent;
import nomad.common.ihm.IhmScreenController;

public class IhmGameControllerScreen3 extends IhmControllerComponent {

    public IhmGameControllerScreen3(IhmScreenController screen) {
        super(screen) ;
    }

    @FXML
    private GridPane gameBoard ;

    @FXML
    private Label label_1 ;

    @FXML
    private TextField chatTextField ;

    @FXML
    private TextArea chatTextArea ;

    @FXML
    private Pane testPane ;

    @FXML
    private void placeTower(MouseEvent event) {
        Node source = (Node) event.getSource() ;
        Integer colIndex = GridPane.getColumnIndex(source) ;
        Integer rowIndex = GridPane.getRowIndex(source) ;
        label_1.setText("(" + colIndex + ", " + rowIndex + ")") ;
        if (colIndex != null && rowIndex != null) {
            gameBoard.add(new ImageView("../../resources/com.example.a21_lo23_td1_tests/images/sand-castle.png"), colIndex, rowIndex) ;
        }
    }

    @FXML
    private void sendMessage(MouseEvent event) {
        String chatTextAreaContent = chatTextArea.getText(), chatTextFieldContent = chatTextField.getText() ;
        chatTextArea.setText(chatTextAreaContent + "\n" + chatTextFieldContent) ;
    }

    @FXML
    private void colorizePane(MouseEvent event) {
        testPane.setStyle("-fx-background-color: #726a6a;");
    }

    @FXML
    private void resetPaneColor(MouseEvent event) {
        testPane.setStyle("-fx-background-color: #ffffff;");
    }

}


