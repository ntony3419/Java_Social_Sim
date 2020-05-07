	import java.util.*;
import java.lang.Math;
public class SocialSimClass
{
	private static int timeStep = 0;
	private DSAGraph network;
	private double like_posibility;
	private double follow_posibility;	
	
	public SocialSimClass()
	{
		network = new DSAGraph();
		like_posibility = 0.0;
		follow_posibility= 0.0;		
	}
	//getter
	public int getTimeStep()
	{
		return this.timeStep;
	}
	public void incTimeStep()
	{
		this.timeStep+=1;
	}
	public DSAGraph getNetwork()
	{
		return this.network;
	}
	public double getLikePos()
	{
		return this.like_posibility;
	}
	public double getFoPos()
	{
		return this.follow_posibility;
	}
	public void setNetwork(DSAGraph inNet)
	{
		this.network = inNet;
	}
	public void setLikePos(double inPos)
	{
		this.like_posibility = inPos;
	}
	public void setFoPos(double inPos)
	{
		this.follow_posibility = inPos;
	}
	public String toString()
	{
		return this.network.toString();
	}
	//addPost
	public void addPost(DSALinkedList inList)
	{
		addPost(inList, this.network);		
	}
	public DSAGraph addPost(DSALinkedList inList, DSAGraph network)
	{
		//Iterator inListIt = inList.iterator();
		Object cursor;
		//find the person inthe network
		DSAGraphVertex found = null;		
		String person = (String)inList.removeFirst();//owner
		found = network.findVertex(person);
		String postTitle = (String) inList.removeFirst();	//this is the post title
		//loop through the inList to extract the posts
		while (inList.isEmpty() == false)	//if the inlist still not empty then there some data related to the post title
		{
				cursor	= (Object) inList.removeFirst();
			postTitle= postTitle+ ":"+(String)cursor;
		}
		//the final post title can be safely use
			DSAPost newPost = new DSAPost(postTitle);
			//add the post to the person
			if (found != null)
			{	
				found.insertPost(newPost);	//add post to the  profile		
					//addpost to the social network post list 
			}			
		return network;
	}
	public DSAGraph update()	
	{
		
		DSAGraphVertex foundPerson = null;		
		DSAPost post=null;
		//find the new post and its owner
		Iterator networkIt = this.network.iterator();
		DSAGraphVertex pCursor;
		while (networkIt.hasNext() == true && post==null)
		{
			pCursor = (DSAGraphVertex)networkIt.next();	
			if (pCursor.hasNewPost() != null)
			{				
				post = pCursor.hasNewPost();//found new post
				post.setNewPost(false);
				foundPerson = pCursor;
			}//exit while loop because post is not null
		}
		DSAGraphVertex owner = foundPerson; //owner never change 
		//find new post
		//use breadth first search to find all the follower
		if (foundPerson != null && post != null)
		{		
			//while there is a new post in the post like 
				DSALinkedList queue = new DSALinkedList();
			//add owner to queue
				queue.insertLast(foundPerson);
				post.addToLikeList(owner.getLabel());//owner should like its own post
				this.network = update(this.network, queue, post, owner);			
		}
		return this.network;
	}	
	//@wrapper
	public DSAGraph update(DSAGraph network, DSALinkedList queue, DSAPost post, DSAGraphVertex owner)
	{	
			DSAGraphVertex cursor ;
		while (queue.isEmpty() == false)
		{		
			cursor = (DSAGraphVertex)queue.removeFirst(); //the person liked the post			
				//find the follower of this person				
			Iterator networkIt = network.iterator();
			DSAGraphVertex netCur;
			while (networkIt.hasNext() == true)
			{

				netCur = (DSAGraphVertex) networkIt.next(); //netCur is the potential follower			
				//check if netCur follow cursor (check the adj)
				DSALinkedList adj = netCur.getAdj();
				Iterator adjIt = adj.iterator();
				DSAGraphVertex adjCur;
				while (adjIt.hasNext() == true)
				{
					adjCur = (DSAGraphVertex) adjIt.next();		
					if (cursor.getLabel().equals(adjCur.getLabel()) == true || owner.getLabel().equals(adjCur.getLabel()) == true)
					{
						//check if netcur is already liked the post
						Iterator postIt = post.getPersonLike().iterator();
						Object pName ;
						boolean found=false,liked=false;					
						while (postIt.hasNext() == true)					
						{
							pName = (Object) postIt.next();
							if (netCur.getLabel().equals(pName) == true)
							{						
								found = true ;
							}
						}
						if (found == true)
						{
							liked = true;
						}						
						if (liked == false)
						{										
							double like_pos = Math.random();
								//if like then maybe forllow
							double fol_pos = Math.random();
							if (like_pos <= like_posibility) // like_post = 0 all connect like post = 1 no connect
							{
								//perform the like
								post.addToLikeList(netCur.getLabel());// add to likelist of the post	
								// if netcur like the post chec if netCur is not in the queue then inseert it
									if (queue.isEmpty()== true)
									{
										queue.insertLast(netCur);
									}
									else
									{
										Iterator queueIt = queue.iterator();
										boolean foundQueue = true;
										while (queueIt.hasNext() == true && foundQueue == true)
										{
											DSAGraphVertex queueCur = (DSAGraphVertex) queueIt.next();
											if (netCur.getLabel().equals(queueCur.getLabel()) == false)
											{
												foundQueue = false;
											}
										}
										if (foundQueue == false)
										{
											queue.insertLast(netCur);//add to the queue to find potential like and follow later
										}
									}										
								if (fol_pos <= follow_posibility)
								{
									//perform follow
									DSALinkedList followData = new DSALinkedList();
									followData.insertFirst(netCur.getLabel()); //the source
									followData.insertFirst(owner.getLabel()); //the destination									
									network.addEdge(followData); // add edge / person  follow  owner
								}//end if 									
							}//end if
						}//end if 							
					}//end if 						
				}//end while				
			}//end while
		}
		return network;
	}
	//edge operation
	public void edgeOperation()
	{
		System.out.println("Choose an operation : \n1. Add like/follow.\n2. Remove like/follow");
		Scanner sc = new Scanner(System.in);
		String choice = sc.next();
		FileIO fileIO = new FileIO();
		switch (choice)
		{
			case "1": // add edge
				// manually add edge 
				// automatically add edge
				this.network = fileIO.loadEdge(network);
			break;
			case "2": //remove edge
				//remove the eddge
				//enter the edge
				DSAGraphEdge deleteEdge= fileIO.termEdge(); // get edge information from the terminal 
				this.network.deleteInAdj(deleteEdge);//remove the follow /connection between two person
			break;
			default:
				System.out.println("Not a corect selection back to menu ");
			break;
		}
	}
	public boolean isEmpty()
	{
		return network.getVertices().isEmpty();
	}
	//node operation
	public void nodeOperation()
	{
		System.out.println("Choose an operation: \n1. Find Node\n2. Delete Node\n3. Insert Node\n4.return to menu");
		Scanner sc = new Scanner(System.in);
		String input = sc.next();
		switch (input)
		{
			case "1":
				//run the find method from graph
				System.out.println("Enter the key to find: ");
				String key = sc.next();
				DSAGraphVertex found;
				found = network.findVertex(key);
				System.out.println("the key found is : " + found.toString());
 			break;
			case "2":
				//run the delete method
				//run the delete method
				DSAGraphVertex delete ;
				System.out.println("Enter the key to delete: ");
				String del = sc.next();
				network.delete(del);
			break;
			case "3":
				FileIO fileIO = new FileIO(); // get method from fileIO
				System.out.println("Start File insertion");
				// this inseertion is only insert single vertex
						//String fileName = sc.next();
				network = fileIO.loadVertex(network);
				System.out.println("File insertion completed");
			break;
		}
	}
	//display post with highest popular rate
	public String showPopularPost()
	{
		String result ="";
		//loop inside the vertice and extract the post out and put them in same list
		DSALinkedList totalPost = new DSALinkedList(); 
		DSALinkedList popular = new DSALinkedList();// this will save the decending data
		Iterator verticesIt = network.getVertices().iterator();
		DSAGraphVertex verCur;
		while (verticesIt.hasNext() == true) // extract every post out of it's owner storage 
		{
			verCur = (DSAGraphVertex)verticesIt.next(); //the vertex
			Iterator postIt = ((DSALinkedList) verCur.getPostList()).iterator() ;
			//loops inside the postList to get the data out
			DSAPost pCursor;
			while (postIt.hasNext() == true)
			{
				pCursor = (DSAPost)postIt.next(); //place cusor at the head of postList
				if (pCursor != null)
				{	
					result = pCursor.toString();				
					totalPost.insertLast(pCursor); //put into a list of all the post for comparation
				}
			}
			
		}
		//the total list of all post is obtained
		//loop through each post to compare the likeCount and add to another linked list poplat
		
	
		Iterator totalIt = totalPost.iterator();
		DSAPost  cursor;	
		
		while(totalIt.hasNext() == true)
		{
			cursor = (DSAPost)totalIt.next();		
			if (popular.isEmpty() == true) //popular is empty
			{
				popular.insertFirst(cursor);
			}
			else if ( cursor.getLikeCount() > ((DSAPost)popular.getHead().getValue()).getLikeCount()) //cursor > head
			{
				popular.insertFirst(cursor);
			}
			else if ( cursor.getLikeCount() < ((DSAPost)popular.getTail().getValue()).getLikeCount())//cursor < tail
			{
				popular.insertLast(cursor);
			}
			else if ( cursor.getLikeCount() == ((DSAPost)popular.getHead().getValue()).getLikeCount())//cursor == head 
			{
				popular.insertFirst(cursor);
			}
			else //cursor == tail
			{
				popular.insertLast(cursor);
			}
		}	
		//after all the post are found and added to the popular post linked llist in decending order
		//print it loop through popular post
		Iterator popularIt = popular.iterator();
		DSAPost pCursor ;
		while (popularIt.hasNext() == true)
		{
			pCursor = (DSAPost) popularIt.next();
			
			result += pCursor.getPost() + " \n - Amount of likes:  " + 
							pCursor.getLikeCount() +"\n"; // print the title of the post		
		}		
		return result;
	}
	//display all data
	public String displayAllData()
	{
		return network.displayRecord();
	}
}