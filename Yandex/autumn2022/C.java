import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            long A = sc.nextLong();
            long B = sc.nextLong();
            long gcd = gcd(A, B).d;
            A /= gcd;
            B /= gcd;
            long max = 1;
            max = getMax(A, max);
            max = getMax(B, max);
            System.out.println(max * gcd);
        }
    }

    private static long getMax(long a, long max) {
        for (long j = 2; j * j <= a; j++) {
            while (a % j == 0) {
                a /= j;
                max = Math.max(max, j);
            }
        }
        if (a > max) {
            max = a;
        }
        return max;
    }

    private static class Triplet {
        long x;
        long y;
        long d;

        public Triplet(long x, long y, long d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
    private static Triplet gcd(long n, long m) {
        if (n == 0){
            return new Triplet(0, 1, m);
        }
        long n1 = n;
        long m1 = m;
        Triplet res = gcd(m % n, n);
        long x = res.y - (m1 / n1) * res.x;
        long y = res.x;
        return new Triplet(x, y, res.d);
    }
}
