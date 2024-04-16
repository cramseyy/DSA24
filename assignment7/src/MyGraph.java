// ===============================================
// CSCI 3230 Data Structures
// Instructor: Yao Xu, Ph.D.
// 
// M10 - Graphs
// 		 An implementation of graph using 
//       adjacency lists representation
// 
// ===============================================

import java.util.ArrayList;
import java.util.LinkedList;

public class MyGraph<V> {

    protected ArrayList<V> vertices;
    protected ArrayList<LinkedList<V>> adjLists;

    // Constructor
    public MyGraph() {
        vertices = new ArrayList<>();
        adjLists = new ArrayList<>();
    }

    // Returns the number of vertices in the graph
    public int numVertices() {
        return vertices.size();
    }

    // Returns the number of edges in the graph
    public int numEdges() {
        int count = 0;
        // number of edges = total length of the adj lists
        for (LinkedList<V> adjList : adjLists)
        	count += adjList.size();
        
        return count; // for digraphs
        // return count / 2; // divide by 2 for undirected edges
    }

    // Checks if the graph is empty (has no vertices)
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    // Checks if there is an edge (u, v) in the graph
    public boolean hasEdge(V u, V v) {
        int indexU = vertices.indexOf(u);
        int indexV = vertices.indexOf(v);
        if (indexU != -1 && indexV != -1) {
            // check if v is in the adj list of u
        	LinkedList<V> adjList = adjLists.get(indexU);
            return adjList.contains(v);
        }
        return false;
    }

    // Adds a vertex v to the graph
    public void addVertex(V v) {
        if (vertices.contains(v))
            throw new IllegalArgumentException("Vertex already exists: " + v);
        // add v to vertices and adjLists
        vertices.add(v);
        adjLists.add(new LinkedList<>());
    }

    // Adds an edge (u, v) to the graph
    public void addEdge(V u, V v) {
        int indexU = vertices.indexOf(u);
        int indexV = vertices.indexOf(v);
        if (indexU == -1)
            throw new IllegalArgumentException("Vertex not found: " + u);
        if (indexV == -1)
            throw new IllegalArgumentException("Vertex not found: " + v);

        // add edge (u, v)
        LinkedList<V> adjListU = adjLists.get(indexU);
        adjListU.addLast(v);
        
        // add edge (v, u) for undirected graph:
        // LinkedList<V> adjListV = adjLists.get(indexV);
        // adjListV.addLast(u);
        
    }

    // Removes a vertex v from the graph
    // Also removes all edges associated with vertex v
    public void removeVertex(V v) {
        int indexV = vertices.indexOf(v);
        if (indexV == -1)
            throw new IllegalArgumentException("Vertex not found: " + v);

        // remove vertex v
        vertices.remove(indexV);
        adjLists.remove(indexV);
        // remove all edges associated with vertex v
        for (LinkedList<V> list : adjLists)
            list.remove(v);       
    }

    // Removes the edge (u, v) from the graph
    public void removeEdge(V u, V v) {
        int indexU = vertices.indexOf(u);
        int indexV = vertices.indexOf(v);
        if (indexU == -1)
            throw new IllegalArgumentException("Vertex not found: " + u);
        if (indexV == -1)
            throw new IllegalArgumentException("Vertex not found: " + v);
        
        // remove edge (u, v)
        LinkedList<V> adjListU = adjLists.get(indexU);
        adjListU.remove(v);
        // remove edge (v, u) for undirected graph:
        // LinkedList<V> adjListV = adjLists.get(indexV);
        // adjListV.remove(u);       
    }
    
    
    // ------------------------- Driver -------------------------
    public static void main(String[] args) {
        MyGraph<Character> graph = new MyGraph<>();

        // Adding vertices
        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');

        // Adding edges
        graph.addEdge('A', 'B'); // removed
        graph.addEdge('A', 'C');
        graph.addEdge('B', 'D'); // removed
        graph.addEdge('C', 'D'); // removed
        graph.addEdge('D', 'E');

        // Number of vertices and edges in the graph
        System.out.println("Number of vertices: " + graph.numVertices());
        System.out.println("Number of edges: " + graph.numEdges());
        System.out.println();
        
        System.out.println("Does the graph have an edge (A, B)? " + (graph.hasEdge('A', 'B') ? "Yes" : "No"));
        System.out.println("Does the graph have an edge (B, C)? " + (graph.hasEdge('B', 'C') ? "Yes" : "No"));
        System.out.println();

        // Removing an edge
        System.out.println("Removing edge (C, D) ...");
        graph.removeEdge('C', 'D');
        System.out.println("Number of edges left: " + graph.numEdges());
        System.out.println();
        
        // Removing a vertex
        System.out.println("Removing vertex 'B' ...");
        graph.removeVertex('B');
        System.out.println("Number of vertices left: " + graph.numVertices());
        System.out.println("Number of edges left: " + graph.numEdges());
        
    }
}
