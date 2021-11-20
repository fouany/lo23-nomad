package nomad.com.client;

import nomad.com.message.ComMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ComClientSender extends Thread {
    private final Socket socket;
    private final ComMessage message;

    public ComClientSender(Socket socket, ComMessage message) {
        this.socket = socket;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(message);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}