import java.math.BigInteger;
import java.util.Scanner;

public class isPrimeBigNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            long next = sc.nextLong();
            BigInteger num = new BigInteger(String.valueOf(next));
            if (num.isProbablePrime(100)){
                System.out.println("YES");
            }else System.out.println("NO");
        }
    }
}
