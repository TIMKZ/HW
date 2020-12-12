package lesson2_6.client;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EchoClient extends Application {

    public final String PATH_TO_FXL = "view.fxml";


 //   public static final ObservableList<String> USERS_TEST_DATA = FXCollections.observableArrayList("Борис Николаевич", "Владимир Владимирович", "Дмитрий Анатольевич");
  //  public static final ArrayList<String> USERS_TEST_DATA = new ArrayList<String>(Arrays.asList("Николай", "Сергей", "Дмитрий"));



    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EchoClient.class.getResource(PATH_TO_FXL));

        Parent root = loader.load();


//        @Override
//        public void start(Stage primaryStage) throws Exception {
//            Parent root = FXMLLoader.load(getClass().getResource(PATH_TO_FXL));


//        Socket socket = new Socket("localhost",8189);
//
//        DataInputStream in = new DataInputStream(socket.getInputStream());
//        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
// перенесли в отдельный фаил Network

        Network network = new Network();
            if(!network.connect()){
//                System.out.println("Ошибка подключения к серверу");
                showErrorMessage("Проблемы с соединением", "","Ошибка подключения к серверу");
            }
            ViewController viewController = loader.getController();
            viewController.setNetwork(network);
            network.waitMessage(viewController);// ожидает входящего сообщения.

        primaryStage.setTitle("Чат клиент");
        primaryStage.setScene(new Scene(root));
        primaryStage.show(); // показать сцену

        primaryStage.setOnCloseRequest(windowEvent -> network.close()); // при закрытие сцены, закрывает socket.close()

    }

    public static void showErrorMessage(String title, String message, String errorMessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(errorMessage);
        alert.showAndWait();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
