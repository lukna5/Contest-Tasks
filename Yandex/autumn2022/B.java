import java.util.*;
import java.util.stream.Collectors;

public class B {
    private static class Order{
        int day;
        int hour;
        int minute;
        int id;
        char status;

        public Order(int day, int hour, int minute, int id, String status) {
            this.day = day;
            this.hour = hour;
            this.minute = minute;
            this.id = id;
            this.status = status.charAt(0);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        Order[] orders = new Order[n];
        HashMap<Integer, Integer> res = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] line = sc.nextLine().split(" ");
            orders[i] = new Order(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]),
                    Integer.parseInt(line[3]), line[4]);
            res.put(Integer.parseInt(line[3]), 0);
        }
        Arrays.sort(orders, (o1, o2) -> {
            if (o1.id > o2.id) {
                return 1;
            } else if (o1.id < o2.id) {
                return -1;
            } else {
                if (o1.day > o2.day) {
                    return 1;
                } else if (o1.day < o2.day) {
                    return -1;
                } else {
                    if (o1.hour > o2.hour) {
                        return 1;
                    } else if (o1.hour < o2.hour) {
                        return -1;
                    } else {
                        if (o1.minute > o2.minute) {
                            return 1;
                        } else if (o1.minute < o2.minute) {
                            return -1;
                        } else {
                            return Character.compare(o1.status, o2.status);
                        }
                    }
                }
            }
        });
        for (int i = 0; i < orders.length; i++) {
            Order next = orders[i];
            if (next.status == 'A'){
                int start = (next.day * 24 * 60) + (next.hour * 60) + next.minute;
                i++;
                next = orders[i];
                if (next.status == 'B'){
                    i++;
                    next = orders[i];
                    if (next.status == 'C' || next.status == 'S'){
                        int end = (next.day * 24 * 60) + (next.hour * 60) + next.minute;
                        res.put(next.id, res.get(next.id) + (end - start));
                    }
                } else if (next.status == 'C') {
                    int end = (next.day * 24 * 60) + (next.hour * 60) + next.minute;
                    res.put(next.id, res.get(next.id) + (end - start));
                }
            }
        }
        int[] keySet = new int[res.size()];
        Set<Integer> set = res.keySet().stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        for (int next: set) {
            System.out.print(res.get(next) + " ");
        }
    }
}
