import java.util.Scanner;

public class D{
    private static class Start{
        long start;
        long cost;

        public Start(long start, long cost) {
            this.start = start;
            this.cost = cost;
        }
    }
    private static class End{
        long start;
        long end;

        public End(long start, long end) {
            this.start = start;
            this.end = end;
        }
        private long getDif(){
            return this.end - this.start;
        }
    }
    static Start[] starts;
    static End[] ends;
    public static class Node{
        long start;
        long cost;
        long sumCost;
        Node left;

        public Node(long start, long cost, Node left, Node right, Node batya) {
            this.start = start;
            this.cost = cost;
            this.left = left;
            this.right = right;
            this.batya = batya;
            sumCost = cost;
        }

        Node right;
        Node batya;
        public Node(long start, Node left, Node right) {
            this.start = start;
            this.left = left;
            this.right = right;
        }
    }
    public static class Node1{
        long end;
        long start;
        long sumCost;
        Node1 left;
        long time;
        public Node1(long start, long end, Node1 left, Node1 right, Node1 batya) {
            this.start = start;
            this.end = end;
            this.left = left;
            this.right = right;
            this.batya = batya;
            time = end - start;
            sumCost = time;
        }

        Node1 right;
        Node1 batya;
        public Node1(long start, Node1 left, Node1 right) {
            this.start = start;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long start = sc.nextInt();
        long end = sc.nextInt();
        long cost = sc.nextInt();
        Node root = new Node(start, cost, null, null, null);
        Node1 root1 = new Node1(start, end, null, null, null);

        for (int i = 1; i < n; i++) {
            start = sc.nextInt();
            end = sc.nextInt();
            cost = sc.nextInt();
            insert(root, new Start(start, cost), null);
            insert(root1, new End(start, end), null);
        }
        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            start = sc.nextInt();
            end = sc.nextInt();
            int type = sc.nextInt();
            if (type == 1) {
                System.out.print(getSum(root, (int) start, (int) end) + " ");
            } else if (type == 2){
                System.out.print(getSum1(root1, (int) start, (int) end) + " ");
            }
        }
    }
    private static Node1 insert(Node1 node, End k, Node1 batya){
        if (node == null) return new Node1(k.start, k.end, null, null, batya);
        if (node.end == k.end){
            node.time += (k.end - k.start);
            node.sumCost += k.getDif();
            return node;
        }
        if (node.end > k.end){
            node.left = insert(node.left, k, node);
        }
        if (node.end < k.end){
            node.right = insert(node.right, k, node);
        }
        node.sumCost = getCost(node.left) + getCost(node.right) + node.time;
        return node;
    }
    private static long getSum1(Node1 node, int l, int r){
        if (node == null) return 0;
        if (node.end >= l && node.end <= r){
            return node.time + getSum1(node.left, l , r) + getSum1(node.right, l, r);
        }
        return getSum1(node.left, l, r) + getSum1(node.right, l, r);
    }
    private static Node insert(Node node, Start k, Node batya){
        if (node == null) return new Node(k.start, k.cost, null, null, batya);
        if (node.start == k.start){
            node.cost += k.cost;
            node.sumCost += k.cost;
            return node;
        }
        if (node.start > k.start){
            node.left = insert(node.left, k, node);
        }
        if (node.start < k.start){
            node.right = insert(node.right, k, node);
        }
        node.sumCost = getCost(node.left) + getCost(node.right) + node.cost;
        return node;
    }
    private static long getCost(Node node){
        if (node == null) return 0;
        return node.sumCost;
    }
    private static long getCost(Node1 node){
        if (node == null) return 0;
        return node.time;
    }
    private static long getSum(Node node, int l, int r){
        if (node == null) return 0;
        if (node.start >= l && node.start <= r){
            return node.cost + getSum(node.left, l , r) + getSum(node.right, l, r);
        }
        return getSum(node.left, l, r) + getSum(node.right, l, r);
    }


}