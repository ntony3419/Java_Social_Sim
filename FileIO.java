import java.util.*;
import java.io.*;
public class FileIO
{
	public DSALinkedList terminalImport() // get data from the terminal line and return a linkedlist of value 
	{
		Scanner sc = new Scanner (System.in);
		String line ;		
		DSALinkedList lineData = new DSALinkedList();
		System.out.println("enter vertex data (A B C ... in which A:label [ B C .... ] label of other location format) : ");
		line = sc.nextLine();
		StringTokenizer stk = new StringTokenizer(line,":");
		while (stk.hasMoreTokens())
		{
			lineData.insertLast(stk.nextToken());
		}
		return lineData;
	}//end terminalImport()
	public DSAGraphEdge termEdge()
	{
		DSAGraphEdge newEd = null;
		Scanner sc = new Scanner(System.in);
		String line;
		int count = 0;
		DSALinkedList lineData = new DSALinkedList();
		System.out.println("enter the edge with the format:\n\t\tsource,dest\n\te.g. A,B ");
		line = sc.nextLine();
		StringTokenizer stk = new StringTokenizer(line, ",");
		while (stk.hasMoreTokens())
		{
			if (count >2 )
			{
				throw new IllegalArgumentException ("the edge only have 2 ends");
			}		
			lineData.insertLast(stk.nextToken());
			count++;
		}
		Object source = lineData.removeFirst();
		Object dest = lineData.removeFirst();
		newEd = new DSAGraphEdge(source, dest);	
		return newEd;		
	}
	public DSAGraph loadEdge(DSAGraph inGraph) //this method will take 
	{
		DSALinkedList labelList = new DSALinkedList();
		String fileName = getFileName();
		FileInputStream fstr;
		InputStreamReader srd;
		BufferedReader brd;
		try 
		{
			fstr = new FileInputStream(fileName);
			 srd = new InputStreamReader(fstr);
			 brd = new BufferedReader(srd);
			 String line;
			 StringTokenizer stk =null;
			 line = brd.readLine();
			 while (line != null)
			 {
				 stk = new StringTokenizer(line, ":");
				 while(stk.hasMoreTokens() == true)
				 {
					 labelList.insertLast(stk.nextToken());
				 }
				 inGraph.addEdge(labelList);
				 line=brd.readLine();
			 }
			 fstr.close();
		}
		catch (IOException e)
		{
			System.out.println("problem open file"+e.getMessage());
		}
		return inGraph;		
	}
	public SocialSimClass loadPost(SocialSimClass socialSim, String fileName)
	{
		System.out.println("data should be in format:\" P:PersonName:post title \"");
		FileInputStream fstr;
		InputStreamReader ir;
		BufferedReader br;
		DSALinkedList lineData = new DSALinkedList();
		try
		{							
			fstr = new FileInputStream(fileName);
			ir = new InputStreamReader(fstr);
			br = new BufferedReader(ir);
			String line ;			
			StringTokenizer stk;
			line = br.readLine();
			while (line != null)
			{
				stk = new StringTokenizer(line,":");
				while (stk.hasMoreTokens())
				{
					lineData.insertLast(stk.nextToken());
				}				
				//get the type P
				Object extract;
				extract = lineData.removeFirst();
				if (String.valueOf(extract).equals("P") == false) 
				{
					loadEventFile(fileName, socialSim);
				}
				else //fist item is a P
				{
					socialSim.addPost(lineData);//perform adding the post
				}
				lineData = new DSALinkedList();//reset the linedata before next line
				line=br.readLine();
			}			
			fstr.close();
		}
		catch (IOException e)
		{
			System.out.println("error" + e.getMessage());
		}
		return socialSim;
	}
	public String getFileName()
	{
		String fileName= "";
		System.out.println("enter file name : ");
		Scanner sc = new Scanner(System.in);		
		fileName = sc.next();
		return fileName;
	}
	public SocialSimClass loadFromFile(String fileName)
	{
		FileInputStream fstr;
		InputStreamReader ir;
		BufferedReader br;
		DSALinkedList lineData = new DSALinkedList();
		SocialSimClass socialSim = new SocialSimClass();
		try
		{							
			fstr = new FileInputStream(fileName);
			ir = new InputStreamReader(fstr);
			br = new BufferedReader(ir);
			String line ;			
			StringTokenizer stk;
			line = br.readLine();
			while (line != null)
			{
				stk = new StringTokenizer(line,":");
				while (stk.hasMoreTokens())
				{
					lineData.insertLast(stk.nextToken());
				}				
				(socialSim.getNetwork()).addVertex(lineData);
				lineData = new DSALinkedList();
				line=br.readLine();
			}			
			fstr.close();
		}
		catch (IOException e)
		{
			System.out.println("error" + e.getMessage());
		}
		return socialSim;		
	}
		//for simulating option
	public SocialSimClass simulation(String argv[])
	{
		//extract the first item of  the inlist and check for the appropriate acction
		SocialSimClass socialSim = new SocialSimClass();
		String netFile = argv[1];
		String eventFile = argv[2];
		double probLike = Double.parseDouble(argv[3]);
		double probFoll = Double.parseDouble(argv[4]);
		//add person 
		if (netFile != null)
		{
			socialSim = loadFromFile(netFile);
		}
		//set the probability
		socialSim.setLikePos(probLike);
		socialSim.setFoPos(probFoll);
		//load event file
		socialSim = loadEventFile(eventFile, socialSim);
		//perform all the update time step
		//while the graph still has new post then the update() will run continuously
			//perform all the update time step
			//while the graph still has new post then the update() will run continuously
			//travel all the new post and update
			Iterator socialSimIt = socialSim.getNetwork().iterator();
			DSAGraphVertex cursor;
			while (socialSimIt.hasNext() == true)
			{
				cursor = (DSAGraphVertex) socialSimIt.next();
				if (cursor.hasNewPost() != null) // there still new post
				{								
					socialSim.update();								
				}								
			}					
		return socialSim;
	}
	public SocialSimClass loadEventFile(String eventFile, SocialSimClass socialSim)
	{
		FileInputStream fst;
		InputStreamReader rd;
		BufferedReader bf;
		StringTokenizer stk; 
		try
		{
			fst = new FileInputStream(eventFile);
			rd = new InputStreamReader(fst);
			bf = new BufferedReader(rd);
			String line ;
			line = bf.readLine();
			while (line != null)
			{
				stk = new StringTokenizer(line, ":");
				DSALinkedList lineData = new DSALinkedList();
				while (stk.hasMoreTokens() == true)
				{
					lineData.insertLast(stk.nextToken());
				}
				String action;
				action = String.valueOf(lineData.removeFirst());
				switch (action)
				{
					case "A":
						//create a list of vertex to add to match the argument of addVertex()
						DSALinkedList nameList = new DSALinkedList();	
						socialSim.getNetwork().addVertex(lineData); //call the add vertex 			
					break;
					case "P"://post					
						socialSim.addPost(lineData);											
					break;
					case "F"://follow												
						socialSim.getNetwork().addVertex(lineData);
					break;
					default:
					break;
				}					
				line = bf.readLine();
			}
			rd.close();
			fst.close()	;
		}
		catch (IOException e)
		{
			System.out.println("eror " + e.getMessage());
		}
		return socialSim;
	}
	public DSAGraph loadVertex(DSAGraph inNetwork)
	{
		FileInputStream fstr;
		InputStreamReader ir;
		BufferedReader br;
		DSALinkedList lineData = new DSALinkedList();
		DSAGraph network = inNetwork;
		try
		{							
			System.out.println("enter file name : ");
			Scanner sc = new Scanner(System.in);
			String fileName;
			fileName = sc.next();
			fstr = new FileInputStream(fileName);
			ir = new InputStreamReader(fstr);
			br = new BufferedReader(ir);
			String line ;			
			StringTokenizer stk;
			line = br.readLine();
			while (line != null)
			{
				stk = new StringTokenizer(line,":");
				while (stk.hasMoreTokens())
				{
					lineData.insertLast(stk.nextToken());
				}				
				network.addVertex(lineData);
				lineData = new DSALinkedList();
				line=br.readLine();
			}			
			fstr.close();
		}
		catch (IOException e)
		{
			System.out.println("error" + e.getMessage());
		}
		return network;		
	}
	//export to the csv file
	public void saveToCsv(String fileName, SocialSimClass socialSim, String mode) //to use in interactive mode
	{
		FileOutputStream fst;
		PrintWriter writer;
		try 
		{	
			if (mode.equals("-s") == true)
			{
				fileName = "save.txt";
			}
			fst = new FileOutputStream(fileName);
			writer = new PrintWriter(fst);					
			if (mode.equals("-i")==true)
			{
				writer.println( socialSim.displayAllData());
			}
			if (mode.equals("-s")==true)
			{
				Iterator socialSimIt = socialSim.getNetwork().iterator();
				DSAGraphVertex cursor;
				while (socialSimIt.hasNext() == true)
				{
					cursor = (DSAGraphVertex) socialSimIt.next();
					if (cursor.hasNewPost() != null) // there still new post
					{	
						socialSim.incTimeStep();					
						writer.println(" ------- Time Step : " + socialSim.getTimeStep());				
						writer.println(socialSim.update());							
						writer.println("\n------Final data : \n" + socialSim.displayAllData());
					}								
				}		
			}
			writer.close(); //close the writer
			fst.close(); //close the file stream
		}
		catch (IOException ex)
		{
			System.out.println("erro open file " + ex.getMessage());
		}
	}
	public void saveToCsv(SocialSimClass socialSim, String mode) //to use in simulation mode
	{
		FileOutputStream fst=null;
		PrintWriter writer=null;
		String fileName;
		try 
		{	
				fileName = "save.txt";
			
				fst = new FileOutputStream(fileName);
				writer = new PrintWriter(fst);							
			
					for (int i = 0 ; i < 5; i ++ )
					{
							socialSim.incTimeStep();												
							writer.println(" ------- Time Step : " + socialSim.getTimeStep());											
							writer.println(socialSim.update());	
					}
			writer.println(socialSim.showPopularPost());
			writer.println(socialSim.getNetwork().orderFollowByCount());
			writer.println("\n------Final data : \n" + socialSim.displayAllData());
			
			writer.close(); //close the writer
			fst.close(); //close the file stream
		}
		catch (IOException ex)
		{
			System.out.println("erro open file " + ex.getMessage());
		}
	}
	
	/* below method is for the purpose of testing Graphharness Only AS REQUIRE TO HAVE ALL test Harness for each file*/
	public DSAGraph loadFromFile()
	{
		FileInputStream fstr;
		InputStreamReader ir;
		BufferedReader br;
		DSALinkedList lineData = new DSALinkedList();
		DSAGraph socialSim = new DSAGraph();
		try
		{							
		Scanner sc = new Scanner(System.in);
		System.out.println("enter file name ");
		String fileName = sc.next();
			fstr = new FileInputStream(fileName);
			ir = new InputStreamReader(fstr);
			br = new BufferedReader(ir);
			String line ;			
			StringTokenizer stk;
			line = br.readLine();
			while (line != null)
			{
				stk = new StringTokenizer(line,":");
				while (stk.hasMoreTokens())
				{
					lineData.insertLast(stk.nextToken());
				}				
				socialSim.addVertex(lineData);
				lineData = new DSALinkedList();
				line=br.readLine();
			}			
			fstr.close();
		}
		catch (IOException e)
		{
			System.out.println("error" + e.getMessage());
		}
		return socialSim;		
	}/* for running GraphHarness only */
	
}