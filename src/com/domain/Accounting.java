package com.domain;
import java.util.Date;

public class Accounting {
    private int id;
    private int volume;
    private int role_id;
    private Date date_start;
    private Date date_end;

    public Accounting(int id, int volume, int role_id, Date date_start, Date date_end) {
        this.id = id;
        this.volume = volume;
        this.role_id = role_id;
        this.date_start = date_start;
        this.date_end = date_end;
    }

    public Accounting() {

    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public void setDate_start(Date date_start) {
        this.date_start = date_start;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public int getVolume() {
        return volume;
    }

    public int getRoleId() {
        return role_id;
    }

    public Date getDateStart() {
        return date_start;
    }

    public Date getDateEnd() {
        return date_end;
    }
}
