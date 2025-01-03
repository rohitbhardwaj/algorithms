package trees.bst ; 
import java.util.ArrayList;

/**
 * The {@code MyBST} class provides an implementation of a Binary Search Tree (BST) with
 * functionalities to insert, lookup, remove nodes, and perform various tree traversals.
 *
 * <p>This class includes methods for breadth-first search (both iterative and recursive)
 * and depth-first search (in-order, pre-order, post-order).</p>
 *
 * <p>Example Usage:</p>
 * <pre>{@code
 * MyBST bst = new MyBST();
 * bst.insert(9);
 * bst.insert(4);
 * bst.insert(6);
 * bst.insert(20);
 * bst.insert(170);
 * bst.insert(15);
 * bst.insert(1);
 *
 * System.out.println("BFS: " + bst.breadthFirstSearch());
 * System.out.println("Lookup 20: " + bst.lookup(20));
 * }</pre>
 *
 * @author
 */
public class MyBST {
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
    //                Breadth First Search              //
    //   Note: {This method is from the algorithms section} //
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

    ///////////////////////////////////////////////////////
    //         Breadth First Search Recursive          //
    //   Note: {This method is from the algorithms section} //
    ///////////////////////////////////////////////////////
    /**
     * Performs a recursive breadth-first search (BFS) on the BST.
     *
     * @param queue        An {@code ArrayList<Node>} representing the current queue of nodes.
     * @param resultArray  An {@code ArrayList<Integer>} to store the BFS traversal result.
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

    ///////////////////////////////////////////////////////
    //////////              DFS               /////////////
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

    // Main method is moved to a separate public class.
}
