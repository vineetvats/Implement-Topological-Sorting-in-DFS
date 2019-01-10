package ypp170130;

/**
 *     Team No: 33
 *     @author Vineet Vats: vxv180008
 *     @author Yash Pradhan: ypp170130
 *     Short Project 8: Implementing DFS based Topological Ordering algorithm
 */

import rbk.Graph.Vertex;
import rbk.Graph.Edge;
import rbk.Graph.GraphAlgorithm;
import rbk.Graph.Factory;
import rbk.Graph.Timer;
import rbk.Graph;

import java.io.File;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class DFS extends GraphAlgorithm<DFS.DFSVertex> {

    private static LinkedList<Vertex> finishList;
    private static boolean isCyclic;
    private static int topnum;

    public static class DFSVertex implements Factory {
        int cno;
        boolean seen;
        Vertex parent;
        boolean isInRecursionStack; //used for detecting cycles
        int top; //stores the topological order number

        public DFSVertex(Vertex u) {
            seen = false;
            parent = null;
            isInRecursionStack = false;
        }
        public DFSVertex make(Vertex u) { return new DFSVertex(u); }
    }

    public DFS(Graph g) {
        super(g, new DFSVertex(null));
    }

    /**
     * helper method used by dfs() to visit all reachable nodes from vertex u
     * @param u
     */
    private void dfsVisit(Vertex u){
        get(u).seen = true;
        get(u).isInRecursionStack = true;

        for(Edge e: g.incident(u)){
            Vertex v = e.otherEnd(u);

            if(!isCyclic){
                if(get(v).isInRecursionStack){
                    isCyclic = true;
                }
            }

            if(!get(v).seen){
                get(v).parent = u;
                dfsVisit(v);
            }
        }

        get(u).isInRecursionStack = false;
        get(u).top = topnum--;
        finishList.addFirst(u);
    }

    /**
     * helper dfs(), invoked by other member functions
     */
    private void dfs(){
        finishList = new LinkedList<>();
        isCyclic = false;
        topnum = g.size();

        for(Vertex u: g){
            get(u).seen = false;
            get(u).parent = null;
            get(u).isInRecursionStack = false;
        }

        for(Vertex u: g){
            if(!get(u).seen){
                dfsVisit(u);
            }
        }
    }

    public static DFS depthFirstSearch(Graph g) {
        DFS d = new DFS(g);
        d.dfs();
        return d;
    }

    // Member function to find topological

    /**
     * Checks that graph is DAG, returns topological ordering of vertices, null otherwise
     * @return topological ordering of vertices if g is DAG, null otherwise
     */
    public List<Vertex> topologicalOrder1() {

        if(!g.isDirected())
            return null;

        this.dfs();

        if(this.isCyclic)
            return null;

        return this.finishList;
    }

    // After running the topological ordering algorithm, the topological order no of each vertex can be queried
    public int topnum(Vertex u) {
        return get(u).top;
    }

    // Find the number of connected components of the graph g by running dfs.
    // Enter the component number of each vertex u in u.cno.
    // Note that the graph g is available as a class field via GraphAlgorithm.
    public int connectedComponents() {
        return 0;
    }

    // After running the connected components algorithm, the component no of each vertex can be queried.
    public int cno(Vertex u) {
        return get(u).cno;
    }

    // Find topological order of a DAG using DFS. Returns null if g is not a DAG.
    public static List<Vertex> topologicalOrder1(Graph g) {
        DFS d = new DFS(g);
        return d.topologicalOrder1();
    }

    // Find topological oder of a DAG using the second algorithm. Returns null if g is not a DAG.
    public static List<Vertex> topologicalOrder2(Graph g) {
        return null;
    }

    public static void main(String[] args) throws Exception {
        // String string = "7 8   1 2 2   1 3 3   2 4 5   3 4 4   4 5 1   5 1 7   6 7 1   7 6 1 0";
        String string = "10 12  1 3 1  3 2 1  2 4 1  4 7 1  1 8 1  8 5 1  8 2 1  5 4 1  6 8 1  6 10 1  5 10 1  10 9 1 0";

        Scanner in;
        // If there is a command line argument, use it as file from which
        // input is read, otherwise use input from string.
        in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(string);

        // Read graph from input
        Graph g = Graph.readDirectedGraph(in);
        g.printGraph(false);

        DFS d = new DFS(g);
        List<Vertex> topologicalOrderList = d.topologicalOrder1();

        if(topologicalOrderList!=null) {
            System.out.print("\nTopological Ordering: ");
            for (Vertex v : topologicalOrderList) {
                System.out.print(v+" ");
            }
        }
        else{
            System.out.print("\nGraph is not a DAG");
        }
    }
}