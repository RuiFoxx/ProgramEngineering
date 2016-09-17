public class User {
    private String name, login, pass, salt;
    private int id;

    public User(String name, String login, String pass, String salt, int id) {
        this.name = name;
        this.login = login;
        this.pass = pass;
        this.salt = salt;
        this.id = id;
    }
}
