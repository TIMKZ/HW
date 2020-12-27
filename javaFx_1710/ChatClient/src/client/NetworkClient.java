package client;

import client.controller.AuthController;
import client.controller.ChatController;
import client.models.Network;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class NetworkClient extends Application {

    public final String VIEW_CHAT_FXL = "views/chat-view.fxml";
    public final String VIEW_AUTH_FXL = "views/auth-views.fxml";
    private Stage primaryStage;
    private Stage authStage;
    private Network network;
    private ChatController chatController;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;

        network = new Network();
        if (!network.connect()) {
            showErrorMessage("Проблемы с соединением", "", "Ошибка подключения к серверу");
            return;
        }

        openAuthWindow();
        createMainChatWindow();
    }

    private void openAuthWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(NetworkClient.class.getResource(VIEW_AUTH_FXL));
        Parent root = loader.load();
        authStage = new Stage();

        authStage.setTitle("Авторизация");
        authStage.initModality(Modality.WINDOW_MODAL);
        authStage.initOwner(primaryStage);
        Scene scene = new Scene(root);
        authStage.setScene(scene);
        authStage.show();

        AuthController authController = loader.getController();
        authController.setNetwork(network);
        authController.setNetworkClient(this);


    }

    public void createMainChatWindow() throws java.io.IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(NetworkClient.class.getResource(VIEW_CHAT_FXL));

        Parent root = loader.load();

        primaryStage.setTitle("Messenger");
        primaryStage.setScene(new Scene(root, 600, 400));

        chatController = loader.getController();
        chatController.setNetwork(network);

        primaryStage.setOnCloseRequest(windowEvent -> network.close());
    }

    public static void showErrorMessage(String title, String message, String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void openMainChatWindow() {
        authStage.close();
        primaryStage.show();

        primaryStage.setTitle(network.getUsername());
        primaryStage.setAlwaysOnTop(true);
        chatController.setLabel(network.getUsername());
        network.waitMessage(chatController);
    }

}
