1. Adding
This section requires you to complete the following method within BinaryTree.java: 

public void add(K key, V value) {
}

The add method should add a new node to the existing tree with the key and value passed into the method. 
If the key already exists in the binary tree, you should update its value to reflect the value passed 
into the method. If the tree is an empty tree, a root should be created with the key and value passed into the method.

2. Finding
This section requires you to complete the following method within BinaryTree.java:

public V find(K key) {
    return null;
}

The find method should return the value associated with the key passed into the method. If the
key does not exist within the binary tree, null should be returned.

3. Flattening
This section requires you to complete the following method within BinaryTree.java:

public V[] flatten() {
    return (V[]) new Object[0];
}

The flatten function should return an array of all of the values in a binary tree, ordered by key. 
The length of the array should be equal to the number of elements in the tree and duplicate values should be included.

4. Removing
This section requires you to complete the following method within BinaryTree.java: 

public void remove(K key) {
}

The remove method should remove the key from the binary tree and modify the tree accordingly to 
maintain the Binary Search Tree Principle. If the key does not exist in the binary tree, no nodes should be removed

5. Determining Subtrees
This section requires you to complete the following method within BinaryTree.java:

public boolean containsSubtree(BinaryTree<K, V> other) {
    return false;
}

The containsSubtree function should return whether the tree passed into the method is a subtree 
of the tree that it is called from. If the subtree passed into the function is null, containsSubtree should return true.