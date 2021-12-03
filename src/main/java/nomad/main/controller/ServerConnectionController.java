package nomad.main.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import nomad.common.data_structure.UserException;
import nomad.common.ihm.IhmControllerComponent;
import nomad.main.IhmMainScreenController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerConnectionController extends IhmControllerComponent {

    @FXML
    public TextField serverIp;
    @FXML
    public TextField serverPort;


    private IhmMainScreenController ihmMainScreenController;
    public ServerConnectionController(IhmMainScreenController screen) {
        super(screen);
        ihmMainScreenController = screen;
    }

    private boolean ipFormatIsValid(String ip) {
        String zeroTo255
                = "(\\d{1,2}|([01])\\"
                + "d{2}|2[0-4]\\d|25[0-5])";

        String regex
                = zeroTo255 + "\\."
                + zeroTo255 + "\\."
                + zeroTo255 + "\\."
                + zeroTo255;

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }

    /**
     * Check if a given string is a valid port number (0 < port < 65536)
     *
     * @param port a string containing a potential port number
     * @return True if the port is valid, else false
     */
    private boolean portFormatIsValid(String port) {
        int portNumber;
        try {
            portNumber = Integer.parseInt(port);
        } catch (NumberFormatException e) {
            return false;
        }

        return portNumber > 0 && portNumber < 65536;
    }

    public void onClickConnection() throws IOException, UserException, ClassNotFoundException {
        String ip = serverIp.getText();
        String port = serverPort.getText();

        if (ipFormatIsValid(ip) && portFormatIsValid(port)) {
            String user = ihmMainScreenController.getAttributes().get("login");
            String password = ihmMainScreenController.getAttributes().get("password");
            boolean signup =  Boolean.parseBoolean(ihmMainScreenController.getAttributes().get("isSignup"));
            if (signup) {
                //TODO: modify account scene
                ihmMainScreenController.getDataI().createAccount(user,password,user,"", null);
            }
            try {
                ihmMainScreenController.getDataI().login(user, password, ip, Integer.parseInt(port));
                screenControl.changeScreen(2);
            } catch (FileNotFoundException e) {
                //TODO: ajouter back pour revenir
                DialogController.display("Erreur connexion", "Veuillez vérifier votre login ou password ou les options du serveur",
                        DialogController.DialogStatus.ERROR, this.ihmMainScreenController);
                screenControl.changeScreen(0);
            }
        } else {
            Logger.getLogger(ServerConnectionController.class.getName()).log(Level.INFO, "Error on Ip address or in port");
        }
    }
}
