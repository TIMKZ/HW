package client.controller;

import client.NetworkClient;
import client.models.Network;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import java.io.IOException;

import static client.NetworkClient.showErrorMessage;

public class AuthController {

    @FXML public TextField loginField;
    @FXML public PasswordField passwordField;

    private Network network;
    private NetworkClient networkClient;

    @FXML
    public void checkAuth(){ //кнопка "войти"
        String login = loginField.getText();
        String password = passwordField.getText();

        if (login.isEmpty() || password.isEmpty()){
            NetworkClient.showErrorMessage("ошибка авторизации", "ошибка ввода","поля не должны быть пустыми");
            return;

        }
        String authErrorMessage = network.sendAuthCommand(login, password);
        if (authErrorMessage != null){
            NetworkClient.showErrorMessage("ошибка авторизации", "ошибка не правильный логин или пароль",authErrorMessage);
        }
        else {

                networkClient.openMainChatWindow();

        }

    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public void setNetworkClient(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }
}
