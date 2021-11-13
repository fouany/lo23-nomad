package nomad.main.resources.controller;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import nomad.common.ihm.IhmControllerComponent;
import nomad.common.ihm.IhmScreenController;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerConnectionController extends IhmControllerComponent {

  public TextField serverIp;
  public TextField serverPort;

  public ServerConnectionController(IhmScreenController screen) {
    super(screen);
  }
  @SuppressWarnings("unused")
  private URL getFxmlUrl(String path) throws MalformedURLException {
    return new File(path).toURI().toURL();
  }

  private boolean IpFormatIsValid (String ip) {
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

  public void onClickConnection() throws IOException {
    String ip = serverIp.getText();
    String port = serverPort.getText();

    if(IpFormatIsValid(ip) && portFormatIsValid(port)) {
      screenControl.changeModule();
    } else {
      Logger.getLogger(ServerConnectionController.class.getName()).log(Level.INFO,"Error on Ip address or in port" );
    }

  }
}
