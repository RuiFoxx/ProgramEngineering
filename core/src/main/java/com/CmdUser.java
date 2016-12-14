package com;

import lombok.Getter;
import lombok.Setter;

//В этот класс записываем данные из коммандной строки
@Setter
@Getter
public class CmdUser {

    private String login;
    private String password;
    private String resource;
    private String role;
    private String date_start;
    private String date_end;
    private String volume;

    public CmdUser() {
        this.login = null;
        this.password = null;
        this.resource = null;
        this.role = null;
        this.date_start = null;
        this.date_end = null;
        this.volume = null;
    }

    public boolean isAuthentication () {
        return login != null && password != null;
    }

    public boolean isAuthorization () {
        return isAuthentication() && resource != null && role != null;
    }

    public boolean isAccounting() {
        return isAuthorization() && date_start != null && date_end != null && volume != null;
    }
}
