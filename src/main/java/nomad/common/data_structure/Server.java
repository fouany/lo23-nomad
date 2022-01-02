package nomad.common.data_structure;

import java.io.Serializable;
import java.net.InetAddress;

/**
 * Represents a Server
 */
public class Server implements Serializable {

    /**
     * Server Name
     */
    private String serverName;

    /**
     * Server IP address
     */
    private InetAddress ipAddress;

    /**
     * Server Port for communication
     */
    private int port;

    /**
     * Server constructor
     * @param serverName - Server name
     * @param ipAddress - Server IP address
     * @param port - Server Port for communication
     */
    public Server(String serverName, InetAddress ipAddress, int port) {
        this.serverName = serverName;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    /**
     * Returns the server name
     * @return the server name as a String
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * Sets the server name
     * @param serverName - Server name as a String
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * Returns the server IP address
     * @return the server IP address as an InetAddress
     */
    public InetAddress getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets the server IP address
     * @param ipAddress - Server IP address as an InetAddress
     */
    public void setIpAddress(InetAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * Returns the server port
     * @return the server port as an int
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets the server port
     * @param port - Server port as an int
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Returns a string representation of the object
     * @return String
     */
    @Override
    public String toString() {
        return "model.Server{" +
                "serverName='" + serverName + '\'' +
                ", ipAddress=" + ipAddress +
                ", port=" + port +
                '}';
    }
}
