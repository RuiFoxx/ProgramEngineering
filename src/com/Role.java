package com;

public class Role {
    private int id;
    private User user;
    private String name, resource;

    public Role(int id, User user, String name, String resource) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.resource = resource;
    }
}
