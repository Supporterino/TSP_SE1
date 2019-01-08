package utilities;

import java.math.BigInteger;

public class Combinations {
    public static void print(String string) {
        char[] characters = string.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < characters.length; i++) {
            stringBuilder.append(characters[i]);
            if (i > 5 && i % 75 == 0)
                stringBuilder.append("\n");
        }

        System.out.println(stringBuilder.toString());
    }

    public static String factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= n; i++)
            result = result.multiply(BigInteger.valueOf(i));
        return result.toString();
    }

    public static void main(String... args) {
        int n = 280;
        print(factorial(n));
    }
}