package nomad.com.server;

import nomad.com.common.message.ComMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;

public class IdentifiedClient extends Thread {
    public IdentifiedClient(UUID id, Socket socket, MessageProcessor messageProcessor) throws IOException {
        this.id = id;
        this.socket = socket;
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.inputStream = new ObjectInputStream(socket.getInputStream());
        this.messageProcessor = messageProcessor;
    }

    private UUID id;
    private Socket socket;
    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;
    private final MessageProcessor messageProcessor;

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public UUID getUID() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (inputStream.available() > 0) {
                    messageProcessor.processMessage(socket, (ComMessage) inputStream.readObject());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}