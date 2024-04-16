// ===============================================
// CSCI 3230 Data Structures
// Instructor: Yao Xu, Ph.D.
// 
// M5 - Trees
//      An Implementation of Binary Tree 
// 		Using Linked Structure
// 
// ===============================================
public class LinkedBinaryTree<E> {
	
    private BinaryTreeNode<E> root;
    
    // Constructors:
    public LinkedBinaryTree() { 
        root = null; 
    }
    
    public LinkedBinaryTree(E e) {
    	root = new BinaryTreeNode<E>(e);
    }
    
    // Accessor method:
    public BinaryTreeNode<E> getRoot() { 
        return root; 
    }

    // Modifier methods:
    public void setRoot(BinaryTreeNode<E> r) { 
        root = r; 
    }
    
    public void setRoot(E e) {
        if (root != null)
            throw new IllegalStateException("Root already exists.");
        root = new BinaryTreeNode<>(e);
    }
    
    // checks whether a node is a root
    public boolean isRoot(BinaryTreeNode<E> node) { 
    	return node.getParent() == null;
    }
    
    // returns true if tree is empty
    public boolean isEmpty() { 
        return root == null; 
    }
    
    // returns the number of nodes in the (sub)tree rooted at node
    public int size(BinaryTreeNode<E> node) {
        if (node == null) return 0;
        int leftSize = size(node.getLeft());
        int rightSize = size(node.getRight());
        return leftSize + rightSize + 1;
    }
    
    // Preorder traversal
    public void preOrderTraversal(BinaryTreeNode<E> node) {
        if (node != null) {
            System.out.print(node.getElement() + " ");
            preOrderTraversal(node.getLeft());
            preOrderTraversal(node.getRight());
        }
    }
    
    // Postorder traversal
    public void postOrderTraversal(BinaryTreeNode<E> node) {
        if (node != null) {
            postOrderTraversal(node.getLeft());
            postOrderTraversal(node.getRight());
            System.out.print(node.getElement() + " ");
        }
    }
    
    // Inorder Traversal
    public void inOrderTraversal(BinaryTreeNode<E> node) {
        if (node != null) {
            inOrderTraversal(node.getLeft());
            System.out.print(node.getElement() + " ");
            inOrderTraversal(node.getRight());
        }
    }
    
    
    // ------------------------- Driver -------------------------
    public static void main(String[] args) {
        LinkedBinaryTree<Character> binaryTree = new LinkedBinaryTree<>();

        // Create nodes for building a binary tree
        BinaryTreeNode<Character> root = new BinaryTreeNode<>('A');
        BinaryTreeNode<Character> nodeB = new BinaryTreeNode<>('B');
        BinaryTreeNode<Character> nodeC = new BinaryTreeNode<>('C');
        BinaryTreeNode<Character> nodeD = new BinaryTreeNode<>('D');
        BinaryTreeNode<Character> nodeE = new BinaryTreeNode<>('E');
        
        // Create a binary tree
        binaryTree.setRoot(root);
        root.setLeft(nodeB);
        root.setRight(nodeC);
        nodeB.setLeft(nodeD);
        nodeB.setRight(nodeE);
        
        System.out.println("There are " + binaryTree.size(root) + " nodes in the tree.");

        // Perform three traversals:
        System.out.print("Preorder Traversal: ");
        binaryTree.preOrderTraversal(binaryTree.getRoot());
        System.out.println();

        System.out.print("Postorder Traversal: ");
        binaryTree.postOrderTraversal(binaryTree.getRoot());
        System.out.println();
        
        System.out.print("Inorder Traversal: ");
        binaryTree.inOrderTraversal(binaryTree.getRoot());
        System.out.println();
   
    }
}
