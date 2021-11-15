package nomad.common.data_structure;

import java.io.Serializable;
import java.net.InetAddress;

public class Server implements Serializable {

    private String serverName;
    private InetAddress ipAddress;
    private int port;

    public Server(String serverName, InetAddress ipAddress, int port) {
        this.serverName = serverName;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(InetAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "model.Server{" +
                "serverName='" + serverName + '\'' +
                ", ipAddress=" + ipAddress +
                ", port=" + port +
                '}';
    }
}
