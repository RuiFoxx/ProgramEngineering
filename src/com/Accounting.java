package com;
import java.util.Date;

public class Accounting {
    private int volume;
    private int role_id;
    private Date date_start;
    private Date date_end;

    public void setRoleId(Role role) { this.role_id = role_id; }

    public Accounting(int volume, Role role, Date date_start, Date date_end) {
        this.volume = volume;
        this.role_id = role_id;
        this.date_start = date_start;
        this.date_end = date_end;
    }
}
