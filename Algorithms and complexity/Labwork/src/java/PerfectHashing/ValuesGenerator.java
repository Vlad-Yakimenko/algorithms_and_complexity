package PerfectHashing;

import java.util.Random;

public class ValuesGenerator {

    private static Random random = new Random();

    public static int generateP(int maxHash) {
        int p = random.nextInt();

        while (!isPrime(p) && p < maxHash) {
            p = random.nextInt();
        }

        return p;
    }

    public static int generateA(int p) {
        return random.nextInt(p - 1) + 1;
    }

    public static int generateB(int p) {
        return random.nextInt(p - 1);
    }

    public static int generateUniversalHash(int key, int a, int b, int p, int m) {
        return Math.abs(((a * key + b) % p) % m);
    }

    private static boolean isPrime(int inputNum){
        if (inputNum <= 3 || inputNum % 2 == 0) {
            return inputNum == 2 || inputNum == 3;
        }

        int divisor = 3;
        while ((divisor <= Math.sqrt(inputNum)) && (inputNum % divisor != 0)) {
            divisor += 2;
        }

        return inputNum % divisor != 0;
    }
}