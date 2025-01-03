package trees;

import java.util.ArrayList;

/**
 * The {@code BinarySearchTree} class provides an implementation of a Binary Search Tree (BST)
 * with functionalities to insert, lookup, remove nodes, and perform various tree traversals.
 *
 * <p>This class includes methods for breadth-first search (both iterative and recursive)
 * and depth-first search (in-order, pre-order, post-order).</p>
 *
 * <p>Additionally, it provides a method to find the Lowest Common Ancestor (LCA) of two nodes
 * within the BST.</p>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * BinarySearchTree bst = new BinarySearchTree();
 * bst.insert(6);
 * bst.insert(2);
 * bst.insert(8);
 * bst.insert(0);
 * bst.insert(4);
 * bst.insert(7);
 * bst.insert(9);
 * bst.insert(3);
 * bst.insert(5);
 *
 * Node p = bst.lookupNode(2);
 * Node q = bst.lookupNode(8);
 * Node lca = bst.findLCA(bst.root, p, q);
 * System.out.println("LCA of " + p.value + " and " + q.value + " is: " + lca.value); // Output: 6
 * }</pre>
 *
 * @author
 */
public class BinarySearchTree {
    Node root = null;

    ///////////////////////////////////////////////////////
    //////////          Insert Function       /////////////
    ///////////////////////////////////////////////////////
    /**
     * Inserts a new node with the specified value into the BST.
     *
     * @param value The integer value to be inserted into the BST.
     */
    public void insert(int value) {
        Node newNode = new Node(value);
        if (this.root == null) {
            this.root = newNode;
        } else {
            Node current = this.root;
            while (true) {
                // If the current node's value is less than the value to insert, go right.
                if (current.value < value) {
                    if (current.right != null) {
                        current = current.right;
                    } else {
                        current.right = newNode;
                        break;
                    }
                }
                // If the current node's value is greater than or equal to the value to insert, go left.
                else {
                    if (current.left != null) {
                        current = current.left;
                    } else {
                        current.left = newNode;
                        break;
                    }
                }
            }
        }
    }

