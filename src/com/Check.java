package com;

public class Check {
    private boolean authentication = false;
    private boolean authorization = false;
    private boolean accounting = false;

    public Check() {
    }

    public boolean isAuthentication() {
        return authentication;
    }

    public void setAuthentication() {
        this.authentication = true;
    }

    public boolean isAuthorization() {
        return authorization;
    }

    public void setAuthorization() {
        this.authentication = true;
        this.authorization = true;
    }

    public boolean isAccounting() {
        return accounting;
    }

    public void setAccounting() {
        this.authentication = true;
        this.authorization = true;
        this.accounting = true;
    }
}
