public class BSTTest {
    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();

        tree.put(5, "five");
        tree.put(2, "two");
        tree.put(7, "seven");
        tree.put(3, "three");

        System.out.println("Size: " + tree.size());           // 4
        System.out.println("Get(7): " + tree.get(7));         // "seven"

        System.out.println("In-order traversal:");
        for (var node : tree) {
            System.out.println("key is " + node.getKey()
                    + " and value is " + node.getValue());
        }

        tree.delete(2);
        System.out.println("After delete(2), size: " + tree.size());
        for (var node : tree) System.out.println(node);
    }
}
