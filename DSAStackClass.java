import java.util.*;

public class DSAStackClass implements Iterable
{	
	private int iCount;
	//private Object[] oStackArray = null;	
	private DSALinkedList oStackArray; // use linkedlist t ocreate the stack					
	int popVal;
	//stack method to get iterator from linkedlist
	public Iterator iterator()
	{
		return oStackArray.iterator();
	}
	
	//default constructor
	public DSAStackClass()
	{
		iCount = 0;
		oStackArray = new DSALinkedList();	
		popVal= 0;
	}	
	
	//get count of the added element
	public int getCount()
	{		
		return iCount;
		
	}//end getCount()
	public int getPopVal()
	{
		return popVal;
	}
		
	public boolean isEmpty()
	{
		boolean isEmpty = true;
		if (oStackArray.isEmpty() == false)
		{
			isEmpty = false;
		}
		return isEmpty;
		
	}
	// add the element to stack
	public void push(Object oValue)
	{
		if (oStackArray.isEmpty() == true)
		{
			iCount++;
			oStackArray.insertFirst(oValue);
		}
		else 
		{
			iCount ++;	
			//System.out.println("icount 1: "+ iCount);
			oStackArray.insertFirst(oValue);			
		}		
	}//end push()
	
	// pop a number out of stack
	public Object pop()
	{	
		Object popVal= null;
		if (oStackArray.isEmpty()== true)
		{
			System.out.println("");	
		}
		else
		{
			popVal = oStackArray.removeFirst();			
		//	System.out.println("i coun 2 : " + getCount());
			iCount--;
		}		
		return popVal;
	}// end pop()
	
	
	//determine the top valueOf
	public Object top()
	{		
		Object topVal = null;		
		topVal = oStackArray.peekFirst();		
		return topVal;
	}
	
	public String toString()
	{		
		String  a = "";		
		//int i =0;
		if (oStackArray.isEmpty()== true)
		{
			System.out.println("");			
		}
		else
		{			
			a = oStackArray.toString();						
		}
		return a;		
	}//end toShow()
	
	// print element into screen
	public void show()
	{		
		//int i =0;
		if (oStackArray.isEmpty()== true)
		{
			System.out.println("");			
		}
		else
		{			
			oStackArray.show();
		}		
	}//end toShow()
	
}