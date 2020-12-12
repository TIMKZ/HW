package lesson2_6.client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;


public class ViewController {
    //    @FXML
//    private Button sendButton;

    private Network network;

    public void setNetwork(Network network) {
        this.network = network;
    }

    @FXML
    private TextField inputField; // (import javafx.scene.control.)

    @FXML
    private ListView<String> listView; // импортировать именно из javaFx import javafx.scene.control.
    private final ObservableList<String> USERS_TEST_DATA = FXCollections.observableArrayList("Николай", "Сергей", "Дмитрий");
    //USERS_TEST_DATA - создание коллекции для хранение сообщений

    @FXML
    private TextArea chatHistory;
    private final ObservableList<String> USERS_HISTORY = FXCollections.observableArrayList(" ");

    @FXML
    public void initialize(){
        listView.setItems(USERS_TEST_DATA);//передается в listView коллекция USERS_TEST_DATA
        chatHistory.appendText(String.valueOf(USERS_HISTORY));
        chatHistory.clear();
    }
    @FXML
    public void addWordToArea(){
        String message = inputField.getText();//через inputField принимает значение текста в переменную message.

        if (message.trim().isEmpty()){  // .trim().isEmpty() - запретит отправить и пустое и состоящие только из пробелов
            System.out.println("пустое сообщение");
        }
//        else {
//
//            //appendMessage(message);
//
//
//        }

        inputField.clear();//очисть inputField после отправки сообщения
        try {
            network.getOut().writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
            EchoClient.showErrorMessage("Ошибка подключения", "ошибка при отправке сообщения", e.getMessage());
//            System.out.println("ошибка при отправке сообщения");
        }
    }

    public void appendMessage(String message) {
        chatHistory.appendText(message);
        chatHistory.appendText(System.lineSeparator());
    }


}