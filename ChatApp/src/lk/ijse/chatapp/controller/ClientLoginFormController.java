package lk.ijse.chatapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientLoginFormController {

    public TextField txtUserName;
    public AnchorPane context;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        if(txtUserName.getText().equalsIgnoreCase("Client1")) {
            loadUi("ClientForm1");
        }if(txtUserName.getText().equalsIgnoreCase("Client2")) {
            loadUi("ClientForm2");
        }if(txtUserName.getText().equalsIgnoreCase("Client3")) {
            loadUi("ClientForm3");
        }

    }

    public void loadUi(String location) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/chatapp/view/"+location+".fxml"))));
        stage.show();
    }
}
