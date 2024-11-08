import com.jwetherell.algorithms.data_structures.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Utilities<T>
{
	public static void out(String s)
	{
		System.out.println(s);
	}
	public static void out(int i)
	{
		System.out.println(i);
	}
	public static void out(double d)
	{
		System.out.println(d);
	}
	private static List<List<Edge>> readEdges() 
	{
		//file format that is readable : u,v*u,v*u,v . . .
		List<List<Edge>> result=new ArrayList<>();
		
		try 
		{
			Scanner sc = new Scanner(new File("data/Edges.txt"));
			String line1="";
			
			int counter=0;
			while(sc.hasNext())
			{
				line1=sc.nextLine();
				StringTokenizer st1=new StringTokenizer(line1,"|");
				result.add(new ArrayList<Edge>());
				while(st1.hasMoreTokens())
				{
					String line2=st1.nextToken();
					StringTokenizer st2=new StringTokenizer(line2,",");
					String u=st2.nextToken();
					String v=st2.nextToken();
					Edge e=new Edge(Integer.parseInt(u),Integer.parseInt(v));
					result.get(counter).add(e);
				}
				++counter;
			}
			sc.close();
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}
	private static List<WaterReservoir> readReservoirs()
	{
		//format for reservoir text file is : name|region|radius|height|volume
		List<WaterReservoir> result=new ArrayList<>();
		String line="";
		StringTokenizer st=null;
		try {
			Scanner sc=new Scanner(new File("data/Reservoirs.txt"));
			while(sc.hasNext())
			{
				line=sc.nextLine();
				st=new StringTokenizer(line,"|");
				String name=st.nextToken();
				String region=st.nextToken();
				String radius=st.nextToken();
				String height=st.nextToken();
				String waterVolume=st.nextToken();
				result.add(new WaterReservoir(name,region,Double.parseDouble(radius),Double.parseDouble(height),Double.parseDouble(waterVolume)));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	private static void writeEdges(List<List<Edge>> e)
	{
		
			
			for(int i=0;i<e.size();++i)
			{
				if(e.get(i).isEmpty())
				{
					return;
				}
				
			}

			try {
				PrintWriter pw=new PrintWriter(new File("data/Edges.txt"));
				
				for(int i=0;i<e.size();++i)
				{
					if(e.get(i).size()>1)
					{
						for(int j=0;j<e.get(i).size()-1;++j)
						{
							pw.append(e.get(i).get(j).u+","+e.get(i).get(j).v+"|");
						}
						pw.append(e.get(i).get(e.get(i).size()-1).u+","+e.get(i).get(e.get(i).size()-1).v+"\n");
					}else
					{
						pw.append(e.get(i).get(0).u+","+e.get(i).get(0).v+"\n");;
					}
						
					
				}
				pw.flush();
				pw.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		
	}
	private static void writeReservoirs(List<WaterReservoir> v)
	{
		
			try {
				PrintWriter pw=new PrintWriter(new File("data/Reservoirs.txt"));
				for(int i=0;i<v.size();++i)
				{
					pw.append(v.get(i).getName()+"|"+v.get(i).getRegion()+"|"+v.get(i).getBaseRadius()+"|"+v.get(i).getHeight()+"|"+v.get(i).getWaterVolume()+"\n");
				}
						
				pw.flush();
				pw.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	public static WaterReservoirNetwork<WaterReservoir> loadGraph()
	{
		List<WaterReservoir> v=Utilities.readReservoirs();
		List<List<Edge>> e=Utilities.readEdges();
		
		return new WaterReservoirNetwork<WaterReservoir>(v,e);
	}
	public static void storeGraph(Graph<WaterReservoir> g)
	{
		writeReservoirs(g.getVertices());
		writeEdges(g.getEdges());
	}
	public static String getGraphString(Graph<WaterReservoir> g)
	{
		DecimalFormat df=new DecimalFormat("##0.0");
		String s="";
		for(int i=0;i<g.getVertices().size();++i)
		{
			s=s+(i+1)+". "+g.getVertex(i).getName()+"---"+g.getVertex(i).getRegion()+"---"+df.format(g.getVertex(i).getPercentageFull())+"% Full "+"\n";
		}
		return s;
		
	}
	public static String getString(int index,Graph<WaterReservoir> g)
	{
		List<Integer> intV=g.getNeighbors(index);
		String result="";
		DecimalFormat df=new DecimalFormat("##0.0");
		for(int i=0;i<intV.size();++i)
		{
			result=result+(i+1)+". "+g.getVertex(intV.get(i)).getName()+"---"+g.getVertex(intV.get(i)).getRegion()+"---"+df.format(g.getVertex(intV.get(i)).getPercentageFull())+"% Full "+"\n";
		}
		return result;
	}
	public static String getAboveHalfString(Graph<WaterReservoir> g)
	{
		String result="";
		DecimalFormat df=new DecimalFormat("##0.0");
		for(int i=0;i<g.getVertices().size();++i)
		{
			if(g.Vertex(i).getPercentageFull()>=50.00)
			{
				result=result+(i+1)+". "+g.Vertex(i).getName()+"---"+g.Vertex(i).getRegion()+"---"+df.format(g.Vertex(i).getPercentageFull())+"% Full "+"\n";
			}
		}
		return result;
	}
	
}

