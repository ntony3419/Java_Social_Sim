import java.util.*;
public class DSAGraph
{
	//inner class for the vertex	
	//for class DSAGraph
	private DSALinkedList vertices;
	private DSALinkedList edges;
	//constructor
	public DSAGraph()
	{
		this.vertices = new DSALinkedList();
		this.edges = new DSALinkedList();
	}
	public DSALinkedList getGraphEdges()
	{
		return this.edges;
	}	
	public DSALinkedList getVertices()
	{
		return this.vertices;
	}
	public void setVertices(DSALinkedList inVertices)
	{
		this.vertices = inVertices;
	}
	public DSAGraphVertex hasVertex(DSAGraphVertex vertex)
	{
		DSAGraphVertex cursor, result = null;
		Iterator verticesIt = this.vertices.iterator();
		while (verticesIt.hasNext() == true)
		{
			cursor = (DSAGraphVertex) verticesIt.next();
			if (cursor.getLabel().equals(vertex.getLabel()) == true)
			{
				result = cursor;
			}
		}
		return result;
	}
	//find node
	public DSAGraphVertex findVertex(Object key)
	{
		DSAGraphVertex found = null;
		found = findVertex(key, this.vertices);
		return found;
	}
	public DSAGraphVertex findVertex(Object key, DSALinkedList inList)
	{
		DSAGraphVertex found = null, cursor;
		Iterator verticesIt = inList.iterator();
		//loop through the vertices for the node wiht same value as key
		while (verticesIt.hasNext() == true)
		{			
			cursor = (DSAGraphVertex) verticesIt.next();			
			if (String.valueOf(key).compareTo(String.valueOf(cursor.getLabel())) == 0)
			{
				found = cursor;
			}			
		}
		if (found == null) 
		{
			System.out.println("The item with key : " + key + " is not available in the graph");
		}
		return found;
	}
	//delete vertex
	public DSAGraphVertex delete(Object key)
	{
		//find the vertex that match the key
		DSAGraphVertex delNode= null;
		DSAGraphVertex foundVertex =null;
		foundVertex	= findVertex(key, this.vertices);
		if (foundVertex == null)
		{
			System.out.println("There no such element in the network");
		}
		else // found the matching key
		{
			//find the listNode associate with the foundVertex
			System.out.println("found vertex " + foundVertex.getLabel().toString());
			DSALinkedList.DSAListNode foundListNode = findNode(foundVertex, this.vertices);
			System.out.println("foundList Node:  "  + ((DSAGraphVertex)foundListNode.getValue()).getLabel().toString());
			DSALinkedList.DSAListNode left = null, right = null;			
			DSALinkedList adj ;
			//delete the node from the vertices						
			if (foundListNode != null) //delete in the vertices then get inside the neighbors and delete
			{
				left = foundListNode.getPrev(); // set cursor left
				right = foundListNode.getNext(); // set cursor right			
				delNode = (DSAGraphVertex)foundListNode.getValue(); // return the value to delnode
				//perform deletion	
				if (left == null && right != null) //at begining of the vertices
				{					
					this.vertices.removeFirst();
				}
				else if (left != null && right == null) // ath the  end of the verties
				{
					this.vertices.removeLast();
				}
				else if (left == null && right == null) // only one item
				{
					this.vertices.removeLast();
				}
				else //other cases
				{
					right.setPrev(left);
					left.setNext(right); 
				}
			}
			foundListNode = null; //reset the found list Node for other search
			/* for directed graph need to search through the adj of the whole vertices*/
			Iterator verticesIt = this.vertices.iterator(); //adj of the deleting node
			DSAGraphVertex cursor;
			DSALinkedList.DSAListNode nodeCur;
			while (verticesIt.hasNext() == true)
			{
				cursor = (DSAGraphVertex) verticesIt.next();
				//extract the adj
				adj = cursor.getAdj();
				System.out.println("adj : "+ adj.toStringGraphValue());
				//loop inside the adj to find the key		
				nodeCur = adj.getHead(); //cursor at head of the adj
				while (nodeCur != null)
				{								
					//extract the first Item adj and remove the key matched node from its adjList	
					System.out.println("curr Node " + ( (DSAGraphVertex)nodeCur.getValue()).getLabel() );	
					if (((DSAGraphVertex)nodeCur.getValue()).getLabel().equals(key) == true )
					{
						foundListNode = nodeCur;
					}
					if (foundListNode != null) //delete in the vertices then get inside the neighbors and delete
					{
						System.out.println("found list Node " + ( (DSAGraphVertex)foundListNode.getValue()).getLabel() );
						left = foundListNode.getPrev(); // get cursor left
						right = foundListNode.getNext(); // get cursor right			
						delNode = (DSAGraphVertex)foundListNode.getValue(); // return the value to delnode						
						//perform deletion	
						if (left == null && right !=null ) //at begining of the vertices
						{					
							System.out.println("here 1");
							adj.removeFirst();		
							foundListNode = null; //reset the foundListNode
						}
						else if (left != null && right == null) // ath the  end of the verties
						{	
							System.out.println("here 2");					
							adj.removeLast();
							foundListNode = null; //reset the foundListNode
						}
						else if (left == null && right == null)
						{
							System.out.println("here 3");
							adj.removeLast();
							foundListNode = null; //reset the foundListNode
						}						
						else //other cases
						{
							System.out.println("here 4");
							right.setPrev(left);
							left.setNext(right); 
							foundListNode = null; //reset the foundListNode
						}					
					}
					nodeCur = nodeCur.getNext();
				}
				System.out.println();
			}
		}
		return delNode;
	}
	//find Node
		public DSALinkedList.DSAListNode findNode(Object key, DSALinkedList adj)
		{
			DSALinkedList.DSAListNode found = null, cursor;
			System.out.println("key : " + key );
			cursor = adj.getHead();
			while (cursor != null )
			{		
				if (key.equals(((DSAGraphVertex)cursor.getValue())) == true )
				{
					found = cursor;
					System.out.println("found : " + ((DSAGraphVertex)found.getValue()).getLabel() ) ;
				}
				cursor = cursor.getNext();
			}
			return found;			
		}
	// delete the vertex inside the adj // delete or remove the likes / follow
	public void deleteInAdj(Object key)
	{
		if (key instanceof DSAGraphEdge)
		{
			DSAGraphEdge newEd = (DSAGraphEdge) key; // convert the key object to graph edge
			Object source = (Object)newEd.getSource(); // get the source to find in the vertices
			Object dest = newEd.getDest(); //get the dest value to perform delete later
			DSAGraphVertex delNode= null;
			DSAGraphVertex foundVertex =null;
			DSALinkedList.DSAListNode left = null, right = null;
			System.out.println("source " + source);
			foundVertex	= findVertex(source, this.vertices); // search for the vertex base on the extracted source
			System.out.println(" find function sorce: " +foundVertex.toString());
			if (foundVertex != null)//delete inside the source adj
			{
				DSALinkedList adj = foundVertex.getAdj(); //extract the adj of the source (coresponding to the edge )
					//use an iterator				 
				Iterator adjIt = adj.iterator(); // iterator of the adj 
					System.out.println("adj is : " + adj.toString());
				DSALinkedList.DSAListNode foundDest = null; 
				foundVertex = findVertex(dest, adj); //find the dest vertex in the adj
				System.out.println(" found vertex des is : " + foundVertex.toString());
				foundDest = findNode(foundVertex, adj); // find the dest Node in the adj using the foundVertex data
				System.out.println(" dest to search is : " + dest);
				System.out.println("found dest is : " + foundDest.getValue());
				if (foundDest != null)
				{
					left = foundDest.getPrev(); // set cursor left
					right = foundDest.getNext(); // set cursor right			
					System.out.println("left : " + left);
					System.out.println("right : "+ right );
					//perform deletion	
					if (left == null && right !=null ) //at begining of the vertices
					{					
						System.out.println("here 1");
						adj.removeFirst();						
					}
					else if (left != null && right == null) // ath the  end of the verties
					{	
						System.out.println("here 2");					
						adj.removeLast();
					}
					else if (left == null && right == null) //the adj only have 1 item
					{
						System.out.println("here 3");
						adj.removeFirst();
					}
					else //other cases
					{
						System.out.println("here 4");
						right.setPrev(left);
						left.setNext(right); 
					}
				}				
			}
		}
	}	
	public String displayRecord()
	{
		Iterator verticesIt = this.vertices.iterator();
		String result = "";
		DSAGraphVertex cursor=null;
		while (verticesIt.hasNext() == true)
		{
			cursor = (DSAGraphVertex)verticesIt.next();			
			result += cursor.displayPerson() + " \n";
		}		
		return result;
	}
	//insert
	public void insert(Object key)
	{
	}
	public boolean isEmpty()
	{
		return this.vertices.isEmpty();
	}
	//get the linkedlist of the label that are linked together
	public void addVertex(DSALinkedList inList)
	{
		Object label;
		DSAGraphVertex newVertex, cursorVertex, addedFound = null, rootVertex=null, currVertex=null;
		Iterator inListIter = inList.iterator();//create iterator for the inList
		Iterator verticesIter = vertices.iterator(); //create iterator for the vertices
		if (inList.getHead().getNext() == null) //when the input list only have 1 item (each list is a line ) 			
		{	//perform add vertex only
			// 2nd argument for value is its label as this worksheet doesn't have value for the vertex
			// make sure duplicate label will not be added
			if (vertices.isEmpty()==true)
			{
				newVertex = new DSAGraphVertex(inList.getHead().getValue(), inList.getHead().getValue() );
				vertices.insertLast(newVertex);	//add the vertex into the vertices	
			}
			else // vertices is not empty
			{				
				newVertex = new DSAGraphVertex(inList.getHead().getValue(), inList.getHead().getValue() );
				//make sure the newVertex is not already added in the list
				Iterator verticesIt = this.vertices.iterator();
				boolean found = false;
				DSAGraphVertex cursor;
				while (verticesIt.hasNext()==true)// loop the vertices
				{
					cursor = (DSAGraphVertex) verticesIt.next();
					if (newVertex.getLabel().equals(cursor.getLabel()) == true )
					{
						found = true;
					}
				}
				if (found == false)//new vertex is not added in the list yet
				{
					vertices.insertLast(newVertex);	//add the vertex into the vertices
				}
			}
		}
		else // when inlist has more than 1 item (inlist is a line vertex and edge as List
		{
			addEdge(inList);
		}		
	}
	public void addEdge(DSALinkedList inList)
	{
		//remove fist item for the labelOne and the rest will be the destination
		if (isEmpty() == true)
		{
			System.out.println("network empty, add some people ");
		}
		else
		{			
			Object label = inList.removeFirst();
			addEdge(label,inList);
		}
	}
	public void addEdge(Object labelOne, DSALinkedList inList)
	{
		//1. find the label in the vertices list 
		DSAGraphVertex found = null, cursor=null, source=null, dest=null;
		// create the iterator of the vertices
		Iterator verticesIt = this.vertices.iterator();
		while (verticesIt.hasNext()==true) 
		{
			cursor = (DSAGraphVertex)verticesIt.next(); // cursor at head of the vertices
			//compare cursor with the labelone
			if (labelOne.equals(cursor.getLabel()) == true ) // compare the labelOne and the vertex label to see if the source of the edge is available
			{
				found = cursor; //found the location of the vertex to add edge
			}
		}
		if (found != null) // if the destination is found above, look for the source
		{ // look for the source of the edge
			// as found not null the source
				//check if the label in the inList is added in the graph 
				// if not added then cannot add the edge
				//use while loop to extract the label from the inList 1 by 1
			dest = found; // this is the destination vertex
			//look for the source 
			Object label, labelTwo =null;
			//create iterator for the inList
			Iterator inListIt = inList.iterator();
			while (inList.isEmpty()==false) //when the inList is not empty loop to find the dest
			{
				label = inList.removeFirst(); // pop first item out of the inList
				//check if the label is in the vertices already
					//reset the iterator of vertices
				verticesIt = this.vertices.iterator();
				while (verticesIt.hasNext()==true) //search the label inside the verties to find the vertex associate with it
				{
					cursor =(DSAGraphVertex) verticesIt.next();
					if (label.equals(cursor.getLabel()) == true)//label is added (destination is found)
					{
							//found the source
						source = cursor;						
					}
				}
				if (source != null)// source is found above and destination is found. 
				{	//create the edge and add to edge list
					if (source.getLabel().equals(dest.getLabel()) == false)
					{
						DSAGraphEdge ed = new DSAGraphEdge(source.getLabel(), dest.getLabel()); //labelOne is source, label is destination of the edge
						edges.insertLast(ed);
					//add labelTwo to the source vertex adj and label to the destination vertex
						//check if the adj already contain the adding vertex
						//create Iterator for the adj of each location
						DSAGraphVertex foundInSourceAdj = null; //foundInDestAdj = null;
						if (source.getAdj().isEmpty()==true && source.getLabel().equals(dest.getLabel()) == false)// source adj (follow list) is empty
						{
							source.getAdj().insertLast(dest); //	source.getAdj().insertLast(dest); 
							source.incFollowCount(); //increase the follow count of the source
							//add the source into the followedBy list in the dest
							dest.addFolerBy(source);
						}
						else //souce adj (follow list ) is not empty
						{//check if there are duplication of destionation inside the source adj (followlist)
							Iterator sourceAdjIt = source.getAdj().iterator();
							Iterator destAdjIt = dest.getAdj().iterator();
							//check in the source adj to see if destination is added
							while (sourceAdjIt.hasNext()==true)
							{// check if the destination vertex is found in the adj list
								cursor = (DSAGraphVertex) sourceAdjIt.next();
								if (dest.getLabel().equals(cursor.getLabel()) == true) //label of dest in the adjList of source
								{
									foundInSourceAdj = cursor;
								}
							}
							if (foundInSourceAdj == null) //destination is not in the adj list (not follow yet)
							{
								source.getAdj().insertLast(dest); // source follow dest (A-> B )
								source.incFollowCount(); //increase follow count for the souce
								//add the source into the followedBy list in the dest
								dest.addFolerBy(source);
							}
						}
					}			
						//else {throw new IllegalArgumentException("error 3 " );}
				}// else process with the next label from the inList			
			}
		}
		else //the source of the edge is not found 
		{
			throw new IllegalArgumentException("Please input the Person first before perform connections");
		}		
	}
	public Iterator iterator()
	{
		return this.vertices.iterator();
	}
	public String toString()
	{
		String result = "";
		Iterator itr = vertices.iterator();
		//reset states of the vertices
		resetState();
		//rearange the vertices
		this.vertices = arrangeVertex(this.vertices);
		while (itr.hasNext() == true)
		{
			result = result  +(itr.next()).toString() + "\n";		
		}		
		return result; 
	}
	public DSAGraphVertex displaySpecific(Object key)
	{
		//loop through the vertices and find the node with same label as the key
		Iterator verticesIt  = this.vertices.iterator();
		DSAGraphVertex cursor = null, found = null; 
		while (verticesIt.hasNext() == true)
		{
			cursor = (DSAGraphVertex) verticesIt.next();
			if (key.equals(cursor.getLabel())==true)
			{
				found = cursor;
			}
		}
		if (found != null)
		{
			System.out.println(found.displayPerson());
		}
		return found;
	}
	public void displayAsList()
	{
		Iterator itr = vertices.iterator();
		while (itr.hasNext() == true)
		{
			System.out.println ( ((DSAGraphVertex)(itr.next())).toString() );
		}
	}
	// this method will rearrange the vertices alphabetical false	
	public DSALinkedList arrangeVertex(DSALinkedList inList)
	{		
		DSALinkedList labelList = inorderSortLabel(inList);
		Iterator labelIt = (labelList).iterator();
		Object label;
		DSALinkedList newList = new DSALinkedList ();
		DSAGraphVertex cursor,new1;
		//get the vertex associate with the label
		while (labelIt.hasNext() == true)
		{
			label = labelIt.next();			
			Iterator verticesIter = (inList).iterator();
			while (verticesIter.hasNext() == true)
			{
				cursor = (DSAGraphVertex)verticesIter.next();
				if ( (String.valueOf(label) ).compareTo ( String.valueOf(cursor.getLabel())) ==0 )
				{
					//the vertex with same labe is found
					//arrange the neighbor list inside the cursor
					new1 = arrangeNeighbor(cursor);
					//add to the new list					
					newList.insertLast(new1);
				}
			}
		}		
		// reset all the visited state to false
		Iterator newIter = newList.iterator();		
		while (newIter.hasNext()== true)
		{
			cursor = (DSAGraphVertex)newIter.next();
			cursor.clearVisited();			
		}
		return newList;
	}
	//method to arrange the neighbor list inside the vertex
	public DSAGraphVertex arrangeNeighbor(DSAGraphVertex vertex)
	{
		DSAGraphVertex newVertex = vertex;
		//extract the adj from the vertex
		DSALinkedList adj = vertex.getAdj();
		if (adj != null) // the vertex has edge and neighbor
		{
			//reset the visit state
			Iterator newIter = adj.iterator();		
			while (newIter.hasNext()== true)
			{
				((DSAGraphVertex)newIter.next()).clearVisited();				
			}
			//arrange the adj
			DSALinkedList labelAdjList = inorderSortLabel(adj); // get the order of the labels of the adj
			DSALinkedList newAdj= new DSALinkedList();
			Object labelAdj;
			Iterator labelAdjIt = labelAdjList.iterator();
			DSAGraphVertex cursor;
			while (labelAdjIt.hasNext())
			{
				labelAdj = labelAdjIt.next(); 
				Iterator adjListIt = adj.iterator();
				while (adjListIt.hasNext()==true) //this loop will rearrange the neighbor list of the vertex
				{
					cursor = (DSAGraphVertex)adjListIt.next();
					if ( (String.valueOf(labelAdj) ).compareTo ( String.valueOf(cursor.getLabel())) ==0 )
					{
						newAdj.insertLast(cursor);	
					}										
				}	
			}				
			newVertex.setAdj(newAdj);			
		}
		return newVertex;
	}
	public void displayArrangedVertex()
	{
		this.vertices = arrangeVertex(this.vertices);
		System.out.println("the arranged vertices " + this.vertices.toStringGraphValue());
	}
	//this method will return a linkedlist of label ONLY
	public DSALinkedList inorderSortLabel(DSALinkedList inList)
	{
		//remov
		DSALinkedList newList =new DSALinkedList();
		Iterator inListIt = inList.iterator();
		while(inListIt.hasNext() == true)
		{		
			DSAGraphVertex min = (DSAGraphVertex)inList.getHead().getValue();			
			Iterator falseIt = inList.iterator();
			while (min.getVisited()==true )
			{
				if (falseIt.hasNext()==true)
				{
					min = (DSAGraphVertex)falseIt.next();
				}
			}			
			Iterator minIt = inList.iterator();
			while (minIt.hasNext() ==true)
			{
				DSAGraphVertex cursor =  (DSAGraphVertex)minIt.next();
				if (String.valueOf(cursor.getLabel()).compareTo(String.valueOf(min.getLabel())) < 0)
				{
					if (cursor.getVisited() == false)
					{
						min = cursor;
					}
				}
			}
			min.setVisited(true);
			newList.insertLast(min.getLabel());	
			inListIt.next();
		}		
		return newList;	
	}
public String stringDepthFirstSearch()
{
	//1. rearragne all the node in vertices before the traversal /use sort method from DSALinkedList
	this.vertices = arrangeVertex(this.vertices);	
	resetState();//2, set  state all vertex to not visited
	String result = "";
	// below start the dfs algorithm
	//create a stack
	DSAStackClass stack = new DSAStackClass();
	//get the first item from the vertices 
	Iterator verticesIter = this.vertices.iterator();
	verticesIter = this.vertices.iterator();
	DSAGraphVertex vertex = (DSAGraphVertex)vertices.getHead().getValue();
	//push the first item to the stack
	stack.push(vertex);	
	//start traversal
	DSAGraphVertex sVertex;	
	while (stack.isEmpty()==false)
	{
		sVertex = (DSAGraphVertex)stack.top(); //get first vertex from the stack
		sVertex.setVisited(true); //set state visit to true
		DSAGraphEdge ed = null;
		DSALinkedList vAdj = sVertex.getAdj(); //extract the adj
		//set up an iterator for the vertex adj
		Iterator vAdjIt =vAdj.iterator();
		//find the next non visited vertex to add into the stack
		DSAGraphVertex next = null; //save the next non visited
		while (vAdjIt.hasNext() == true)
		{
			DSAGraphVertex vCursor = (DSAGraphVertex)vAdjIt.next();
			//check the cursor to find the non visited
			if ( vCursor.getVisited() == false && next == null)
			{
				next = vCursor;
			}
		}
		//push the net to stack
		if (next != null)
		{
			stack.push(next);
			 ed = new DSAGraphEdge(sVertex.getLabel(), next.getLabel());
		}
		else
		{
			stack.pop();
		}
		if (ed != null)
		{
			result = result + ed.toString() + ",";
		}		
	}
	resetState(); //set  state all vertex to not visited for other menu option
	return "{"+result+"}";
} 
	public String stringBreadthSearch()
	{
		String result= "";
		this.vertices = arrangeVertex(this.vertices);	//1. rearragne all the node in vertices before the traversal /use sort method from DSALinkedList
		resetState(); //2. set  state all vertex to not visited		
		// below start the breadth search algorithm
		//create a queue
		DSAQueue queue = new DSAQueue();
		//get the first item from the vertices 
		DSAGraphVertex vertex = (DSAGraphVertex)vertices.getHead().getValue();
		//enqueue the first item to the queue
		queue.enqueue(vertex);	
		//start traversal
		DSAGraphVertex sVertex; //source		
		while (queue.isEmpty()==false)
		{
			sVertex = (DSAGraphVertex)queue.dequeue(); //get first vertex from the queue
			sVertex.setVisited(true); //set state visit to true
			DSAGraphEdge ed = null;
			DSALinkedList vAdj = sVertex.getAdj(); //extract the adj
			//set up an iterator for the vertex adj
			Iterator vAdjIt =vAdj.iterator();
			//put al lthe neighbor into the queue
			while (vAdjIt.hasNext()==true) //loop through the neighbor and put into the queue
			{
				//find the not visited vertex in the neighbord and put into the queue
				DSAGraphVertex cursor = (DSAGraphVertex)vAdjIt.next();
				if ( cursor.getVisited() == false)
				{
					queue.enqueue(cursor);
				}
			}
			//reset the iterator of the neigbor list
			vAdjIt =vAdj.iterator();
			DSAGraphVertex next; //save the next non visited
			while (vAdjIt.hasNext() == true)
			{
				DSAGraphVertex vCursor = (DSAGraphVertex)vAdjIt.next();
				//check the cursor to find the non visited
				if ( vCursor.getVisited() == false )
				{
					next = vCursor;
					next.setVisited(true);// set the state of the next to visited
					ed = new DSAGraphEdge(sVertex.getLabel(), next.getLabel()); // create the edge from source and next					
					result = result + ed.toString() + ","; // export 
				}
			}		
		}
		//reset all the state to false for other activity from the main menu	
		resetState();
		return "{"+result +"}";
	}
	
