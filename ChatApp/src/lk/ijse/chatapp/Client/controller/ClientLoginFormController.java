package lk.ijse.chatapp.Client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.chatapp.User;

import java.io.IOException;
import java.util.ArrayList;

public class ClientLoginFormController {

    public TextField txtUserName;
    public AnchorPane context;
    public static String userName;

    public static ArrayList<User> users = new ArrayList<>();

    public void loginOnAction(ActionEvent actionEvent) throws IOException {

        if(txtUserName.getText().equalsIgnoreCase("Sameera")) {
            userName=txtUserName.getText();
            loadUi("ClientForm1", txtUserName.getText());
        }if(txtUserName.getText().equalsIgnoreCase("Nimal")) {
            userName=txtUserName.getText();
            loadUi("ClientForm2",txtUserName.getText());
        }if(txtUserName.getText().equalsIgnoreCase("Naduni")) {
            userName=txtUserName.getText();
            loadUi("ClientForm3",txtUserName.getText());
        }

        for (User user : users) {
            System.out.println(user.userName);
        }
        users.add(new User(userName));

    }

    public void loadUi(String location, String user) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/chatapp/Client/view/" +location+".fxml"))));
        stage.setTitle("Chat Room - " +user.toUpperCase());
        stage.centerOnScreen();
    }
}
