package com.domain;

import com.Hash;
import lombok.Getter;
import lombok.Setter;

import java.security.NoSuchAlgorithmException;

@Setter
@Getter
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

    public User(int id, String name, String login, String password) throws NoSuchAlgorithmException {
        this.id = id;
        this.name = name;
        this.login = login;
        this.salt = Hash.salt();
        this.password = Hash.hash(Hash.hash(password) + salt);
    }
}
