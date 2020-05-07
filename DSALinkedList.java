import java.util.*;
import java.io.*;

public class DSALinkedList implements Serializable, Iterable
{	
	private class IteratorHelper implements Iterator
	{
		DSAListNode idxNode;
		//constructor for the iterator
		public IteratorHelper(DSALinkedList inList) // this will make the idxNode of iterator at location linkedLít's head 
		{
			idxNode = inList.head;
		}
		//check if the node at pointed location is empty
		public boolean hasNext()
		{
			boolean hasNext = true;
			if (idxNode == null)
			{
				hasNext = false;
			}
			return hasNext;
		}
		public Object next()
		{
			Object value = null;
			if (hasNext() != true)
			{
				throw new NoSuchElementException();
			}
			else 
			{
				value = idxNode.value;
				idxNode = idxNode.nextNode;
			}
			return value;
		}
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}			
	
	// this inner class can only be used in DSALinkedList
	public class DSAListNode implements Serializable//create nodes
	{
			public Object value;					// only usable inside DSALinkedList
			public DSAListNode nextNode;
			public DSAListNode prevNode;
			
			//alternate constructor			
			public DSAListNode(Object inValue)
			{
				value = inValue; 
				nextNode = null;
				prevNode = null;
			}	
			/* normall accessor and mutator if need to use in other place that required*/
			// accessing the value
			public Object getValue()
			{
				return value;
			}
			
			// setting the value
			public void setValue(Object inValue)
			{
				value = inValue;
			}
			
			//accessing the next location
			public DSAListNode getNext()
			{
				return nextNode;				
			}
			
			//setting the next location
			public void setNext(DSAListNode inNode)
			{
				nextNode = inNode;				
			}			
			
			//getter for prevnode
			public DSAListNode getPrev()
			{
				return prevNode;
			}
			
			//mutator for prevNode
			public void setPrev(DSAListNode inNode)
			{
				prevNode = inNode;
			}
	}		
	
	
		
	private DSAListNode head;
	private DSAListNode tail;
	//default constructor
	public DSALinkedList()
	{
		head = null;
		tail = null;
	}
	
	/*
	* custom iterator() method for the DSALinkedList. 
	*This method will return the constructor of the Iterator inside the Iterator class.
	* so the pointer location will be at head 
	*/
	public Iterator iterator() 
	{
		return new IteratorHelper(this); // IteratorHelper(DSALinkedList); this represent the object created by DSALinkedList constructor
	}
	
	
	// check empty
	public boolean isEmpty()
	{
		boolean isEmpty = true;
		if (head != null)
		{
			isEmpty = false;
		}
		return isEmpty;
	}//end isEmpty()
	
	//setter
	public void setHead(DSAListNode inNode)
	{
		head = inNode;
	}
	public void setTail(DSAListNode inNode)
	{
		tail = inNode;
	}
	
	//getter
	public DSAListNode getHead()
	{
		return head;
	}
	public DSAListNode getTail()
	{
		return tail;
	}
	
	//insert item in head location
	public void insertFirst (Object inValue)
	{
		DSAListNode newNode = new DSAListNode(inValue);
		if (isEmpty() == true) // execute this part when list empty
		{
			newNode.nextNode = null;
			newNode.prevNode = null;
			head = newNode;			// assign new head 
			tail = newNode; //assign new tail
		}	
		else
		{			
			newNode.nextNode = head; // make the newnode point to the old head
			newNode.prevNode = null;
			head.prevNode = newNode; //point the current head prevNode to the new node
			head = newNode; // move the head pointer to new location
		}
	}// end insertFirst()
	public void insertLast(Object inValue)
	{
		DSAListNode newNode = new DSAListNode(inValue);
		
		
		if (isEmpty() == true)
		{
			insertFirst(inValue); //empty so insert first one
		}				
		else //list has more than 1 item
		{						
			newNode.nextNode = null; // newnode nextnode poitn to null
			newNode.prevNode = tail; // newnode prevNode  point to the old tail
			tail.nextNode = newNode; // old tail next node point to newNode
			tail = newNode;				// move old tail to new tail value
		}		
	}

	public Object peekFirst() 
	{
		Object peekFirst = null;
		if (isEmpty() == false)
		{
			peekFirst = head.value; // look at value of first node
		}
		else
		{
			//throw new IllegalArgumentException("The Linked List is empty. Nothing at the first Node");
			//System.out.println("the linked list is empty, nothing in the head to peek");
			System.out.println("");
		}		
		return peekFirst;
	}
	
