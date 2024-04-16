// ========================================================================
// CSCI 3230 Data Structures
// Instructor: Yao Xu, Ph.D.
//
// Coding Assignment 3
//
// =========================== Requirements =============================== 
// Implement the insertion and deletion operations on an array-based heap.
// That is, complete the method bodies of:
//		void insert(E element)
// 		E remove()
// 		void upHeap(int index)
// 		void downHeap(int index)
//
// The ArrayHeap class is a subclass of the ArrayBinaryTree class, which is
// provided in the file "ArrayBinaryTree.java". Please place them under the
// same package/folder.
//
// Your output may look as follows:
// ------------------------------------------------------------------------
// Original heap: 2 5 6 9 7 
// Inserting a new element 1 ...
// New heap: 1 5 2 9 7 6 
// The minimum element is: 1
// Updated heap after removing 1: 2 5 6 9 7 
// The minimum element of the updated heap is: 2
// Updated heap after removing 2: 5 7 6 9 
//
// ============================== Note ====================================
//
// 1. DO NOT MODIFY OR DELETE ANY GIVEN CODE OR COMMENTS!!!
// 2. You ONLY need to write code under each comment "YOUR CODE GOES HERE".
// 3. Modify the file name to "ArrayHeap.java" to compile and run.
// 4. Make sure that you place the file "ArrayBinaryTree.java" under the
//    same package/folder as your current file to compile and run the code.
//
// ========================================================================

public class ArrayHeap<E extends Comparable<E>> extends ArrayBinaryTree<E> {	
	
    // Constructors:
    public ArrayHeap() { super(); }
    
    public ArrayHeap(int capacity) { super(capacity); }
    
    // returns the root/minimum element of the heap
    public E min() throws IllegalStateException {
       if (size == 0)
           throw new IllegalStateException("Heap is empty.");
       return treeArray[0];
    }
    
    // inserts a new element to the heap and maintains heap property using upHead
    public void insert(E element) throws IllegalStateException {
        if (size >= treeArray.length)
	        throw new IllegalStateException("Cannot insert element: array capacity exceeded");
        // YOUR CODE GOES HERE --Part 1/4--
        treeArray[size] = element;
        upHeap(size);
        size++;
    }
    
    // removes the root from the heap and maintains heap property using downHeap
    // returns the removed element
    public E remove() throws IllegalStateException {
        if (size == 0)
            throw new IllegalStateException("Heap is empty.");
        // YOUR CODE GOES HERE --Part 2/4--
        E removedElement = treeArray[0];
        treeArray[0] = treeArray[size - 1];
        size--;
        downHeap(0);
        
        return removedElement;
    }
    
    // maintains heap property by upHeap
    private void upHeap(int index) {
        // YOUR CODE GOES HERE --Part 3/4--
        int p;
        while (index >= 0) {
            p = (index - 1) / 2;

            if (treeArray[index].compareTo(treeArray[p]) < 0) { 
                E temp = treeArray[index];
                treeArray[index] = treeArray[p];
                treeArray[p] = temp;
                
                index = p;
            } else {
                break;
            }
        }
    }
    
    // maintains heap property by downHeap
    private void downHeap(int index) {
        // YOUR CODE GOES HERE --Part 4/4--
    	int smallest;

        while (true) {
            int leftChild = getLeftChildIndex(index);
            int rightChild = getRightChildIndex(index);
            smallest = index;

            if (leftChild < size && treeArray[leftChild].compareTo(treeArray[smallest]) < 0) {
                smallest = leftChild;
            }

            if (rightChild < size && treeArray[rightChild].compareTo(treeArray[smallest]) < 0) {
                smallest = rightChild;
            }

            if (smallest != index) {            
                E temp = treeArray[index];
                treeArray[index] = treeArray[smallest];
                treeArray[smallest] = temp;
                
                index = smallest;
            } else {
                break;
            }
        }
    }
   
    public static void main(String[] args) {
    	// Create a new ArrayHeap
        ArrayHeap<Integer> heap = new ArrayHeap<>();

        // Insert elements into the heap
        heap.insert(9);
        heap.insert(5);
        heap.insert(6);
        heap.insert(2);
        heap.insert(7);
       
        // Print the heap
        System.out.println("Original heap: " + heap.toString());
        
        // Insert new element 1
        int k = 1;
        System.out.println("Inserting a new element " + k + " ...");
        heap.insert(1);
        System.out.println("New heap: " + heap.toString());

        // Remove the minimum key
        int min1 = heap.remove();
        System.out.println("The minimum element is: " + min1);
        System.out.println("Updated heap after removing " + min1 + ": " + heap.toString());
        
        // Remove the next minimum key
        int min2 = heap.remove();
        System.out.println("The minimum element of the updated heap is: " + min2);
        System.out.println("Updated heap after removing " + min2 + ": " + heap.toString());

    }

}
