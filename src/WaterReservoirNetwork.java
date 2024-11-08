import com.jwetherell.algorithms.data_structures.*;
import java.util.*;
public class WaterReservoirNetwork<T> {
	
	//Vertices and Edges
	private List<T> vertices=new ArrayList<>();//to store the vertices
	private List<List<Edge>> edges= new ArrayList<>(); //to store the list edges for each vertex 
	
	//constructor for an empty graph
	WaterReservoirNetwork()
	{
		//nothing goes in here
	}
	
	//constructor with vertices and edges in arrays
	WaterReservoirNetwork(T[] vertices,int[][] edges)
	{
		for(int i=0;i<vertices.length;i++)
		{
			addVertex(vertices[i]);
		}
		createAdjacencyLists(edges,vertices.length);
	}
	
	//construct a graph from vertices and edges stored in list
	WaterReservoirNetwork(List<T> vertices,List<List<Edge>> e)
	{
		this.vertices=vertices;
		this.edges=e;
		
	}
	
	@SuppressWarnings("unchecked")
	//construct the graph for vertices 1, 2, 3 ... and edge list
	WaterReservoirNetwork(List<Edge> edges,int numberOfVertices)
	{
		for(int i=0;i<numberOfVertices;++i)
		{
			addVertex((T)(new Integer(i)));//vertices 1 ,2,3 ...
		}
		createAdjacencyLists(edges,numberOfVertices);
	}
	
	@SuppressWarnings("unchecked")
	//construct a graph for vertices 1, 2, 3 ... and array
	WaterReservoirNetwork(int[][] edges,int numberOfVertices)
	{
		for(int i=0;i<numberOfVertices;++i)
		{
			addVertex((T)(new Integer(i))); //vertices 1,2 ,3 ...
		}
		createAdjacencyLists(edges,numberOfVertices);
	}
	
	//create adjacency lists for each vertex
	private void createAdjacencyLists(int[][] edges,int numberOfVertices)
	{
		for(int i=0;i<edges.length;++i)
		{
			addEdge(edges[i][0],edges[i][1]);
		}
	}
	
	//create adjacency lists for each vertex
	private void createAdjacencyLists(List<Edge> edges,int numberOfVertices)
	{
		for(Edge edge:edges)
		{
			addEdge(edge.u,edge.v);
		}
	}
	
	public int getSize() {
		// TODO Auto-generated method stub
		return vertices.size();
	}

	public List<T> getVertices() {
		// TODO Auto-generated method stub
		return vertices;
	}
	public List<List<Edge>> getEdges()
	{
		return edges;
	}
	public T getVertex(int index) {
		// TODO Auto-generated method stub
		if(index<0 || index>vertices.size()-1)
		{
			return null;
		}else
		{
			return vertices.get(index);
		}
	}
	public Edge getEdge(int i,int j)
	{
		return edges.get(i).get(j);//needs to be fixed
	}

	public int getIndex(T v) {
		// TODO Auto-generated method stub
		return vertices.indexOf(v);
	}

	public List<Integer> getNeighbors(int index) {
		// TODO Auto-generated method stub
		List<Integer> result=new ArrayList<>();
		for(Edge e: edges.get(index))
		{
			if(index!=e.v)
			{
				result.add(e.v);
			}	
		}
		return result;
	}

	public int getDegree(int v) {
		// TODO Auto-generated method stub
		return edges.get(v).size();
	}

	public void clear() {
		// TODO Auto-generated method stub
		vertices.clear();
		edges.clear();
	}

	public boolean addVertex(T vertex) {
		// TODO Auto-generated method stub
		if(!vertices.contains(vertex))
		{
			vertices.add(vertex);
			edges.add(new ArrayList<Edge>());
			edges.get(getIndex(vertex)).add(new Edge(getIndex(vertex),getIndex(vertex)));
			return true;
		}else
		{
			return false;
		}
	}

	public boolean addEdge(int u, int v) {
		// TODO Auto-generated method stub
		return addEdge(new Edge(u,v));
	}
	public boolean addEdge(Edge e) {
		// TODO Auto-generated method stub
		if(e.u<0 || e.u>vertices.size()-1)
		{
			return false;
		}
		if(e.v<0 || e.v>vertices.size()-1)
		{
			return false;
		}
		if(!edges.get(e.u).contains(e) && e.u!=e.v)
		{
			edges.get(e.u).add(e);
			return true;
		}else
		{
			return false;
		}
	}

