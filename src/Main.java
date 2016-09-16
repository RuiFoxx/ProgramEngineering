import java.security.NoSuchAlgorithmException;

public class Main
{
    public static void main(String ...args) throws NoSuchAlgorithmException
    {

       User U1=new User(1,"John Doe", "jdoe", "sup3rpaZZ","ds65rt");

       String HP = Hash.hash(Hash.hash(U1.getPassword())+U1.getSalt());

        System.out.println(HP);

    }
}
