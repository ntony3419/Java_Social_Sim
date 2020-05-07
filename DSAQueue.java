import java.util.*;

public class DSAQueue implements Iterable
{
	
	private DSALinkedList oQueue = null;		
	private int count ;
	
	public Iterator iterator()
	{
		return oQueue.iterator();
	}
	
	public DSAQueue()
	{
		oQueue = new DSALinkedList();
		count = 0;
	}	
	
	//get queue
	public DSALinkedList getQueue()
	{
		return oQueue;
	}//end getQueue()		
	
	public int getCount()
	{
		return count;
	}
	
	public int increaseCount()
	{
		return count++;
	}
	
	public int decreaseCount()
	{
		return count--;
	}
	
	public boolean isEmpty()
	{
		boolean isEmpty = true;
		isEmpty = oQueue.isEmpty();
		return isEmpty;
	}
			

	
	//add item in the last
	public void enqueue(Object oInValue)
	{	
		oQueue.insertLast(oInValue);
		count++;
		
	}// end enqueue
	
	
	//remove first in item
	public Object dequeue()
	{		
		Object delVal=null;		
		if ( oQueue.isEmpty() == true )
		{
			System.out.println("");
		}
		else
		{	
			delVal = oQueue.removeFirst();
		}
		return delVal;
	}//end dequeue()
	
	//check the front item
	public Object peek()
	{
		Object peekVal = null;
		if (oQueue.isEmpty() == true)
		{
			System.out.println("");			
		}
		else 
		{
			peekVal = oQueue.peekFirst();
		}
		return peekVal;
	}
	
	public Object peekTail()
	{
		Object peekTail = null; 
		if (oQueue.isEmpty() == true)
		{
			System.out.println("");			
		}
		else 
		{
			peekTail = oQueue.peekLast();
		}
		return peekTail;
	}
	
	
	public String toString()
	{		
		String a="";
			
		if ( oQueue.isEmpty() == true)
		{
			System.out.println("");			
		}
		else 
		{			
			a= oQueue.toString();
		}// end else
		return a;
	
	}
	
	public String toStringGraphValue()
	{		
		String a="";
			
		if ( oQueue.isEmpty() == true)
		{
			System.out.println("");			
		}
		else 
		{			
			a= ((DSALinkedList) oQueue).toStringGraphValue();
		}// end else
		return a;
	
	}
	
	//print the queue	
	public void show()
	{		
		if ( oQueue.isEmpty() == true )
		{
			System.out.println("");			
		}
		else 
		{			
			oQueue.show();
		}// end else
	
	
	}
	
	
	
}