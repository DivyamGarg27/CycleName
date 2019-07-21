import java.util.*;
import java.io.*;
import java.lang.*; 
  
public class CycleName 
{ 
    static class Graph 
    { 
        int V; 
        LinkedList<Integer> adjListArray[]; 

        Graph(int V) 
        { 
            this.V = V; 

            adjListArray = new LinkedList[V]; 

            for(int i = 0; i < V ; i++){ 
                adjListArray[i] = new LinkedList<>(); 
            } 
        } 
    } 
      
    // Adds an edge to an undirected graph 
    static void addEdge(Graph graph, int src, int dest) 
    { 
        // Add an edge from src to dest.  
        graph.adjListArray[src].add(dest); 
        // Add an edge from dest to src.
        graph.adjListArray[dest].add(src); 
    } 
	static void removeEdge(Graph g,int v)
    {
        int w = g.adjListArray[v].poll();
        g.adjListArray[w].remove(g.adjListArray[w].get(g.adjListArray[w].indexOf(v)));
    }

    static void printifcycle(Graph g)
    {
        LinkedList<Integer> lt = new LinkedList<Integer>();
        int visited=0;
        int temp=1;
    	int len = g.adjListArray.length;
	for(int i=0;i<g.adjListArray.length;i++)
	{
		if(!lt.contains(i))
		{
			lt.add(i);
		        visited++;
		}
	}
	System.out.println(lt);
        while(true)
        {
            for(int k=0;k<lt.size();k++)
            {
                int x = lt.get(k);
            	if(g.adjListArray[x].size()==1)
		{
			removeEdge(g,x);
                	lt.remove(lt.get(k));
                	System.out.println(lt);
			temp=0;
		        break;
       	        }
	     }
		
	     for(int h=0;h<lt.size();h++)
	     {
	     	   int x1 = lt.get(h);
	           if(g.adjListArray[x1].size()==1)
		   temp=1;
	     }
	        
	     if(visited==len && temp==0)
	     break;
        }
        
        if(lt.isEmpty())
        {
            System.out.println("Not cyclic");
        }
        else
        {
            System.out.println("Cyclic with following elements forming the cycle");
            for(int u:lt)
            {
                System.out.print(u+" ");
            }
        }
    }
       
    // Print the adjacency list representation of graph 
    static void printGraph(Graph graph) 
    {        
        for(int v = 0; v < graph.V; v++) 
        { 
            System.out.println("Adjacency list of vertex "+ v); 
            System.out.print("head"); 
            for(Integer p: graph.adjListArray[v]){ 
                System.out.print(" -> "+p); 
            } 
            System.out.println("\n"); 
        } 
    } 
       
    // Driver program to test above functions 
    public static void main(String args[]) 
    { 
        Scanner s = new Scanner(System.in);
	System.out.print("Enter number of nodes : ");
        int V = s.nextInt(); 
        Graph graph = new Graph(V); 
	System.out.print("Enter number of edges : ");
	int n = s.nextInt();
	System.out.println("Enter all pairs of two nodes forming an edge");
	for(int i=0;i<n;i++)
	{
		int a = s.nextInt();
		int b = s.nextInt();
		addEdge(graph, a, b); 
        }      
        printGraph(graph); 
	Graph g = graph;
	printifcycle(g);
    } 
} 
