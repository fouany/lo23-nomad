package nomad.main;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerConnectionController {

    public TextField serverIp;
    public TextField serverPort;

    public Scene getScene() {

        FXMLLoader fxmlLoader = new FXMLLoader(
                ServerConnectionController.class.getResource("gui/gui_server_connection.fxml"),
                null,
                new JavaFXBuilderFactory()
        );

        try {
            return new Scene(fxmlLoader.load());
        } catch (IOException e) {
            Logger.getLogger(ServerConnectionController.class.getName()).log(Level.SEVERE, "Scene can't be initialized", e);
        }
        return null;
    }

    private URL getFxmlUrl(String path) throws MalformedURLException {
        return new File(path).toURI().toURL();
    }

    private boolean IpFormatIsValid(String ip) {
        String zeroTo255
                = "(\\d{1,2}|(0|1)\\"
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

    private boolean portFormatIsValid(String port) {
        if (port == null || port.isEmpty())
            return false;
        String regex = "((6553[0-5])|(655[0-2][0-9])|(65[0-4][0-9]{2})|(6[0-4][0-9]{3})|([1-5][0-9]{4})|([0-5]{0,5})|([0-9]{1,4}))";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(port);
        return matcher.matches();
    }

    public void onClickConnection(MouseEvent mouseEvent) {
        String ip = serverIp.getText();
        String port = serverPort.getText();

        if (IpFormatIsValid(ip) && portFormatIsValid(port)) {
            Logger.getLogger(ServerConnectionController.class.getName()).log(Level.INFO, "Trying to connect on " + ip + " : " + port);
            //todo: call update profile de data
        } else {
            Logger.getLogger(ServerConnectionController.class.getName()).log(Level.INFO, "Error on Ip address or in port");
            return;
        }

    }
}
