package trees.bst ; 
import java.util.ArrayList;

/**
 * The {@code Main} class serves as the entry point for testing the {@code MyBST} class.
 *
 * <p>It demonstrates the creation of a Binary Search Tree, insertion of nodes, and various
 * traversal methods.</p>
 *
 * <p>Example Output:</p>
 * <pre>{@code
 * bfs: [9, 4, 20, 1, 6, 15, 170]
 * look for 20: true
 * bfs recursive: [9, 4, 20, 1, 6, 15, 170]
 * dfs inOrder: [1, 4, 6, 9, 15, 20, 170]
 * dfs preOrder: [9, 4, 1, 6, 20, 15, 170]
 * dfs postOrder: [1, 6, 4, 15, 170, 20, 9]
 * look for 20 after removing 20: false
 * }</pre>
 *
 * @author
 */
public class Main {
    public static void main(String[] args) {
        // Initialize the Binary Search Tree.
        MyBST bst = new MyBST();

        // Insert nodes into the BST.
        bst.insert(9);
        bst.insert(4);
        bst.insert(6);
        bst.insert(20);
        bst.insert(170);
        bst.insert(15);
        bst.insert(1);

        // Perform and display Breadth First Search (BFS).
        System.out.println("bfs: " + bst.breadthFirstSearch());

        // Lookup for a value in the BST.
        System.out.println("look for 20: " + bst.lookup(20));

        // Perform and display Recursive Breadth First Search.
        ArrayList<Node> queue = new ArrayList<>();
        queue.add(bst.root);
        System.out.println("bfs recursive: " + bst.breadthFirstSearchRecursive(queue, new ArrayList<>()));

        // Perform and display Depth First Search (DFS) Traversals.
        System.out.println("dfs inOrder: " + bst.DFSInOrder());
        System.out.println("dfs preOrder: " + bst.DFSPreOrder());
        System.out.println("dfs postOrder: " + bst.DFSPostOrder());

        // Remove a node and perform lookup again.
        bst.remove(20);
        System.out.println("look for 20 after removing 20: " + bst.lookup(20));
    }
}

/**
 * The {@code Node} class represents a node in the Binary Search Tree.
 *
 * <p>Each node contains an integer value and references to its left and right child nodes.</p>
 */
class Node {
    int value;
    Node right;
    Node left;

    /**
     * Constructs a new {@code Node} with the specified value.
     *
     * @param value The integer value to be stored in the node.
     */
    Node(int value) {
        this.value = value;
        this.right = null;
        this.left = null;
    }
}

/**
 * The {@code HelperFunctions} class provides utility methods for performing
 * in-order, pre-order, and post-order traversals of the BST.
 */
class HelperFunctions {
    /**
     * Traverses the BST in in-order and appends node values to the provided array.
     *
     * @param node  The current node being traversed.
     * @param array The {@code ArrayList<Integer>} to store traversal results.
     * @return The {@code ArrayList<Integer>} containing in-order traversal of the BST.
     */
    public ArrayList<Integer> traverseInOrder(Node node, ArrayList<Integer> array) {
        if (node.left != null) {
            traverseInOrder(node.left, array);
        }
        array.add(node.value);
        if (node.right != null) {
            traverseInOrder(node.right, array);
        }
        return array;
    }

    /**
     * Traverses the BST in pre-order and appends node values to the provided array.
     *
     * @param node  The current node being traversed.
     * @param array The {@code ArrayList<Integer>} to store traversal results.
     * @return The {@code ArrayList<Integer>} containing pre-order traversal of the BST.
     */
    public ArrayList<Integer> traversePreOrder(Node node, ArrayList<Integer> array) {
        array.add(node.value);
        if (node.left != null) {
            traversePreOrder(node.left, array);
        }
        if (node.right != null) {
            traversePreOrder(node.right, array);
        }
        return array;
    }

    /**
     * Traverses the BST in post-order and appends node values to the provided array.
     *
     * @param node  The current node being traversed.
     * @param array The {@code ArrayList<Integer>} to store traversal results.
     * @return The {@code ArrayList<Integer>} containing post-order traversal of the BST.
     */
    public ArrayList<Integer> traversePostOrder(Node node, ArrayList<Integer> array) {
        if (node.left != null) {
            traversePostOrder(node.left, array);
        }
        if (node.right != null) {
            traversePostOrder(node.right, array);
        }
        array.add(node.value);
        return array;
    }
}

