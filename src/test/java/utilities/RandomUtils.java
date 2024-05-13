package utilities;
import java.util.Random;
public class RandomUtils {
    public static String generateRandomString(int minLength, int maxLength){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        Random rnd = new Random();
        int length = rnd.nextInt(maxLength - minLength + 1) + minLength; // Generate random length between minLength and maxLength
        for (int i = 0; i < length; i++) {
            randomString.append(characters.charAt(rnd.nextInt(characters.length())));
        }
        return randomString.toString();
    }
}