    ///////////////////////////////////////////////////////
    //////////          Lookup Function       /////////////
    ///////////////////////////////////////////////////////
    /**
     * Looks up whether a given value exists in the BST.
     *
     * @param value The integer value to search for in the BST.
     * @return {@code true} if the value exists in the BST; {@code false} otherwise.
     */
    public boolean lookup(int value) {
        Node current = this.root;
        while (current != null) {
            if (current.value > value) {
                current = current.left;
            } else if (current.value < value) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the node with the specified value in the BST.
     *
     * @param value The integer value of the node to retrieve.
     * @return The {@code Node} with the specified value, or {@code null} if not found.
     */
    public Node lookupNode(int value) {
        Node current = this.root;
        while (current != null) {
            if (current.value > value) {
                current = current.left;
            } else if (current.value < value) {
                current = current.right;
            } else {
                return current;
            }
        }
        return null;
    }

    ///////////////////////////////////////////////////////
    //////////          Remove Function       /////////////
    ///////////////////////////////////////////////////////
    /**
     * Removes a node with the specified value from the BST.
     *
     * @param value The integer value of the node to be removed from the BST.
     */
    public void remove(int value) {
        if (this.root == null) {
            return;
        }
        Node current = this.root;
        Node parentNode = null;
        // Traverse the tree to find the node to remove.
        while (current != null) {
            if (value < current.value) {
                parentNode = current;
                current = current.left;
            } else if (value > current.value) {
                parentNode = current;
                current = current.right;
            }
            // Node with the value found.
            else {
                // Case 1: Node has no right child.
                if (current.right == null) {
                    if (parentNode == null) {
                        this.root = current.left;
                    } else {
                        if (current.value < parentNode.value) {
                            parentNode.left = current.left;
                        } else if (current.value > parentNode.value) {
                            parentNode.right = current.left;
                        }
                    }
                }
                // Case 2: Node's right child has no left child.
                else if (current.right.left == null) {
                    if (parentNode == null) {
                        this.root = current.right;
                    } else {
                        if (current.value < parentNode.value) {
                            parentNode.left = current.right;
                        } else if (current.value > parentNode.value) {
                            parentNode.right = current.right;
                        }
                    }
                }
                // Case 3: Node's right child has a left child.
                else {
                    if (parentNode == null) {
                        // Save references to left and right subtrees.
                        Node leftNode = this.root.left;
                        Node rightNode = this.root.right;
                        // The new root becomes the leftmost node of the right subtree.
                        this.root = current.right.left;
                        rightNode.left = rightNode.left.right;
                        // Reattach the left and right subtrees.
                        this.root.left = leftNode;
                        this.root.right = rightNode;
                    } else {
                        if (current.value < parentNode.value) {
                            parentNode.left = current.right.left;
                        } else if (current.value > parentNode.value) {
                            parentNode.right = current.right.left;
                        }
                    }
                }
                return;
            }
        }
    }

    ///////////////////////////////////////////////////////
    //////////         Find LCA Function       /////////////
    ///////////////////////////////////////////////////////
    /**
     * Finds the Lowest Common Ancestor (LCA) of two given nodes in the BST.
     *
     * @param root The root node of the BST.
     * @param p    The first node for which to find the LCA.
     * @param q    The second node for which to find the LCA.
     * @return The {@code Node} representing the LCA of nodes {@code p} and {@code q}.
     */
    public Node findLCA(Node root, Node p, Node q) {
        // Start from the root of the BST.
        Node current = root;
        while (current != null) {
            // If both p and q are greater than current, LCA lies in the right subtree.
            if (p.value > current.value && q.value > current.value) {
                current = current.right;
            }
            // If both p and q are less than current, LCA lies in the left subtree.
            else if (p.value < current.value && q.value < current.value) {
                current = current.left;
            }
            // If p and q lie on either side of current, or one of them is equal to current,
            // then current is the LCA.
            else {
                return current;
            }
        }
        return null; // If LCA doesn't exist (which shouldn't happen given the constraints).
    }

    ///////////////////////////////////////////////////////
    //////////                DFS               /////////////
    ///////////////////////////////////////////////////////
    HelperFunctions hlp = new HelperFunctions();

    /**
     * Performs an in-order depth-first search (DFS) traversal of the BST.
     *
     * @return An {@code ArrayList<Integer>} containing the values of nodes in in-order DFS.
     */
    public ArrayList<Integer> DFSInOrder() {
        ArrayList<Integer> answer = new ArrayList<>();
        return hlp.traverseInOrder(this.root, answer);
    }

    /**
     * Performs a pre-order depth-first search (DFS) traversal of the BST.
     *
     * @return An {@code ArrayList<Integer>} containing the values of nodes in pre-order DFS.
     */
    public ArrayList<Integer> DFSPreOrder() {
        ArrayList<Integer> answer = new ArrayList<>();
        return hlp.traversePreOrder(this.root, answer);
    }

    /**
     * Performs a post-order depth-first search (DFS) traversal of the BST.
     *
     * @return An {@code ArrayList<Integer>} containing the values of nodes in post-order DFS.
     */
    public ArrayList<Integer> DFSPostOrder() {
        ArrayList<Integer> answer = new ArrayList<>();
        return hlp.traversePostOrder(this.root, answer);
    }

    ///////////////////////////////////////////////////////
    //////////   Breadth First Search Methods    //////////
    ///////////////////////////////////////////////////////
    /**
     * Performs an iterative breadth-first search (BFS) on the BST.
     *
     * @return An {@code ArrayList<Integer>} containing the values of nodes in BFS order.
     */
    public ArrayList<Integer> breadthFirstSearch() {
        if (this.root == null) {
            return new ArrayList<>();
        }

        Node currentNode = this.root;
        ArrayList<Integer> resultArray = new ArrayList<>();
        ArrayList<Node> queue = new ArrayList<>();
        queue.add(currentNode);

        while (queue.size() > 0) {
            currentNode = queue.remove(0);
            resultArray.add(currentNode.value);

            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
        return resultArray;
    }

    /**
     * Performs a recursive breadth-first search (BFS) on the BST.
     *
     * @param queue       An {@code ArrayList<Node>} representing the current queue of nodes.
     * @param resultArray An {@code ArrayList<Integer>} to store the BFS traversal result.
     * @return An {@code ArrayList<Integer>} containing the values of nodes in BFS order.
     */
    public ArrayList<Integer> breadthFirstSearchRecursive(
            ArrayList<Node> queue,
            ArrayList<Integer> resultArray
    ) {
        if (queue.size() == 0) {
            return resultArray;
        }

        Node currentNode = queue.remove(0);
        resultArray.add(currentNode.value);
        if (currentNode.left != null) {
            queue.add(currentNode.left);
        }
        if (currentNode.right != null) {
            queue.add(currentNode.right);
        }

        return breadthFirstSearchRecursive(queue, resultArray);
    }

    /**
     * The {@code main} method serves as an entry point to test the {@code BinarySearchTree} class with various input scenarios.
     *
     * <p>It demonstrates the insertion of nodes, traversal methods, lookup, removal, and finding the LCA of two nodes.</p>
     *
     * @param args Command-line arguments (not utilized in this program).
     */
    public static void main(String[] args) {
        // Initialize the Binary Search Tree.
        BinarySearchTree bst = new BinarySearchTree();

        // Insert nodes into the BST.
        /*
                        6
                      /   \
                     2     8
                    / \   / \
                   0   4 7   9
                      / \
                     3   5
        */
        bst.insert(6);
        bst.insert(2);
        bst.insert(8);
        bst.insert(0);
        bst.insert(4);
        bst.insert(7);
        bst.insert(9);
        bst.insert(3);
        bst.insert(5);

        // Perform and display Breadth First Search (BFS).
        System.out.println("BFS: " + bst.breadthFirstSearch());

        // Perform and display Recursive Breadth First Search.
        ArrayList<Node> queue = new ArrayList<>();
        queue.add(bst.root);
        System.out.println("BFS Recursive: " + bst.breadthFirstSearchRecursive(queue, new ArrayList<>()));

        // Perform and display Depth First Search (DFS) Traversals.
        System.out.println("DFS InOrder: " + bst.DFSInOrder());
        System.out.println("DFS PreOrder: " + bst.DFSPreOrder());
        System.out.println("DFS PostOrder: " + bst.DFSPostOrder());

        // Lookup for a value in the BST.
        System.out.println("Lookup 8: " + bst.lookup(8)); // Output: true

        // Remove a node and perform lookup again.
        bst.remove(8);
        System.out.println("Lookup 8 after removing 8: " + bst.lookup(8)); // Output: false

        // Find Lowest Common Ancestor (LCA) of two nodes.
        Node p = bst.lookupNode(2);
        Node q = bst.lookupNode(5);
        Node lca = bst.findLCA(bst.root, p, q);
        if (lca != null) {
            System.out.println("LCA of " + p.value + " and " + q.value + " is: " + lca.value); // Expected Output: 2
        } else {
            System.out.println("LCA not found.");
        }

        // Another LCA example
        p = bst.lookupNode(3);
        q = bst.lookupNode(5);
        lca = bst.findLCA(bst.root, p, q);
        if (lca != null) {
            System.out.println("LCA of " + p.value + " and " + q.value + " is: " + lca.value); // Expected Output: 4
        } else {
            System.out.println("LCA not found.");
        }
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
