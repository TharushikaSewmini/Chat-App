package lk.ijse.chatapp.Client.controller;

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
        if(txtUserName.getText().equalsIgnoreCase("Sameera")) {
            loadUi("ClientForm1");
        }if(txtUserName.getText().equalsIgnoreCase("Nimal")) {
            loadUi("ClientForm2");
        }if(txtUserName.getText().equalsIgnoreCase("Jayani")) {
            loadUi("ClientForm3");
        }

    }

    public void loadUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/chatapp/Client/view/" +location+".fxml"))));
        stage.centerOnScreen();
    }
}
