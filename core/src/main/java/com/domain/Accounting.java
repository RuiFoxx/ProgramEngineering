package com.domain;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
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
}
