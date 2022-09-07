import java.util.HashMap;
import java.util.Scanner;

public class C1 {

    public static class Node{
        int x;
        Node left;

        public Node(int x, Node left, Node right, Node batya) {
            this.x = x;
            this.left = left;
            this.right = right;
            this.batya = batya;
        }

        Node right;
        Node batya;
        public Node(int x, Node left, Node right) {
            this.x = x;
            this.left = left;
            this.right = right;
        }
    }
    private static HashMap<Integer, Node> nodeHashMap;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        nodeHashMap = new HashMap<>();
        int n = sc.nextInt();
        int q = sc.nextInt();
        Node root = startBuild(n);
        for (int i = 0; i < q; i++) {
            Node next = nodeHashMap.get(sc.nextInt());
            swap(next);
            while (root.batya != null){
                root = root.batya;
            }
        }
        lvr(root);
    }

    private static Node findNode(Node node, int k){
        if (node == null) return null;
        if (node.x == k) return node;
        Node res = findNode(node.left, k);
        if (res == null) {
            res = findNode(node.right, k);
        }
        return res;
    }
    private static void swap(Node node){
        if (node == null) return;
        if (node.batya == null) return;
        Node batya = node.batya;
        Node ded = batya.batya;
        if (node.batya.left == node){
            batya.left = node.left;
            if (node.left != null) {
                node.left.batya = batya;
            }
            batya.batya = node;
            node.batya = ded;
            node.left = batya;
        }
        else if (node.batya.right == node){
            batya.right = node.right;
            if (node.right != null) {
                node.right.batya = batya;
            }
            batya.batya = node;
            node.batya = ded;
            node.right = batya;
        }
        if (ded != null){
            if (ded.left == batya){
                ded.left = node;
            }
            else {
                ded.right = node;
            }
        }
    }
    public static Node startBuild(int n){
        if (n < 1) return null;
        Node root = new Node(1, null, null, null);
        nodeHashMap.put(1, root);
        root.left = build(2, root, n);
        root.right = build(3, root, n);
        return root;
    }
    public static Node build(int x, Node batya, int n){
        if (x > n) return null;
        Node node = new Node(x, null, null, batya);
        nodeHashMap.put(x, node);
        node.left = build(x * 2, node, n);
        node.right = build(x * 2 + 1, node, n);
        return node;
    }
    private static void printTree(Node node){
        System.out.print(node.x + " | ");
        if (node.left != null) printTree(node.left);
        if (node.right != null) printTree(node.right);
    }

    private static void lvr(Node node){
        if (node.left != null) lvr(node.left);
        System.out.print(node.x + " ");
        if (node.right != null) lvr(node.right);
    }


}
