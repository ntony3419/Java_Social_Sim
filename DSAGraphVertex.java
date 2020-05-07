import java.util.*;
public class DSAGraphVertex
	{
		private Object label;
		private Object value;		
		private DSALinkedList adj; //or follower list
		private int followCount;// the ammount of following other 
		private boolean visited;
		private DSALinkedList postList; //save the record of posts 
		private int postCount;
		private DSALinkedList folerBy; // list of the person following me
		private int numFollowed;
		public DSAGraphVertex(Object label, Object value)
		{			
			if (label == null || value == null)
			{
				throw new IllegalArgumentException("label cannot be empty");
			}			
			try
			{
				Integer.parseInt(String.valueOf(label));
				this.label = label;				
				this.value = value;			
				this.adj = new DSALinkedList();				
				this.visited = false;		
				postCount = 0;				
				this.followCount = 0;
				this.postList = new DSALinkedList();
				this.folerBy = new DSALinkedList();
				this.numFollowed = 0;
			}
			catch (Exception e)
			{
				try
				{
					if ( (label instanceof String ) )
					{
						this.label = label;				
						this.adj = new DSALinkedList();					
						this.value = value;
						this.visited = false;						
						postCount = 0;		
						this.postList = new DSALinkedList();
						postCount = 0;
						this.folerBy = new DSALinkedList();
						this.numFollowed=0;
					}					
				}
				catch (Exception ex)
				{					
					System.out.println("prefer String or int");
				}
			}			
		}
		public void clearVisited()
		{
			this.visited = false;
		}
		public Object getValue()
		{
			return value;
		}
		public boolean getVisited()
		{
			return visited;
		}
		public int getPostCount()
		{
			return this.postCount;
		}
			public Object getLabel()
		{
			return label;
		}
		public DSALinkedList getAdj()
		{
			return adj;
		}	
		public DSALinkedList getPostList()
		{
			return this.postList;
		}
		public int getFollowCount()
		{
			return this.followCount;
		}
		public DSALinkedList getFolerBy()
		{
			return this.folerBy;
		}
		public int getNumFoler()
		{
			return this.numFollowed;
		}
		//mutator
		public void setValue(Object value)
		{
			this.value = value;
		}
		public void setPostCount (int postCount)
		{
			this.postCount = postCount;
		}
		public void setVisited(boolean inVisit)
		{
			this.visited = inVisit;
		}
		public void setLabel(Object label)
		{
			this.label = label;
		}
		public void setAdj(DSALinkedList adj)
		{
			this.adj = adj;
		}
		public void setPostList(DSALinkedList list)
		{
			this.postList = list;
		}
		public void setNumFoler(int in)
		{
			this.numFollowed = in;
		}
		public void incFollowCount()
		{
			this.followCount++;
		}
		public String adjString()
		{
			String adjString = "";
			Iterator adjIter = (this.adj).iterator();	
			while (adjIter.hasNext() == true)
			{
				adjString = adjString + " : " +  ( ((DSAGraphVertex)(adjIter.next())).getLabel() ).toString() ;
			}		
			return adjString;			
		}
		public DSAPost hasNewPost()
		{
			DSAPost hasNewPost = null;
			Iterator newPostIt = postList.iterator();
			DSAPost cursor;
			while (newPostIt.hasNext() == true)
			{
				cursor = (DSAPost)newPostIt.next();			
				if (cursor.getNewPost() == true)
				{
					hasNewPost = cursor;
				}
			}
			return hasNewPost;
		}
		public void insertPost(DSAPost post)
		{
			postList.insertLast(post);
			postCount++;
		}
		public void addFolerBy(DSAGraphVertex person)
		{
			this.folerBy.insertLast(person);
			numFollowed++;
		}		
		public String displayPerson()
		{
			String result = "";
			result = "\nName: " + label + "\nfollowing : " + adj.toStringGraphValue() +
						"\npost Count :" + postCount +"\n" + postList.toString()+ "\n";
			return result ;
		}
		//display vertex as list
		public String toString()
		{
			String adjResult ="";			
			adjResult = label.toString() + "" + adjString() ;
			return adjResult;
		}		
	}