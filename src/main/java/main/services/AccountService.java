package main.services;

import main.models.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AccountService {
    Map<String, User> map = new HashMap<>();

    public User signUp(String login, String pass) {
        User user = map.get(login);
        if (user == null) {
            user = new User(login, pass);
            map.put(login, user);
            return user;
        } else {
            return null;
        }

    }

    public User signIn(String login, String pass) {
        User user = map.get(login);
        if (user == null || !user.getPassword().equals(pass)) {
            return null;
        }
        return user;
    }
}
