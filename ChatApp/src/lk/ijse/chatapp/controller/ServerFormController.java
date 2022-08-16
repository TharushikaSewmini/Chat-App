package lk.ijse.chatapp.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lk.ijse.chatapp.ClientHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerFormController {

    public TextArea txtTextArea;
    public TextField txtMessage;

    int PORT = 5000;
    ServerSocket serverSocket;
    Socket accept;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    ArrayList<ClientHandler> client = new ArrayList<>();
    String message = "";

    public void initialize() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(PORT);
                txtTextArea.appendText("Server Started..");
                while (true) {

                    accept = serverSocket.accept();
                    txtTextArea.appendText("\nClient Accepted!");
                    System.out.println("Client Accepted!");

                    ClientHandler clientHandler = new ClientHandler(accept, client);
                    client.add(clientHandler);
                    System.out.println(client.toString());

                    clientHandler.start();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }

    public void sendOnAction(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(txtMessage.getText().trim());
        dataOutputStream.flush();
    }
}
