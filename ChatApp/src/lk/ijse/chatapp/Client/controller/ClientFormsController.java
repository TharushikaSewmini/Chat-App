package lk.ijse.chatapp.Client.controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientFormsController {

    public TextArea txtTextArea;
    public TextField txtMessage;
    public ImageView imgSend;
    public ImageView imgSendImage;
    public Label lblUserName;
    public ImageView imgSendEmoji;

    int PORT = 6000;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    String message = "";

    public void initialize() {
        lblUserName.setText(ClientLoginFormController.userName);
        String userName= lblUserName.getText();
        new Thread(() -> {
            try {
                socket = new Socket("localhost",PORT);

                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                while(!message.equals("Exit")) {
                        message = dataInputStream.readUTF();
                    if (!txtMessage.getText().trim().equalsIgnoreCase(message)) {
                        txtTextArea.appendText("\n\nClient: " + message);
                        txtMessage.clear();
                    }
                }
                socket.close();
                dataInputStream.close();
                dataOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }

    public void MouseClickedOnAction(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getSource() instanceof ImageView) {
            imgSend = (ImageView) mouseEvent.getSource();

            dataOutputStream.writeUTF(txtMessage.getText().trim());
            dataOutputStream.flush();
            txtTextArea.appendText("\n\nYou: " + txtMessage.getText().trim());
        }
    }

    public void sendImageOnAction(MouseEvent mouseEvent) throws IOException {
    }
}
