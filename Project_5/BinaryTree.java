
import java.util.ArrayList;
import java.util.List;

public class BinaryTree<K extends Comparable<K>, V> {
    private Node<K, V> root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public Node<K, V> getRoot() {
        return this.root;
    }

    public void add(K key, V value) {

        Node<K, V> newNode = new Node<>(key, value);

        // If root is empty, add the new node as the current root
        if (root == null) {
            root = newNode;
            return;
        }
        Node<K, V> ptr = root;
        while(true) {
            // Check if the key is smaller than the pointer key
            // If the key is smaller than pointer key, go to the left child until it hits the leaf node that enables us to add a new node
            if (key.compareTo(ptr.getKey()) < 0) {
                if (ptr.getLeft() == null) {
                    ptr.setLeft(newNode);
                    return;    // Stop the loop
                } else {
                    ptr = ptr.getLeft();
                }
            }
            // If the key is bigger than pointer key, go to the right child until it hits the leaf node that enables us to add a new node
            else if (key.compareTo(ptr.getKey()) > 0) {
                if (ptr.getRight() == null) {
                    ptr.setRight(newNode);
                    return;    // Stop the loop
                }
                else{
                    ptr = ptr.getRight();
                }
            }
            // If the key is existed in the main tree, update a new value
            else {
                ptr.setValue(value);
                return;        // Stop the loop
            }
        }
    }

    public V find(K key) {
        Node<K, V> node = root;

        // Go into the loop if the key is not found yet
        while(node.getKey() != key){
            if(key.compareTo(node.getKey()) > 0){
                node = node.getRight();
            }
            else if(key.compareTo(node.getKey()) < 0) {
                node = node.getLeft();
            }
            // If the node hits to leaf node
            if( node == null ){
                return null;
            }
        }
        // return the value once it ends the while loop that helps to find the exact key
        return node.getValue();
    }

    @SuppressWarnings("unchecked")

    // Use the in order tree traversal to sort accordingly
    public V[] flatten() {

        List<V> elements = new ArrayList();

        Node<K, V> ptr = root;
        List<V> finalResult = inOrder(ptr, elements);

        V[] finalArray = (V[]) finalResult.toArray();

        return finalArray;
    }

    // Use helper method which recurs through the main tree
    public void remove(K key) {
        deleteRec(root, key);
    }

    public boolean containsSubtree(BinaryTree<K, V> other) {

        Node<K, V> root = this.getRoot();
        Node<K, V> otherSubTree = other.getRoot();
        return isSubtree(root, otherSubTree);

    }

    // HELPER METHODS

    // Helper thod that check if the subtree structure is in the main tree
    private boolean areIdentical(Node<K, V> root, Node<K, V> otherSubTree){

        // 2 base cases
        if (root == null && otherSubTree == null) {
            return true;
        }

        if (root == null || otherSubTree == null) {
            return false;
        }

        // Start comparing the key and value from the root to sub tree
        // Recursion for checking the both left and right sub trees has the similar structure to main tree
        return (root.getKey() == otherSubTree.getKey()
                && root.getValue() == otherSubTree.getValue()
                && areIdentical(root.getLeft(), otherSubTree.getLeft())
                && areIdentical(root.getRight(), otherSubTree.getRight()));
    }

    // Helper method that check if the subtree structure is in the main tree
    private boolean isSubtree(Node<K, V> root, Node<K, V> otherSubTree){

        // Two base case for this helper method.
        // For the first base case, if otherSubTree is empty, we return true since there is nothing to compare
        if (otherSubTree == null) {
            return true;
        }
        // For the second base case, if the root is empty, we return false since we can't compare empty root with sub tree
        if (root == null) {
            return false;
        }
        // Compare actual tree structure of other present in main tree
        if (areIdentical(root, otherSubTree)) {
            return true;
        }
        // Recursion down for both left and right subtrees if it fails 3 conditions above
        return isSubtree(root.getLeft(), otherSubTree) || isSubtree(root.getRight(), otherSubTree);
    }

    private Node<K, V> deleteRec(Node<K, V> root, K key) {

        // Start with a base case which checks if the main tree is empty
        if (root == null) {
            return root;
        }

        // Recursion to find the exact key from the main tree
        if(key.compareTo(root.getKey()) < 0) {
            root.setLeft(deleteRec(root.getLeft(), key));
        }
        else if(key.compareTo(root.getKey()) > 0) {
            root.setRight(deleteRec(root.getRight(), key));
        }
        // If the key is equal to the root key, we can start removing that key
        else {
            // Check if that node with assigned key has one child
            if (root.getLeft() == null) {
                return root.getRight();
            }
            else if (root.getRight() == null) {
                return root.getLeft();
            }
            // Recursion that set a new node if the node with assigned has two children
            else {
                // For our case, we can get the minimum key and value from the right child
                root.setKey(minKey((root.getRight())));
                root.setValue(minValue((root.getRight())));

                // Delete the parent node and set the new child node with minimum key and value
                root.setRight(deleteRec(root.getRight(), root.getKey()));
            }
        }
        return root;
    }

    // Helper method that get the minimum key with is located to the most left side of the tree
    private K minKey(Node root) {

        K minK = (K) root.getKey();
        while (root.getLeft() != null)
        {
            minK = (K) root.getLeft().getKey();
            root = root.getLeft();
        }
        return minK;
    }

    // Helper method that get the minimum value with is located to the most left side of the tree
    private V minValue(Node root) {

        V minV = (V) root.getValue();
        while (root.getLeft() != null)
        {
            minV = (V) root.getLeft().getKey();
            root = root.getLeft();
        }
        return minV;
    }

    // Helper method that flatten the subtree in Array List
    private List<V> inOrder(Node<K, V> node, List<V> elements){

        if (node == null)
            return elements;

        inOrder(node.getLeft(), elements);

        elements.add(node.getValue());

        inOrder(node.getRight(), elements);

        return elements;

    }

}
