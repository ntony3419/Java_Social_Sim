import java.util.*;
public class DSAPost
{
	private String post;
	private DSALinkedList personLike; //save the list of person who like the post
	private int likeCount; //count the like	
	private boolean newPost; //a new post is when the post has not been manipulate by the posibility from the other peoplr
	private boolean visited; // for convienice in accessing for shorting in showPopular() in DSAGraph class
	//create a post
	public DSAPost(String post)
	{
		this.post = post;
		likeCount = 0;
		personLike = new DSALinkedList(); // noone like yet		
		newPost = true;
	}
	//getter
	public String getPost()
	{
		return post;
	}
	public int getLikeCount()
	{
		return likeCount;
	}
	public DSALinkedList getPersonLike()
	{
		return this.personLike;
	}
	public boolean getNewPost()
	{
		return this.newPost;
	}
	public boolean getVisited()
	{
		return this.visited;
	}
	//setter
	public void setPost(String inPost)
	{
		this.post = inPost;
	}
	public void setLikeCount(int likeCount)
	{
		this.likeCount = likeCount;
	}
	public void setPersonLike(DSALinkedList personLike)
	{
		this.personLike = personLike;
	}
	public void setNewPost(boolean newPost)
	{
		this.newPost=newPost;
	}
	public void setVisited(boolean inVisited)
	{
		this.visited = inVisited;
	}
	public void addToLikeList(Object person)
	{	
			if (this.personLike.isEmpty() == false)//if the person is not in the personLike then add
			{				
				if (alreadyLike(person) == false)
				{
					this.personLike.insertLast(person);
					likeCount++;	
				}
			}
			else //nothing in the personLike yet
			{			
				this.personLike.insertLast(person);
				likeCount++;	
			}
	}
	public boolean alreadyLike(Object person)
	{
		boolean liked = true;
		if (this.personLike.isEmpty() == false)
		{
			Iterator likeIt = this.personLike.iterator();
			boolean found = false;
			Object cursor ;
			while (likeIt.hasNext() == true && found == false )
			{
				cursor = (Object)likeIt.next();
				if (cursor.equals(person) == true)
				{
					found = true;
				}				
			}
			if (found == false)
			{
				liked=false;
			}
		}
		else //emtpy list
		{
			liked = false;
		}
		return liked;
	}
	public String toString()
	{
		String result="";
		result = "Post: \""+ post +  "\t newly posted: "+ newPost + " \" has "+likeCount + " like " + 
		"and liked by " +  personLike.toString() +"\n" ;  
		return result;
	}
}