	public boolean removeVertex(T v) {
		// TODO Auto-generated method stub
		//we first remove the edges associated with vertex v
		if(!vertices.isEmpty() && vertices.contains(v))
		{
			for(int i=0;i<edges.size();++i)
			{
				for(int j=0;j<edges.get(i).size();++j)
				{
					if(edges.get(i).get(j).u==getIndex(v) || edges.get(i).get(j).v==getIndex(v))
					{
						edges.get(i).remove(edges.get(i).get(j));
					}
				}
			}
			edges.remove(edges.get(getIndex(v)));
			if(getIndex(v)<vertices.size()-1)
			{
				int s=getIndex(v);
				for(int i=s;i<edges.size();++i)
				{
					for(int j=0;j<edges.get(i).size();++j)
					{
						
						if(edges.get(i).get(j).u==edges.get(i).get(j).v)
						{
							edges.get(i).get(j).u-=1;
							edges.get(i).get(j).v-=1;
						}else
						{
							edges.get(i).get(j).u-=1;
						}
					}
				}
			}
			//now we are removing the vertex itself
			vertices.remove(v);
			
			
			
			return true;
		}else
		{
			return false;
		}
			
			
		
	}

	public boolean removeEdge(int u, int v) {
		// TODO Auto-generated method stub
		Edge e=new Edge(u,v);
		if(u<0 || u>vertices.size()-1)
		{
			return false;
		}
		if(v<0 || v>vertices.size()-1)
		{
			return false;
		}
		if(edges.get(u).contains(e) && u!=v)
		{
			edges.get(u).remove(e);
			return true;
		}else
		{
			return false;
		}
	}

	public boolean removeEdge(Edge e) {
		// TODO Auto-generated method stub
		if(e.u<0 || e.u>vertices.size()-1)
		{
			return false;
		}
		if(e.v<0 || e.v>vertices.size()-1)
		{
			return false;
		}
		if(edges.get(e.u).contains(e))
		{
			edges.get(e.u).remove(e);
			return true;
		}else
		{
			return false;
		}
	}
	//Tree inner class inside WaterReservoir
	public class SearchTree
	{
		private int root;
		private int[] parent;
		private List<Integer> searchOrder;
		//constructor
		SearchTree(int root,int[] parent,List<Integer> searchOrder)
		{
			this.root=root;
			this.parent=parent;
			this.searchOrder=searchOrder;
		}
		
		public int getRoot()
		{
			return root;
		}
		public int getParent(int v)
		{
			return parent[v];
		}
		public List<Integer> getSearchOrder()
		{
			return searchOrder;
		}
		public int getNumberOfVerticesFound()
		{
			return searchOrder.size();
		}
		public List<T> getPath(int index)
		{
			ArrayList<T> path=new ArrayList<>();
			do
			{
				path.add(vertices.get(index));
			}while(index!=-1);
			return path;
		}
	}
	public SearchTree dfs(int v) {
		// TODO Auto-generated method stub
		List<Integer> searchOrder=new ArrayList<>();
		int[] parent =new int[vertices.size()];
		for(int i=0;i<parent.length;++i)
		{
			parent[i]=-1;
		}
		
		boolean[] isVisited=new boolean[vertices.size()];
		
		//recursively searching 
		dfs(v,parent,searchOrder,isVisited);
		return new SearchTree(v,parent,searchOrder);
	}
	private void dfs(int v,int[] parent,List<Integer> searchOrder,boolean[] isVisited)
	{
		searchOrder.add(v);
		isVisited[v]=true;
		for(Edge e:edges.get(v))
		{
			if(!isVisited[e.v])
			{
				parent[e.v]=v;
				dfs(e.v,parent,searchOrder,isVisited);
			}
		}
	}
	public SearchTree bfs(int v) {
		// TODO Auto-generated method stub
		List<Integer> searchOrder=new ArrayList<>();
		int[] parent=new int[vertices.size()];
		for(int i=0;i<parent.length;++i)
		{
			parent[i]=-1; //initialize parent[i] to -1
		}
		LinkedList<Integer> queue= new LinkedList<>();
		boolean[] isVisited=new boolean[vertices.size()];
		queue.offer(v);
		isVisited[v]=true;
		while(!queue.isEmpty())
		{
			int u=queue.poll();
			searchOrder.add(u);
			for(Edge e:edges.get(u))
			{
				if(!isVisited[e.v])
				{
					queue.offer(v);
					parent[e.v]=u;
					isVisited[e.v]=true;
				}
			}
		}
		return new SearchTree(v,parent,searchOrder);
	}

}

