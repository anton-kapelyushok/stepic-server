package main.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User implements Serializable{
    public String name;
    public String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
