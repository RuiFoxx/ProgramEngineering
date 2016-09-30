package com;
import java.util.Date;

public class Accounting
{
    private int  volume;
    private Role role;
    private Date date_start, date_end;

    public void setRole(Role role)
    {
        this.role = role;
    }

    public Accounting(int volume, Role role, Date date_start, Date date_end)
    {
        this.volume = volume;
        this.role = role;
        this.date_start = date_start;
        this.date_end = date_end;
    }
}
