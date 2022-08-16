package lk.ijse.chatapp.Client.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientFormsController {

    public TextArea txtTextArea;
    public TextField txtMessage;
    public ImageView imgSend;
    public ImageView imgSendImage;

    int PORT = 6000;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message = "";

    public void initialize() {
        new Thread(() -> {
            try {
                socket = new Socket("localhost",PORT);

                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                while(!message.equals("exit")) {
                        message = dataInputStream.readUTF();
                    if (!txtMessage.getText().trim().equalsIgnoreCase(message)) {
                        txtTextArea.appendText("\n\nClient: " + message);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }

    public void sendOnAction(ActionEvent actionEvent) throws IOException {
        if (!txtMessage.getText().equalsIgnoreCase("")) {
            dataOutputStream.writeUTF(txtMessage.getText().trim());
            dataOutputStream.flush();
            txtTextArea.appendText("\n\nYou: " + txtMessage.getText().trim());
        }
    }
}
