import java.util.*;
public class DSAGraphEdge
{
	private Object source;
	private Object dest;	
	private Object weight;
	
	
	//alternate constructor when weight is not involed 
	public DSAGraphEdge(Object source, Object dest)
	{
		this.source = source;
		this.dest = dest;
		this.weight = null;
	}
	//alternate constructor when weight involve
	public DSAGraphEdge(Object source, Object dest, Object weight)
	{
		this.source = source;
		this.dest = dest;
		this.weight = weight;
	}
	//getter
	public Object getSource()
	{
		return this.source;
		
	}
	public Object getDest()
	{
		return this.dest;		
	}
	
	public Object getWeight()
	{
		return this.weight;
	}	
	
	//mutators
	public void setSource(Object source)
	{
		this.source = source;
	}
	
	public void setDest(Object dest)
	{
		this.dest = dest;
	}
	public void setWeight(Object weight)
	{
		this.weight = weight;
	}
	
	
	
	
	
	public String toString()
	{
		String result = "";
		result = "{"+ String.valueOf(this.source) + "," + String.valueOf(this.dest) + "}";
		return result;
	}
	
	
}