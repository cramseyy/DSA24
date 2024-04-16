// ===============================================
// CSCI 3230 Data Structures
// Instructor: Yao Xu, Ph.D.
// 
// M5 - Trees
//      A Binary Tree Node class
// 
// ===============================================
public class BinaryTreeNode<E> {
	private E element;
	private BinaryTreeNode<E> parent;
	private BinaryTreeNode<E> left;
	private BinaryTreeNode<E> right;

	// Contructor:
	public BinaryTreeNode(E e) {
		element = e;
		parent = null;
		left = null;
		right = null;
	}

	// Accessor methods:
	public E getElement() {
		return element;
	}

	public BinaryTreeNode<E> getParent() {
		return parent;
	}

	public BinaryTreeNode<E> getLeft() {
		return left;
	}

	public BinaryTreeNode<E> getRight() {
		return right;
	}

	// Modifier methods:
	public void setElement(E e) {
		element = e;
	}

	public void setParent(BinaryTreeNode<E> p) {
		parent = p;
	}

	public void setLeft(BinaryTreeNode<E> l) {
		left = l;
		l.parent = this;
	}

	public void setRight(BinaryTreeNode<E> r) {
		right = r;
		r.parent = this;
	}

}