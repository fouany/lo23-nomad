package nomad.main.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import nomad.common.ihm.IhmControllerComponent;
import nomad.main.IhmMainScreenController;

import java.util.ArrayList;
import java.util.List;

public class CreateGameController extends IhmControllerComponent {

    @FXML
    public CheckBox random;
    @FXML
    public CheckBox white;
    @FXML
    public CheckBox red;
    @FXML
    public TextField gameName;
    @FXML
    public Slider towerNumber;
    @FXML
    public CheckBox allowViewers;
    @FXML
    public CheckBox allowViewersChat;


    public ArrayList<CheckBox> checkboxes = new ArrayList<>();



    private enum Color {
        RED,
        WHITE,
        RANDOM,
        NULL, //if no color is clicked
    }
    private Color color = Color.NULL;
    /*todo replace boolean with custom enum*/

    //Todo CUstom textfiled for only numbers ( towernumber )


    private IhmMainScreenController ihmMainScreenController;

    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */
    public CreateGameController(IhmMainScreenController screen) {
        super(screen);
        this.ihmMainScreenController = screen;


    }



    public void onClickBack() {
        screenControl.changeScreen(2);
    }

    private void resetCheckBoxes() {
        //not working ???
        /*for (CheckBox checkbox : checkboxes) {
            checkbox.setSelected(false);
        }*/

        red.setSelected(false);
        white.setSelected(false);
        random.setSelected(false);

    }

    public void onCheckColor(ActionEvent e)
    {
        CheckBox checkbox = (CheckBox) e.getSource();
        String id = checkbox.getId();
        boolean checked = checkbox.isSelected();
        resetCheckBoxes();
        checkbox.setSelected(checked);
        if(!checked)
        {
            color = Color.NULL;
            return;
        }
        switch (id)
        {
            //Todo Uncheck all boxes except the selected
            case "red" :
                color = Color.RED;
                break;

            case "white":
                color = Color.WHITE;
                break;

            case "random" :
                color = Color.RANDOM;
                break;
            default:
                Log("Error");
                /*todo throw exception*/

        }

    }

    private void Log(String log)
    {

        System.out.println(log);
    }


    public void onCheckAllow() {
        if(allowViewers.isSelected()) {
            allowViewersChat.setDisable(false);
            return;
        }
        allowViewersChat.setDisable(true);
        allowViewersChat.setSelected(false);


    }




    public boolean validate(){
        return color != Color.NULL && !gameName.getText().equals("");
    }

    public void onClickCreateGame() {
        if(!validate()) {
            Alert al = new Alert(AlertType.INFORMATION);
            al.setHeaderText("Look, an Information Dialog");
            al.setContentText("Please fill blank fields");
            al.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                }


                return;
            });

            Log(gameName.getText());
            Log(String.valueOf((int)towerNumber.getValue()));
            Log(color.name());
            //ihmMainScreenController.getAttributes().put("gameName", gameName.getText());
            //ihmMainScreenController.getAttributes().put("towerNumber", String.valueOf(towerNumber.getValue()));

        }
    }
    


}
