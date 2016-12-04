package com.domain;

import com.Hash;

import java.security.NoSuchAlgorithmException;

public class User {
    private int id;
    private String name;
    private String login;
    private String password;
    private String salt;

    public User(User u) {
        this.id = u.id;
        this.name = u.name;
        this.login = u.login;
        this.salt = u.salt;
        this.password = u.password;
    }

    public User() {
    }

    public String getSalt() {
        return salt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() { return login; }

    public User(int id, String name, String login, String password) throws NoSuchAlgorithmException {
        this.id = id;
        this.name = name;
        this.login = login;
        this.salt = Hash.salt();
        this.password = Hash.hash(Hash.hash(password) + salt);
    }
}
