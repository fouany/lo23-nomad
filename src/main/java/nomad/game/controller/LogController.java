package nomad.game.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import nomad.common.data_structure.*;
import nomad.common.ihm.IhmScreenController;

import java.sql.Timestamp;
import java.util.UUID;

public class LogController extends GameComponentsAbstract{
    /**
     * Constructor that link the screen controller to the component controller
     *
     * @param screen
     */
    protected LogController(IhmScreenController screen) {
        super(screen);
    }

    @FXML
    public TextArea logs;

    @Override
    public void init() {

    }

    @Override
    public void update(String type, Object value) {
        if(type=="Move"){
            Move move = (Move)value;
            updateLogsMove(move);
        }else if(type=="UserLight"){
            UserLight userLight = (UserLight) value;
            updateLogsViewers(userLight);
        }

    }

    public void updateLogsMove(Move move) {
        UUID senderUUID = move.getUserId();
        //TODO : replace UUID by User login in display
        if (move instanceof Tile)
        {
            String newMove = senderUUID +" placed a tile in "+ ((Tile) move).getX()+ ","+ ((Tile) move).getY()+ "\n";
            logs.appendText(newMove);
        }
        else if (move instanceof Tower)
        {
            String newMove = senderUUID +" placed a tower in "+ ((Tower) move).getX()+ ","+ ((Tower) move).getY()+ "\n";
            logs.appendText(newMove);
        }
        else if (move instanceof Skip)
        {
            String newMove = senderUUID +" skipped his turn "+ "\n";
            logs.appendText(newMove);
        }
    }

    public void updateLogsViewers(UserLight userLight) {
        String senderLogin = userLight.getLogin();
        String newMove = senderLogin +" joined the game "+ "\n";
        logs.appendText(newMove);
    }
}
