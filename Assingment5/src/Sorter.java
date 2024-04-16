// ========================================================================
// CSCI 3230 Data Structures
// Instructor: Yao Xu, Ph.D.
//
// Coding Assignment 5
//
// =========================== Requirements =============================== 
// Implement bubble sort and insertion sort to sort elements of an ArrayList
// for generic data types.
//
// Your output may look as follows:
// ------------------------------------------------------------------------
// Original list: [3, 5, 2, 4, 1, 8, 7, 6, 9]
// Apply bubble sort: [1, 2, 3, 4, 5, 6, 7, 8, 9]
// Apply insertion sort: [1, 2, 3, 4, 5, 6, 7, 8, 9]
// 
// Original list: [s, o, r, t, i, n, g]
// Apply bubble sort: [g, i, n, o, r, s, t]
// Apply insertion sort: [g, i, n, o, r, s, t]
//
// ============================== Note ====================================
//
// 1. DO NOT MODIFY OR DELETE ANY GIVEN CODE OR COMMENTS!!!
// 2. You ONLY need to write code under each comment "YOUR CODE GOES HERE".
// 3. Modify the file name to "Sorter.java" to compile and run.
//
// ========================================================================

import java.util.ArrayList;

public class Sorter<E extends Comparable<E>> {

	public static <E extends Comparable<E>> void bubbleSort(ArrayList<E> list) {
		// Sort the given ArrayList (of generic type E) into ascending order
		// YOUR CODE GOES HERE --Part 1/2--
		boolean swapped = true;

		while (swapped) {
			swapped = false;
			for (int i = 0; i < list.size() - 1; i++) {
				if (list.get(i).compareTo(list.get(i + 1)) > 0) {
					swapped = true;
					E temp = list.get(i);
					list.set(i, list.get(i + 1));
					list.set(i + 1, temp);
				}
			}
			if (!swapped) {
				break;
			}
		}

	}

	public static <E extends Comparable<E>> void insertionSort(ArrayList<E> list) {
		// Sort the given ArrayList (of generic type E) into ascending order
		// YOUR CODE GOES HERE --Part 2/2--
		for (int i = 1; i < list.size(); i++) {
			E key = list.get(i);
			int j = i - 1;

			while (j >= 0 && list.get(j).compareTo(key) > 0) {
				list.set(j + 1, list.get(j));
				j--;
			}
			list.set(j + 1, key);
		}

	}

	public static void main(String[] args) {
		int[] test1 = { 3, 5, 2, 4, 1, 8, 7, 6, 9 };
		char[] test2 = "sorting".toCharArray();

		// Sort test1 into ascending order
		ArrayList<Integer> bubbleSortInput1 = new ArrayList<Integer>();
		for (int e : test1)
			bubbleSortInput1.add(e);
		
		ArrayList<Integer> insertionSortInput1 = new ArrayList<Integer>(bubbleSortInput1);
		System.out.println("Original list: " + bubbleSortInput1);

		// Apply bubble sort
		bubbleSort(bubbleSortInput1);
		System.out.println("Apply bubble sort: " + bubbleSortInput1);
		// Apply insertion sort
		insertionSort(insertionSortInput1);
		System.out.println("Apply insertion sort: " + insertionSortInput1);
		System.out.println();

		// Sort test2 into ascending order
		ArrayList<Character> bubbleSortInput2 = new ArrayList<Character>();
		for (char e : test2)
			bubbleSortInput2.add(e);
		ArrayList<Character> insertionSortInput2 = new ArrayList<Character>(bubbleSortInput2);
		System.out.println("Original list: " + bubbleSortInput2);

		// Apply bubble sort
		bubbleSort(bubbleSortInput2);
		System.out.println("Apply bubble sort: " + bubbleSortInput2);
		// Apply insertion sort
		insertionSort(insertionSortInput2);
		System.out.println("Apply insertion sort: " + insertionSortInput2);

	}

}
