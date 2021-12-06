package nomad.main.controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import nomad.com.common.exception.MainException;
import nomad.common.data_structure.Game;
import nomad.common.data_structure.UserLight;
import nomad.common.ihm.IhmControllerComponent;
import nomad.main.IhmMainScreenController;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CreateGameController extends IhmControllerComponent {

    private final IhmMainScreenController ihmMainScreenController;
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
    private boolean color;
    private Random rand;

    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */
    public CreateGameController(IhmMainScreenController screen) {
        super(screen);
        this.ihmMainScreenController = screen;
        try {
            this.rand = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(CreateGameController.class.getName()).log(Level.SEVERE,"Random Color failed");
        }
    }


    public void onClickBack() {
        screenControl.changeScreen(2);
    }

    private void resetCheckBoxes() {
        red.setSelected(false);
        white.setSelected(false);
        random.setSelected(false);

    }

    public void onCheckColor(ActionEvent e) throws MainException {
        CheckBox checkbox = (CheckBox) e.getSource();
        String id = checkbox.getId();
        boolean checked = checkbox.isSelected();
        resetCheckBoxes();
        checkbox.setSelected(checked);
        if (!checked) {
            int nb;
            nb = rand.nextInt(2);
            boolean b = nb!=0 ;
            color = b;
        }
        switch (id) {
            case "red":
                color = true;
                break;
            case "white":
                color = false;
                break;
            case "random":
                int nb;
                nb = rand.nextInt(2);
                boolean b = nb!=0;
                color = b;
                break;
            default:
                throw new MainException("Unknown Color");
        }

    }

    //Todo Logger à reformatter/remplacer ?
    private void log(String data) {
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, data);
    }


    public void onCheckAllow() {
        if (allowViewers.isSelected()) {
            allowViewersChat.setDisable(false);
            return;
        }
        allowViewersChat.setDisable(true);
        allowViewersChat.setSelected(false);
    }


    public boolean validate() {
        return !gameName.getText().equals("");
    }

    public void displayWaitingRoom(Game game) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(game.getHost().getId().equals(ihmMainScreenController.getDataI().getPlayer().getId()))
                {
                    DialogController.display("Partie créée", "Votre partie a bien été créée", DialogController.DialogStatus.SUCCESS, ihmMainScreenController);

                }
                else {
                    DialogController.display("Match accepté", "Vous avez été accepté comme opposant", DialogController.DialogStatus.SUCCESS, ihmMainScreenController);

                }
                ihmMainScreenController.changeScreen(6);
            }
        });
    }

    public void onClickCreateGame() {
        if (!validate()) {
            DialogController.display("Formulaire incomplet", "Veuillez renseigner tous les champs", DialogController.DialogStatus.WARNING, this.ihmMainScreenController);
            return;
        }
        log(gameName.getText());
        log(String.valueOf((int) towerNumber.getValue()));
        UserLight user = ihmMainScreenController.getDataI().getUserLight();

        ihmMainScreenController.getComI().newGame(gameName.getText(), user, (int) towerNumber.getValue(), allowViewers.isSelected(), allowViewersChat.isSelected(), color);


    }


}