	public Object peekLast()
	{		
		Object peekLast = null;
		
		if (isEmpty() == true)
		{
			//throw new IllegalArgumentException("The Linked List is empty. There is no thing at the last node"); 
		//	System.out.println("the linked list is empty, nothing in the tail to peek");
			System.out.println("");
		}
		else if (head.nextNode == null) //one item
		{
			peekLast = head.value; // only one item
		}		
		else 
		{						
			peekLast = tail.value;		// get the last value when the loop ends
		}
		return peekLast;
	}

	
	public Object removeFirst()
	{
		Object removedFirst =null;
		if (isEmpty() == true)
		{
			//throw new IllegalArgumentException("the linked List is empty");
			//System.out.println("the linked list is empty, nothing in the head to remove");
			
			System.out.println("");
		}
		else if (head.nextNode == null)
		{
			
			removedFirst = head.value;
			head = null;
			tail = null;
		}
		else 
		{
			removedFirst = head.value; // save the remove data
			head = head.nextNode; // assign the head to the next node or head.setNext() = head.getNext();
			head.prevNode = null;
		}
		return removedFirst;
	}
	
	public Object removeLast()
	{
		Object removedLast = null;
		
		if (isEmpty() == true)
		{
			System.out.println("");
			//System.out.println("the link list is empty, nothing in the tail to remove");
			//throw new IllegalArgumentException("the list is empty. nothing to remove");			
		}		
		else if (head.nextNode == null ) // 1 item
		{
			removedLast = tail.value;//1 item
			head = null;
			tail = null;
		}
		else 
		{
			removedLast = tail.value; // get value from the current node before delete
		//	System.out.println("tail value is : " + tail.value);
			tail = tail.prevNode; // set tail back one node			
			tail.nextNode = null; // point tail.nextNode to null
		}
		
		return removedLast;
	}
	
	
	
	/*
	public DSALinkedList inorderSort(DSALinkedList inList)
	{
		DSALinkedList newList = inList;
		DSAListNode curr = (DSAListNode) inList.head;
		DSAListNode cursor = null, temp=null, cursorTwo=null, cursorThree=null;
		if (inList.isEmpty() == true)
		{
			throw new IllegalArgumentException("empty list ");
		}
		else
		{
			while (curr !=null) // first node
			{
				cursor = curr.getNext(); //second node
				while (cursor != null) //while second node is not empty
				{
					if ( (String.valueOf(curr.getValue())).compareTo(String.valueOf(cursor.getValue())) == 1)
					{ //compare if the curren location > next location 
						if (cursor.getNext() != null && curr.getPrev() == null) //front of the list
						{
							temp = cursor;
							cursorTwo= cursor.getNext();
							cursorThree = curr.getPrev();
							cursor = curr;
							curr = temp;							
							curr.setNext(cursor);
							curr.setPrev(null);
							cursor.setNext(cursorTwo);
							cursor.setPrev(curr);
							head = curr;
							
						}
						else if ( cursor.getNext() == null && curr.getPrev()!=null) //end of the list
						{
							temp = cursor;
							cursorTwo= cursor.getNext();
							cursorThree = curr.getPrev();
							cursor = curr;
							curr = temp;							
							curr.setNext(cursor);
							curr.setPrev(cursorThree);
							cursor.setNext(null);							
							cursor.setPrev(curr);
							cursorThree.setNext(curr);
							tail = cursor;
						}
						else if ( cursor.getNext() == null && curr.getPrev()==null) //list only has 2 cases
						{
							temp = cursor;
							cursorTwo= cursor.getNext();
							cursorThree = curr.getPrev();
							cursor = curr;
							curr = temp;							
							curr.setNext(cursor);
							curr.setPrev(null);
							cursor.setNext(null);							
							cursor.setPrev(curr);
							head = curr;
							tail = cursor;
							
						}
						else //mislde of the list
						{
							temp = cursor;
							cursorTwo= cursor.getNext();
							cursorThree = curr.getPrev();
							cursor = curr;
							curr = temp;							
							curr.setNext(cursor);
							curr.setPrev(cursorThree);
							cursor.setNext(cursorTwo);							
							cursor.setPrev(curr);
							cursorThree.setNext(curr);
							cursorTwo.setPrev(cursor);
						
						}
						
					}
					cursor = cursor.getNext(); //increate the cursor to next location
				}
				curr = curr.getNext();
			}
		}
		return newList;
	}
	*/
	
	public String toString()  // to print specific item
	{		
		String result = "";
		DSAListNode showNode = head;
		if (isEmpty() == true)
		{
			result = "";
		}
		else 
		{
			while (showNode != null) // start from the head
			{			
				result = result + (showNode.value).toString() +" " ; // can add + " " to see clearer
				showNode = showNode.nextNode;				
			}
		}			
		return result;		
	}
	
	public void show() //print all the list
	{
		DSAListNode showNode = head;
		if (isEmpty() == true)
		{
			System.out.println("Empty");
		}
		while (showNode != null) // start from the head
		{			
			System.out.print(showNode.value +" ");
			showNode = showNode.nextNode;	
			
		}		
	}
	
	public String toStringGraphValue()
	{
		String s = "";
		DSAListNode showNode = head;
		if (isEmpty() == true)
		{
			s = "";
		}
		else 
		{
			while (showNode != null) // start from the head
			{			
				s = s + ((DSAGraphVertex)showNode.getValue()).getLabel()+ " : " ;
				showNode = showNode.nextNode;				
			}
		}	
		
		return s;
	}

}