import java.math.BigInteger;
import java.util.Scanner;

public class A {
    static int[] pow2;
    public static void main(String[] args) {
        pow2 = new int[27];
        pow2[0] = 1;
        for (int i = 1; i < 27; i++) {
            pow2[i] = pow2[i - 1] * 2;
        }
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int W = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int next = sc.nextInt();
            int log = getLog2(Math.abs(next - W));
            builder.append((char) (log + 97));
            W = next;
        }
        System.out.println(builder);
    }
    private static int getLog2(int x){
        for (int i = 0; i < 27; i++) {
            if (x == pow2[i]) return i;
        }
        return 0;
    }
}
