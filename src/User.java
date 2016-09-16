

public class User
{
    private int id;
    private String name;
    private String login;
    private String password;
    private String salt;

    public String getSalt()
    {
        return salt;
    }

    public String getPassword()
    {
        return password;
    }

    public User(int id, String name, String login, String password, String salt)
    {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.salt = salt;
    }

}
