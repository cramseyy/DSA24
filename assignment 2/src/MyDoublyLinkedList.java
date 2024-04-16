// ========================================================================
// CSCI 3230 Data Structures
// Instructor: Yao Xu, Ph.D.
//
// Coding Assignment 2
//
// =========================== Requirements =============================== 
//
// In this MyDoublyLinkedList class, please complete the method bodies of 
// 		addBetween(E e, Node<E> pred, Node<E> succ)
// 		remove(E e)
// 		remove(Node<E> node)
//
// Your output may look as follows:
// ------------------------------------------------------------------------ 
// Current list: (1, 2, 3, 4, 5)
// Current list: (-3, -2, -1, 0, 1, 2, 3, 4, 5)
// There are 9 elements in the list.
// 
// After removing the first element: (-2, -1, 0, 1, 2, 3, 4, 5)
// After removing the last element: (-2, -1, 0, 1, 2, 3, 4)
// 
// Trying to remove element 10...
// No element 10 found!
// Trying to remove element 0...
// Element 0 found and removed!
// The updated list is: (-2, -1, 1, 2, 3, 4)
// Now there are 6 elements in the list.
//
// ============================== Note ====================================
//
// 1. DO NOT MODIFY OR DELETE ANY GIVEN CODE OR COMMENTS!!!
// 2. You ONLY need to write code under each comment "YOUR CODE GOES HERE".
// 3. Modify the file name to "MyDoublyLinkedList.java" to compile and run.
//
// ========================================================================

public class MyDoublyLinkedList<E> {
	//------------------------- Node class -------------------------
	private static class Node<E> {
		private E element; 
		private Node<E> prev;
		private Node<E> next;
		
		public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
		}
		
		public E getElement() { return element; }
		public Node<E> getPrev() { return prev; }
		public Node<E> getNext() { return next; }
		public void setPrev(Node<E> p) { prev = p; }
		public void setNext(Node<E> n) { next = n; }
	} //-------------------- End of Node class --------------------
	
	//------------------------- Data field -------------------------
	public Node<E> header;  // dummy node before head
	public Node<E> trailer;  // dummy node after tail 
	public int size = 0; 
	
	//------------------------- Method field ------------------------- 
	// Constructor:
	public MyDoublyLinkedList() {
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, null, null);
	}
	
	// returns the number of elements in the list
	public int size() { 
		return size; 
	}
	
	// returns true if list is empty
	public boolean isEmpty() { 
		return size == 0; 
	}
	
	// returns the first element
	public E first() {	
		if (isEmpty()) 
			return null;
		return header.getNext().getElement(); // head.getElement()
	}
	
	// returns the last element
	public E last() {
		if (isEmpty()) 
			return null;
		return trailer.getPrev().getElement(); // tail.getElement()
	}

	// adds element e to front of the list
	public void addFirst(E e) {
		if (isEmpty()) {
	        Node<E> newNode = new Node<>(e, header, trailer);
	        header.setNext(newNode);
	        trailer.setPrev(newNode);
	        size++;
	    } else {
	        addBetween(e, header, header.getNext()); // place just after the header
	    }
	}
	
	// adds element e to end of the list
	public void addLast(E e) {
		if (isEmpty()) {
	        Node<E> newNode = new Node<>(e, header, trailer);
	        header.setNext(newNode);
	        trailer.setPrev(newNode);
	        size++;
	    } else {
	        addBetween(e, trailer.getPrev(), trailer); // place just before the trailer
	    }
	}
	
	// inserts an element e between nodes pred and succ
	private void addBetween(E e, Node<E> pred, Node<E> succ) {
		// YOUR CODE GOES HERE --Part 1/3--
		Node<E> newNode = new Node<>(e, pred, succ);
	    pred.setNext(newNode);
	    succ.setPrev(newNode);
	    size++;
	}
		
	// removes and returns the first element
	public E removeFirst() {
		if (isEmpty()) 
			return null;
		return remove(header.getNext());
	}
	
	// removes and returns the last element
	public E removeLast() {
		if (isEmpty()) 
			return null;
		return remove(trailer.getPrev()); 
	}
	
	// removes the node with element value being e
	public E remove(E e) {
		// YOUR CODE GOES HERE --Part 2/3--
		Node<E> node = header.getNext(); 
	    while (node != trailer) {
	        if (node.getElement().equals(e)) {
	            return remove(node); 
	        }
	        node = node.getNext();
	    }
	    System.out.println("No element " + e + " found!");
	    return null;
 	}
	
	// remove a node
	private E remove(Node<E> node) {
		// YOUR CODE GOES HERE --Part 3/3--
		Node<E> pred = node.getPrev();
	    Node<E> succ = node.getNext();
	    pred.setNext(succ);
	    succ.setPrev(pred);
	    size--;
	    return node.getElement();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		Node<E> walk = header.getNext();
		while (walk != trailer) {
			sb.append(walk.getElement());
			walk = walk.getNext();
			if (walk != trailer)
				sb.append(", ");
		}
		sb.append(")");
		return sb.toString();
	}
	
   
	//------------------------- Driver -------------------------
	public static void main(String[] args) {
		// Create a doubly linked list of integers
		MyDoublyLinkedList<Integer> list = new MyDoublyLinkedList<Integer>();
		
		// Add 1 to 5 to list using addLast
		for (int i = 1; i <= 5; i++)
			list.addLast(i);
		System.out.println("Current list: " + list.toString());
		
		// Add 0 down to -3 to list using addFirst
		for (int j = 0; j <= 3; j++)
			list.addFirst(-j);
		System.out.println("Current list: " + list.toString());
     
		System.out.println("There are " + list.size() + " elements in the list.");
		System.out.println();
      
		// Remove the first element
		list.removeFirst();
		System.out.println("After removing the first element: " + list.toString());
		
		// Remove the last element
		list.removeLast();
		System.out.println("After removing the last element: " + list.toString());
		System.out.println();
      
		// Remove element 10
		int e1 = 10;
		System.out.println("Trying to remove element " + e1 + "...");
		list.remove(e1);
		// Remove element 0
		int e2 = 0;
		System.out.println("Trying to remove element " + e2 + "...");
		list.remove(e2);
		
		System.out.println("The updated list is: " + list.toString());
		System.out.println("Now there are " + list.size() + " elements in the list.");
      
	}
   
}