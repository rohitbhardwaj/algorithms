package trees.bst;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code KthSmallestElementBST} class provides methods to find the kth smallest element
 * in a Binary Search Tree (BST) using in-order traversal.
 * java trees.bst.KthSmallestElementBST
 * 
 * <p>It includes both recursive and iterative approaches to perform the in-order traversal.</p>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * // Construct the BST
 * TreeNode root = new TreeNode(3);
 * root.left = new TreeNode(1);
 * root.right = new TreeNode(4);
 * root.left.right = new TreeNode(2);
 *
 * KthSmallestElementBST solver = new KthSmallestElementBST();
 * int k = 1;
 * int kthSmallest = solver.kthSmallestRecursive(root, k); // Output: 1
 * }</pre>
 */
public class KthSmallestElementBST {
    
    /**
     * Finds the kth smallest element in a BST using recursive in-order traversal.
     *
     * @param root The root node of the BST.
     * @param k    The order of the smallest element to find.
     * @return The value of the kth smallest element in the BST.
     */
    public int kthSmallestRecursive(TreeNode root, int k) {
        List<Integer> inorderList = new ArrayList<>();
        inorderTraversal(root, inorderList);
        return inorderList.get(k - 1); // k is 1-indexed
    }

    /**
     * Helper method to perform in-order traversal of the BST recursively.
     *
     * @param node        The current node being traversed.
     * @param inorderList The list accumulating the in-order traversal.
     */
    private void inorderTraversal(TreeNode node, List<Integer> inorderList) {
        if (node == null) {
            return;
        }
        // Traverse the left subtree
        inorderTraversal(node.left, inorderList);
        // Visit the current node
        inorderList.add(node.val);
        // Traverse the right subtree
        inorderTraversal(node.right, inorderList);
    }

    /**
     * Finds the kth smallest element in a BST using iterative in-order traversal with a stack.
     *
     * @param root The root node of the BST.
     * @param k    The order of the smallest element to find.
     * @return The value of the kth smallest element in the BST.
     */
    public int kthSmallestIterative(TreeNode root, int k) {
        java.util.Stack<TreeNode> stack = new java.util.Stack<>();
        TreeNode current = root;
        int count = 0;

        // Iterate until we find the kth smallest element
        while (current != null || !stack.isEmpty()) {
            // Reach the leftmost node of the current node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Current must be null at this point
            current = stack.pop();
            count++;

            // If the count is equal to k, return the current node's value
            if (count == k) {
                return current.val;
            }

            // Move to the right subtree
            current = current.right;
        }

        // If k is larger than the number of nodes in the BST
        throw new IllegalArgumentException("k is larger than the number of nodes in the BST");
    }

    /**
     * The {@code TreeNode} class represents a node in the Binary Search Tree.
     *
     * <p>Each node contains an integer value and references to its left and right child nodes.</p>
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        /**
         * Constructs a new {@code TreeNode} with the specified value.
         *
         * @param val The integer value to be stored in the node.
         */
        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * The {@code main} method serves as an entry point to test the {@code KthSmallestElementBST} class.
     *
     * <p>It demonstrates the creation of a BST, insertion of nodes, and finding the kth smallest element
     * using both recursive and iterative approaches.</p>
     *
     * @param args Command-line arguments (not utilized in this program).
     */
    public static void main(String[] args) {
        /*
                    3
                   / \
                  1   4
                   \
                    2
        */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);

        KthSmallestElementBST solver = new KthSmallestElementBST();
        
        // Example 1
        int k1 = 1;
        int kthSmallest1 = solver.kthSmallestRecursive(root, k1);
        System.out.println("Recursive Approach - Example 1:");
        System.out.println("k = " + k1 + ", kth smallest = " + kthSmallest1); // Output: 1

        int kthSmallest1Iterative = solver.kthSmallestIterative(root, k1);
        System.out.println("Iterative Approach - Example 1:");
        System.out.println("k = " + k1 + ", kth smallest = " + kthSmallest1Iterative); // Output: 1

        // Example 2
        /*
                    5
                   / \
                  3   6
                 / \
                2   4
               /
              1
        */
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(6);
        root2.left.left = new TreeNode(2);
        root2.left.right = new TreeNode(4);
        root2.left.left.left = new TreeNode(1);

        int k2 = 3;
        int kthSmallest2 = solver.kthSmallestRecursive(root2, k2);
        System.out.println("\nRecursive Approach - Example 2:");
        System.out.println("k = " + k2 + ", kth smallest = " + kthSmallest2); // Output: 3

        int kthSmallest2Iterative = solver.kthSmallestIterative(root2, k2);
        System.out.println("Iterative Approach - Example 2:");
        System.out.println("k = " + k2 + ", kth smallest = " + kthSmallest2Iterative); // Output: 3

        // Example 3
        TreeNode root3 = new TreeNode(2);
        root3.left = new TreeNode(1);

        int k3 = 2;
        int kthSmallest3 = solver.kthSmallestRecursive(root3, k3);
        System.out.println("\nRecursive Approach - Example 3:");
        System.out.println("k = " + k3 + ", kth smallest = " + kthSmallest3); // Output: 2

        int kthSmallest3Iterative = solver.kthSmallestIterative(root3, k3);
        System.out.println("Iterative Approach - Example 3:");
        System.out.println("k = " + k3 + ", kth smallest = " + kthSmallest3Iterative); // Output: 2
    }
}
