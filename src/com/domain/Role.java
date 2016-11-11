package com.domain;

public class Role {
    private int id;
    private int user_id;
    private String name;
    private String resource;

    public Role() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return user_id;
    }

    public String getResource() {
        return resource;
    }

    public Role(int id, int user_id, String name, String resource) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.resource = resource;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

}
