import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash
{
    public String hash(String pass) throws NoSuchAlgorithmException
    {
        MessageDigest messageDigest = null;
        byte [] digest = new byte[0];

        messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(pass.getBytes());
        digest=messageDigest.digest();

        BigInteger bigInt = new BigInteger(1,digest);
        String md5Hex = bigInt.toString(16);

        while (md5Hex.length()<32)
        {
            md5Hex="0"+md5Hex;
        }
        return md5Hex;
    }
}