		//display the graph with the hightest follow by
	public String orderFollowByCount()
	{//highest followCount first
		resetState();
		String result = "";
		DSALinkedList newList =new DSALinkedList();
		DSALinkedList inList = this.vertices;
		Iterator inListIt = inList.iterator();
		while(inListIt.hasNext() == true)
		{		
			DSAGraphVertex max = ((DSAGraphVertex)inList.getHead().getValue() );			
			Iterator falseIt = inList.iterator();
			while (max.getVisited()==true )
			{
				if (falseIt.hasNext()==true)
				{
					max = (DSAGraphVertex)falseIt.next();
				}
			}			
			Iterator maxIt = inList.iterator();
			while (maxIt.hasNext() ==true)
			{
				DSAGraphVertex cursor =  (DSAGraphVertex)maxIt.next();
				if (cursor.getNumFoler() > max.getNumFoler() )
				{
					if (cursor.getVisited() == false)
					{
						max = cursor;
					}
				}
			}
			max.setVisited(true);
			newList.insertLast(max);	
			inListIt.next();
		}		
		this.vertices = newList;
		Iterator verticesIt = this.vertices.iterator();
		DSAGraphVertex cursor ;
		System.out.println("---the one with the most followers will be on top: \n");
		while (verticesIt.hasNext() == true)
		{
			cursor = (DSAGraphVertex) verticesIt.next();
			result = result + cursor.getLabel()+ " is followed by:  " + cursor.getNumFoler()  +" person."+ "\nThey are: "+(cursor.getFolerBy()).toStringGraphValue() +
					"\n" + cursor.getLabel() + " following : " + cursor.getAdj().toStringGraphValue() + "\n\n";
		}
		return result;
	}
	public void resetState()
	{
		Iterator verticesIter = this.vertices.iterator();
		while(verticesIter.hasNext() == true)
		{
			((DSAGraphVertex) verticesIter.next()).clearVisited();
		}
	}
	public String toStringAsList()
	{
		String result = "";
		Iterator itr = vertices.iterator();
		
		
		while (itr.hasNext() == true)
		{
			result = result + (itr.next()).toString() + "\n";		
		}		
	
		return result; 
	
	}
	
}