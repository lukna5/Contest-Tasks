import java.util.ArrayList;
import java.util.Scanner;

public class A1 {
    // Have a nice day)
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            String[] next = sc.nextLine().split(",");
            String surname = next[0];
            String name = next[1];
            String middleName = next[2];
            int day = Integer.parseInt(next[3]);
            int mouth = Integer.parseInt(next[4]);
            int year = Integer.parseInt(next[5]);
            int first = uniqueSymbols(surname + name + middleName);
            int second = 64 * (sumOfNums(day) + sumOfNums(mouth));
            int third = numOfFirstLetter(surname) * 256;
            String hex = Integer.toHexString(first + second + third);
            System.out.print(substring(hex).toUpperCase() + " ");
        }
    }
    private static int uniqueSymbols(String x){
        ArrayList<Character> was = new ArrayList<>();
        int res = 0;
        for (int i = 0; i < x.length(); i++) {
            char next = x.charAt(i);
            if (!was.contains(next)){
                res++;
                was.add(next);
            }
        }
        return res;
    }

    private static int sumOfNums(int num){
        int res = 0;
        while (num > 0){
            res += num % 10;
            num /= 10;
        }
        return res;
    }
    private static int numOfFirstLetter(String x){
        char first = x.charAt(0);
        if (Character.isLowerCase(first)){
            return first - 96;
        }
        else {
            return first - 64;
        }
    }
    private static String substring(String x){
        if (x.length() <= 3){
            //toUpper(x);
            return x;
        }
        //x = toUpper(x);
        return x.substring(x.length() - 3);
    }

    private static String toUpper(String x) {
        for (int i = 0; i < x.length(); i++) {
            if (Character.isLetter(x.charAt(i)) && Character.isLowerCase(x.charAt(i))){
                String x1 = Character.toString(x.charAt(i));
                String x2 = Character.toString(Character.toUpperCase(x.charAt(i)));
                x = x.replaceAll(x1, x2);
            }
        }
        return x;
    }
}
