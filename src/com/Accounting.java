package com;

public class Accounting {
    private int volume;
    private Role role;
    private String date_start, date_end;

    public Accounting(int volume, Role role, String date_start, String date_end) {
        this.volume = volume;
        this.role = role;
        this.date_start = date_start;
        this.date_end = date_end;
    }
}
