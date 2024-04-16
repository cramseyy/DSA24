// ========================================================================
// CSCI 3230 Data Structures
// Instructor: Yao Xu, Ph.D.
//
// Coding Assignment 4
//
// =========================== Requirements =============================== 
// Implement the operations of finding a successor, finding a predecessor,
// and insertion. That is, complete the method bodies of:
//      BinaryTreeNode<E> findSuccessor(BinaryTreeNode<E> node)
//      BinaryTreeNode<E> findPredecessor(BinaryTreeNode<E> node)
//      void insertNode(BinaryTreeNode<E> node, BinaryTreeNode<E> newNode)
//
// The BST class is a subclass of the LinkedBinaryTree class and is also
// using the BinaryTreeNode class, which are provided in the two files,
// "LinkedBinaryTree.java" and "BinaryTreeNode.java". Please place them 
// under the same package/folder.
//
// Your output may look as follows:
// ------------------------------------------------------------------------
// BST (inorder): 1 2 4 5 7 8 9 
// BST (preorder): 5 2 1 4 7 9 8 
// The successor of 2 is 4
// The successor of 7 is 8
// The predecessor of 5 is 4
// The predecessor of 7 is 5
// 
// After inserting 3 and 6 to the BST
// Updated BST (inorder): 1 2 3 4 5 6 7 8 9 
// Updated BST (postorder): 1 3 4 2 6 8 9 7 5 
// The successor of 2 is 3
// The predecessor of 7 is 6
//
// ============================== Note ====================================
//
// 1. DO NOT MODIFY OR DELETE ANY GIVEN CODE OR COMMENTS!!!
// 2. You ONLY need to write code under each comment "YOUR CODE GOES HERE".
// 3. Modify the file name to "BST.java" to compile and run.
// 4. Make sure that you place the two files "LinkedBinaryTree.java" and 
//    "BinaryTreeNode.java" under the same package/folder as your current 
//    file to compile and run the code.
//
// ========================================================================

public class BST<E extends Comparable<E>> extends LinkedBinaryTree<E> {

	// Constructors:
	public BST() {
		super();
	}

	public BST(E e) {
		super(e);
	}

	// Searching: Returns true if node with given key exists
	public boolean search(E element) {
		return searchNode(getRoot(), element) != null;
	}

	private BinaryTreeNode<E> searchNode(BinaryTreeNode<E> node, E element) {
		if (node == null || element.compareTo(node.getElement()) == 0)
			return node;
		else if (element.compareTo(node.getElement()) < 0)
			return searchNode(node.getLeft(), element);
		else
			return searchNode(node.getRight(), element);
	}

	// Returns the minimum key
	public E findMinimum() throws IllegalStateException {
		if (getRoot() == null)
			throw new IllegalStateException("Tree is empty");
		return findMinimum(getRoot()).getElement();
	}

	private BinaryTreeNode<E> findMinimum(BinaryTreeNode<E> node) {
		while (node.getLeft() != null)
			node = node.getLeft();
		return node;
	}

	// Returns the maximum key
	public E findMaximum() throws IllegalStateException {
		if (getRoot() == null)
			throw new IllegalStateException("Tree is empty");
		return findMaximum(getRoot()).getElement();
	}

	private BinaryTreeNode<E> findMaximum(BinaryTreeNode<E> node) {
		while (node.getRight() != null)
			node = node.getRight();
		return node;
	}

	// Returns the key of the successor of given element
	public E findSuccessor(E element) throws IllegalArgumentException {
		BinaryTreeNode<E> node = searchNode(getRoot(), element);
		if (node == null)
			throw new IllegalArgumentException("Node with key = " + element + " does not exist!");
		BinaryTreeNode<E> successor = findSuccessor(node);
		if (successor == null)
			throw new IllegalArgumentException("Node with key = " + element + " has no successor.");
		else
			return successor.getElement();
	}

	private BinaryTreeNode<E> findSuccessor(BinaryTreeNode<E> node) {
		// YOUR CODE GOES HERE --Part 1/3--
		if (node.getRight() != null) {
			return findMinimum(node.getRight());
		} else {
			BinaryTreeNode<E> y = node.getParent();
			while (y != null && node == y.getRight()) {
				y = node;
				y.getParent();
			}
			return y;
		}

	}

