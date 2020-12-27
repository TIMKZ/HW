package chat.auth;

import chat.User;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

public class BaseAuthService implements AuthService {
    private static final List<User> clients = FXCollections.observableArrayList(
            new User("user1","1111","Николай"),
            new User("user2","2222","Сергей"),
            new User("user3","3333","Дмитрий"));

    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");

    }

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        for (User client : clients ){
            if (client.getLogin().equals(login) && client.getPassword().equals(password)){
                return client.getUsername();
            }
        }
        return null;
    }

    @Override
    public void close() {
        System.out.println("Сервис аутентификации завершен");
    }
}
