package nomad.common.data_structure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Tests the Server class.
 */
class ServerTest {

    @Test
    void constructorTest() throws UnknownHostException {

        InetAddress address_ip = InetAddress.getByName("localhost");
        Server s1 = new Server("Server_name", address_ip, 0);
        Server s2 = new Server("Server_name",address_ip, 0);

        Assertions.assertEquals("Server_name", s1.getServerName());
        Assertions.assertEquals(address_ip, s1.getIpAddress());
        Assertions.assertEquals(0, s1.getPort());

        Assertions.assertEquals("Server_name", s2.getServerName());
        Assertions.assertEquals(address_ip, s2.getIpAddress());
        Assertions.assertEquals(0, s2.getPort());

    }

    @Test
    void setTest() throws UnknownHostException {

        InetAddress address_ip = InetAddress.getByName("localhost");
        Server s1 = new Server("Server_name", address_ip, 0);
        s1.setServerName("Server_Name");
        s1.setIpAddress(address_ip);
        s1.setPort(0);
        Assertions.assertEquals("Server_Name", s1.getServerName());
        Assertions.assertEquals(address_ip, s1.getIpAddress());
        Assertions.assertEquals(0, s1.getPort());
    }
}