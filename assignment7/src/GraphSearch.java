// ========================================================================
// CSCI 3230 Data Structures
// Instructor: Yao Xu, Ph.D.
//
// Coding Assignment 7
//
// =========================== Requirements =============================== 
// Implement the BFS and DFS traversal methods for directed graphs.  
// For BFS, implement it to find the distance (shortest path length) from  
// a source vertex s to every vertex in the graph.
// 
// You will need to complete the method bodies of:
// 		void bfs(Vertex<L> s)
// 		void dfs(Vertex<L> s)
// 		void dfsVisit(Vertex<L> current)
// 
// The BFS class is coded as a subclass of the MyGraph class and is also 
// using the MyLinkedQueue class, which is a modified version of the 
// LinkedQueue class from Module 3. These two classes are provided in the  
// "MyGraph.java" and "MyLinkedQueue.java" files. Please place them under 
// the same package/folder.
//
// Your output may look as follows:
// ------------------------------------------------------------------------
// DFS traversal starting from vertex 3: 3 4 0 6 5 7 8 9 1 2 
// BFS traversal starting from vertex 3: 3 4 6 0 5 9 7 8 1 2 
// Shortest path lengths from 3 to every vertex:
// Vertices:  0 1 2 3 4 5 6 7 8 9 
// Distances: 2 3 4 0 1 2 1 3 3 2 
//
// ============================== Note ====================================
//
// 1. DO NOT MODIFY OR DELETE ANY GIVEN CODE OR COMMENTS!!!
// 2. You ONLY need to write code under each comment "YOUR CODE GOES HERE".
// 3. Modify the file name to "GraphSearch.java" to compile and run.
// 4. Make sure that you place the "MyGraph.java" and "MyLinkedQueue.java"  
//    files under the same package/folder as your current file to compile
//    and run the code.
//
// ========================================================================

import java.util.ArrayList;
import java.util.LinkedList;

//---------------- Vertex class ----------------
class Vertex<L> {
	L label;
	int color; // 0 = GRAY, 1 = RED, 2 = BLACK
	int distance;

	public Vertex(L label) {
		this.label = label;
		this.color = 0; // GRAY
		this.distance = Integer.MAX_VALUE;
	}
} // ------------ End of Vertex class ------------

public class GraphSearch<L> extends MyGraph<Vertex<L>> {

	// Breadth-First Search to find shortest path lengths from source vertex s to
	// every vertex
	public void bfs(Vertex<L> s) {
		if (!vertices.contains(s))
			throw new IllegalArgumentException("Source vertex " + s.label + " not found!");

		MyLinkedQueue<Vertex<L>> queue = new MyLinkedQueue<>();

		// YOUR CODE GOES HERE --Part 1/3--

		for (Vertex<L> vertex : vertices) {
			vertex.color = 0;
		}

		queue.enqueue(s);
		s.color = 1;
		s.distance = 0;

		while (!queue.isEmpty()) {
			Vertex<L> currentVertex = queue.dequeue();
			LinkedList<Vertex<L>> adjList = adjLists.get(vertices.indexOf(currentVertex));
			for (Vertex<L> neighbor : adjList) {
				if (neighbor.color == 0) {
					neighbor.color = 1;
					neighbor.distance = currentVertex.distance + 1;
					queue.enqueue(neighbor);
				}

			}

			currentVertex.color = 2;
			System.out.print(currentVertex.label + " ");

		}
	}

	// Depth-First Search starting from vertex s
	public void dfs(Vertex<L> s) {
		if (!vertices.contains(s))
			throw new IllegalArgumentException("Source vertex " + s.label + " not found!");

		// YOUR CODE GOES HERE --Part 2/3--
		for (Vertex<L> vertex : vertices) {
			vertex.color = 0;
		}

		s.color = 1;
		dfsVisit(s);
	}

	private void dfsVisit(Vertex<L> current) {
		// YOUR CODE GOES HERE --Part 3/3--

		current.color = 1;
		System.out.print(current.label + " ");
		LinkedList<Vertex<L>> adjList = adjLists.get(vertices.indexOf(current));
		for (Vertex<L> neighbor : adjList) {
			if (neighbor.color == 0) {
				dfsVisit(neighbor);
			}
		}
		current.color = 2;

	}

	public ArrayList<Vertex<L>> getVertices() {
		return vertices;
	}

	// ---------------- Driver ----------------
	public static void main(String[] args) {
		// Create a graph
		GraphSearch<Integer> graph = new GraphSearch<>();

		// Add ten vertices with integer labels
		Vertex<Integer>[] vertices = new Vertex[10];
		for (int i = 0; i < 10; i++) {
			vertices[i] = new Vertex<>(i);
			graph.addVertex(vertices[i]);
		}

		// Define 18 edges
		int[][] edges = { { 1, 2 }, { 1, 3 }, { 1, 5 }, { 2, 4 }, { 2, 5 }, { 2, 7 }, { 3, 4 }, { 3, 6 }, { 4, 0 },
				{ 5, 7 }, { 5, 8 }, { 6, 5 }, { 6, 9 }, { 7, 0 }, { 8, 9 }, { 8, 0 }, { 9, 0 }, { 9, 1 } };

		// Add the edges
		for (int[] edge : edges) {
			int u = edge[0];
			int v = edge[1];
			graph.addEdge(vertices[u], vertices[v]);
		}

		int s = 3;
		// Perform DFS from vertex 3
		// Print out the order of vertices being first visited by DFS
		System.out.print("DFS traversal starting from vertex " + vertices[s].label + ": ");
		graph.dfs(vertices[s]);
		System.out.println();

		// Perform BFS from vertex 3
		// Print out the order of vertices being first visited by BFS
		System.out.print("BFS traversal starting from vertex " + vertices[s].label + ": ");
		graph.bfs(vertices[s]);
		System.out.println();

		// Print the distance/shortest path lengths from vertex 3 to other vertices
		System.out.println("Shortest path lengths from " + s + " to every vertex:");
		System.out.print("Vertices:  ");
		for (Vertex<Integer> vertex : graph.getVertices())
			System.out.print(vertex.label + " ");
		System.out.println();
		System.out.print("Distances: ");
		for (Vertex<Integer> vertex : graph.getVertices())
			System.out.print(vertex.distance + " ");

	}

}
