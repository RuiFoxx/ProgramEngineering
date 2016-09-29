package com;

public class Role
{
    private int id;
    private User user;
    private String name, resource;

    public String getName()
    {
        return name;
    }

    public User getUser()
    {
        return user;
    }

    public String getResource()
    {
        return resource;
    }

    public Role(int id, User user, String name, String resource)
    {
        this.id = id;
        this.user = user;
        this.name = name;
        this.resource = resource;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setResource(String resource)
    {
        this.resource = resource;
    }
}