	// Returns the key of the successor of given element
	public E findPredecessor(E element) throws IllegalArgumentException {
		BinaryTreeNode<E> node = searchNode(getRoot(), element);
		if (node == null)
			throw new IllegalArgumentException("Node with key = " + element + " does not exist!");
		BinaryTreeNode<E> predecessor = findPredecessor(node);
		if (predecessor == null)
			throw new IllegalArgumentException("Node with key = " + element + " has no predecessor.");
		else
			return predecessor.getElement();
	}

	private BinaryTreeNode<E> findPredecessor(BinaryTreeNode<E> node) {
		// YOUR CODE GOES HERE --Part 2/3--
		if (node.getLeft() != null) {
			return findMaximum(node.getLeft());
		} else {
			BinaryTreeNode<E> y = node.getParent();
			while (y != null && node == y.getLeft()) {
				node = y;
				y = y.getParent();
			}
			return y;
		}

	}

	// Inserts a node with given key
	public void insert(E element) {
		BinaryTreeNode<E> newNode = new BinaryTreeNode<>(element);
		if (getRoot() == null)
			setRoot(newNode);
		else
			insertNode(getRoot(), newNode);
	}

	private void insertNode(BinaryTreeNode<E> node, BinaryTreeNode<E> newNode) {
		// YOUR CODE GOES HERE --Part 3/3--
		if (getRoot() == null) {
			setRoot(newNode);
		} else if (newNode.getElement().compareTo(node.getElement()) < 0) {
			if (node.getLeft() == null) {
				node.setLeft(newNode);
			} else {
				insertNode(node.getLeft(), newNode);
			}
		} else if (newNode.getElement().compareTo(node.getElement()) > 0) {
			if (node.getRight() == null) {
				node.setRight(newNode);
			} else {
				insertNode(node.getRight(), newNode);
			}
		}

	}

	// ------------------------- Driver -------------------------
	public static void main(String[] args) {
		// Create an empty BST
		BST<Integer> bst = new BST<>();

		// Create nodes for building a BST
		BinaryTreeNode<Integer> root = new BinaryTreeNode<>(5);
		BinaryTreeNode<Integer> node1 = new BinaryTreeNode<>(1);
		BinaryTreeNode<Integer> node2 = new BinaryTreeNode<>(2);
		BinaryTreeNode<Integer> node4 = new BinaryTreeNode<>(4);
		BinaryTreeNode<Integer> node7 = new BinaryTreeNode<>(7);
		BinaryTreeNode<Integer> node8 = new BinaryTreeNode<>(8);
		BinaryTreeNode<Integer> node9 = new BinaryTreeNode<>(9);

		// Build a BST
		bst.setRoot(root);
		root.setLeft(node2);
		root.setRight(node7);
		node2.setLeft(node1);
		node2.setRight(node4);
		node7.setRight(node9);
		node9.setLeft(node8);

		// Print the BST using inorder traversal
		System.out.print("BST (inorder): ");
		bst.inOrderTraversal(bst.getRoot());
		System.out.println();

		// Print the BST using preorder traversal
		System.out.print("BST (preorder): ");
		bst.preOrderTraversal(bst.getRoot());
		System.out.println();

//        // Search for nodes with given elements
//        System.out.println("Is 2 in the BST? " + bst.search(2));
//        System.out.println("Is 6 in the BST? " + bst.search(6));
//        
//        // Find the minimum and maximum elements
//        System.out.println("The minimum element is " + bst.findMinimum());
//        System.out.println("The maximum element is " + bst.findMaximum());
//        System.out.println();

		// Find successors
		System.out.println("The successor of 2 is " + bst.findSuccessor(2));
		System.out.println("The successor of 7 is " + bst.findSuccessor(7));

		// Find predecessors
		System.out.println("The predecessor of 5 is " + bst.findPredecessor(5));
		System.out.println("The predecessor of 7 is " + bst.findPredecessor(7));
		System.out.println();

		// Insert two new nodes
		bst.insert(3);
		bst.insert(6);
		System.out.println("After inserting 3 and 6 to the BST");
		// Print the updated BST using inorder traversal
		System.out.print("Updated BST (inorder): ");
		bst.inOrderTraversal(bst.getRoot());
		System.out.println();

		// Print the updated BST using postorder traversal
		System.out.print("Updated BST (postorder): ");
		bst.postOrderTraversal(bst.getRoot());
		System.out.println();

		// Find successor and predecessor
		System.out.println("The successor of 2 is " + bst.findSuccessor(2));
		System.out.println("The predecessor of 7 is " + bst.findPredecessor(7));
	}
}
