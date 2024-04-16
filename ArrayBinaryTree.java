// ===============================================
// CSCI 3230 Data Structures
// Instructor: Yao Xu, Ph.D.
// 
// M5 - Trees
//      An Array Implementation of Binary Tree
// 
// ===============================================
public class ArrayBinaryTree<E> {

    public static final int CAPACITY = 1000;  // default array capacity
    protected E[] treeArray;
    protected int size;
    private int rootIndex;
    
    // Constructors:
    @SuppressWarnings("unchecked")
    public ArrayBinaryTree() { 
        treeArray = (E[]) new Comparable[CAPACITY];
    }
    
    @SuppressWarnings("unchecked")
    public ArrayBinaryTree(int capacity) {
        treeArray = (E[]) new Comparable[capacity];
        size = 0;
        rootIndex = -1;
    }
    
    // Accessor methods:
    // returns element of node at given index
    public E getElement(int index) {
        return validateIndex(index) ? treeArray[index] : null;
    }
    
    // returns the index of the parent of node at given index
    public int getParentIndex(int index) {
        if (index == 0 || !validateIndex(index))
            return -1;
        return (index - 1) / 2;
    }
    
    public E getParent(int index) { 
        return treeArray[getParentIndex(index)];
    }

    // returns the index of the left child of node at given index
    public int getLeftChildIndex(int parentIndex) {
        int leftChildIndex = 2 * parentIndex + 1;
        return validateIndex(leftChildIndex) ? leftChildIndex : -1;
    }
    
    public E getLeftChild(int index) { 
        return treeArray[getLeftChildIndex(index)];
    }

    // returns the index of the right child of node at given index
    public int getRightChildIndex(int parentIndex) {
        int rightChildIndex = 2 * parentIndex + 2;
        return validateIndex(rightChildIndex) ? rightChildIndex : -1;
    }
    
    public E getRightChild(int index) { 
        return treeArray[getRightChildIndex(index)];
    }
    
    // Modifier methods:
    // sets element of root
    public void setRoot(E element) throws IllegalStateException {
        if (rootIndex != -1)
            throw new IllegalStateException("Root already exists");
        rootIndex = 0;
        setElement(rootIndex, element);     
    }
    
    // sets element of node at given index
    // increment size by 1 if newly added
    public void setElement(int index, E element) {
        if (treeArray[index] == null)
            size++;
        if (validateIndex(index))
            treeArray[index] = element;
    }
    
    // sets element of left child of the node at given index
    public void setLeftChild(int parentIndex, E element) throws IllegalStateException {
        int leftChildIndex = 2 * parentIndex + 1;
        if (leftChildIndex >= treeArray.length)
            throw new IllegalStateException("Cannot set left child: array capacity exceeded");
        setElement(leftChildIndex, element);
    }
    
    // sets element of right child of the node at given index
    public void setRightChild(int parentIndex, E element) throws IllegalStateException {
        int rightChildIndex = 2 * parentIndex + 2;
        if (rightChildIndex >= treeArray.length)
            throw new IllegalStateException("Cannot set right child: array capacity exceeded");
        setElement(rightChildIndex, element);
    }
    
    // returns the number of nodes in the tree
    public int size() { 
        return size; 
    }
    
    // returns true if tree is empty
    public boolean isEmpty() { 
        return size == 0; 
    }
    
    // returns true if node at given index is root
    public boolean isRoot(int index) { 
        return rootIndex == index; 
    }
    
    // returns true if index is valid
    private boolean validateIndex(int index) {
        return index >= 0 && index < treeArray.length;
    }
    
    // Three traversal methods:   
    public void preorder(int index) {
        if (index < size) {
	        System.out.print(treeArray[index] + " ");
	        preorder(2 * index + 1);
	        preorder(2 * index + 2);
        }
    }

    public void postorder(int index) {
        if (index < size) {
	        postorder(2 * index + 1);
	        postorder(2 * index + 2);
	        System.out.print(treeArray[index] + " ");
        }
    }
    
    public void inorder(int index) {
        if (index < size) {
	        inorder(2 * index + 1);
	        System.out.print(treeArray[index] + " ");
	        inorder(2 * index + 2);
        }
    }

    // returns the tree's node elements as an array
    public String toString() {
      StringBuilder sb = new StringBuilder("");
      for (int j = 0; j < size; j++)
        sb.append(treeArray[j] + " ");
      return sb.toString();
    }

    
    // ------------------------- Driver -------------------------
    public static void main(String[] args) {
        ArrayBinaryTree<Character> binaryTree = new ArrayBinaryTree<>(10);

        // Create a binary tree
        binaryTree.setRoot('A');
        binaryTree.setLeftChild(0, 'B');
        binaryTree.setRightChild(0, 'C');
        binaryTree.setLeftChild(1, 'D');
        binaryTree.setRightChild(1, 'E');
        System.out.println("Array representation of the tree: " + binaryTree.toString());
        
        System.out.println("There are " + binaryTree.size() + " elements in the tree.");

        // Perform three traversals:
        System.out.print("Preorder traversal: ");
        binaryTree.preorder(0);
        System.out.println();
        
        System.out.print("Postorder traversal: ");
        binaryTree.postorder(0);
        System.out.println();
        
        System.out.print("Inorder traversal: ");
        binaryTree.inorder(0);
        System.out.println();
    
    }
}
