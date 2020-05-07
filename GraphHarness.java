import java.util.*;
public class GraphHarness
{
	public static void main(String[] args)
	{
		FileIO io = new FileIO();
		Scanner sc = new Scanner(System.in);
		int choice;
		DSAGraph myGraph = new DSAGraph();
		// initialize a graph
		do 
		{			
			menu();		
			choice = sc.nextInt();
			switch (choice)
			{
				case 1: // data is prepare below
					myGraph = normalTest();
				break;//end normal test
				case 2: //create graph from csv file
					myGraph = getFromFile();		
				break; //end create graph from csv file
				case 3: //create graph from serial file
					System.out.println("not implement");
				break;// end create graph from serial file
				case 4:// save graph to csv file
					System.out.println("not implement");
				break;	// end save graph to 
				case 5: // save graph to the serialized file
					System.out.println("not implement");
				break; //end case 5 save graph to serializ file
				case 6: // display the graph in the terminal			
					 System.out.println(myGraph.toStringAsList());
					
				break; //end display the graph in the terminal
				case 7: //exit
					System.out.println("method one ");
					myGraph.stringDepthFirstSearch();
					
					//method 2
					System.out.println("\n\n Method two (using Assertion test)");
					String result = "";
					result = myGraph.stringDepthFirstSearch();	
					if (result == "{}")
					{
						assert "{}".equals(result);
					}
					else
					{
						assert "{{A,B},{B,C},{C,D},{C,E},}".equals(result);
					}
					
				break;
				case 8:// breadth first search
				
					//method one: 
					System.out.println("method one ");
					myGraph.stringBreadthSearch();
					//Method 2:
					System.out.println("\n\n Method two (using Assertion test)");
					String result2= "";
					result2 = myGraph.stringBreadthSearch();
					if (result2 == "{}")
					{
						assert "{}".equals(result2);
					}
					else
					{
						assert "{{A,B},{A,D},{A,E},{B,C},}".equals(result2);
					}
					
				break;//end breadth first search
				case 9://add edge					
					myGraph = io.loadEdge(myGraph);			
					
				break;//end breadth first search
				case 14: //arange
					myGraph.displayArrangedVertex();
				break;
				case 15: //exit
					System.out.println("exit");
				break; //end exit
					
			}
		
		}
		while ( ! (String.valueOf(choice).equals("15")) );
	}
	
	//menu
	public static void menu()
	{
		System.out.println("\nchoose an operation for the graph\n" + 
							"1. Create graph from inner data\n" +
							"2. Create graph from csv file\n"+
							"3. Create graph from serialized file\n"+
							"4. Save graph to csv file (save as List)\n" +
							"5. Save graph to serialized file\n" +
							"6. Display graph in the terminal\n"+
							"7. Display graph in Depth First Search Mode\n"+
							"8. Display graph in Breadth First Search Mode\n"+
							"9. Add edge\n"+
							"Extra Method \n"+
							"14. Rearange the vertices of the graph and display the vertices in order\n"+
							"15. exit\n");
	}
	
	public static DSAGraph getFromFile()
	{
		DSAGraph inGraph = new DSAGraph();
		FileIO io = new FileIO();
		
		
		inGraph = io.loadFromFile();
		return inGraph;
	}
	
	// this is where the normal test perform
	public static DSAGraph normalTest()
	{
		
		FileIO io = new FileIO();
		System.out.println ("graph is created and initialized");
		
		/* perform test at graph empty*/
		DSAGraph inGraph = new DSAGraph();			
			//display graph 
	
		
			// delete vertex
		
			// delete edge
		
		//add vertex manually
	// add vertex
		Scanner sc = new Scanner(System.in);		
		String answer;
		
		do
		{
			DSALinkedList lineData = io.terminalImport(); // get line list data of vertex's labels			
			inGraph.addVertex(lineData);
			System.out.println("do you want to continue input data? Y or N" );
			answer = sc.next();
		}
		while ( ! (answer.equals("N") ) );
		
			//System.out.println(	inGraph.toString());
			// add edge
			
			// traversal deapth AND breadth 
		
		/* initialized for other test */
	//	inGraph = new DSAGraph();		
			//add four vertex for a simple graph that connect A, B - C , D to B,C
			
			
			//perform test on a 4 edge graph
			//display graph
		
			//add another vertex
			//inGraph.addGraphNode(b);
			//add another edge
			
			//traversal deapth AND breadth
			
			// remove a vertex
			
			// remove an edge
			
			// display graph
			
		/* initialied for other test */
		//	inGraph = new DSAGraph();			
		/* add from csv */
		
			//display graph
			
			//add another vertex
			
			//add another edge
			
			//traversal deapth AND breadth
			
			// remove a vertex
			
			// remove an edge
			
			// display graph
			
		/* Write to csv file*/
			
			
		/* initialied for other test */
	//		inGraph = new DSAGraph();	
		/* add from serial file*/
		
			//display graph
			
			//add another vertex
			
			//add another edge
			
			//traversal deapth AND breadth
			
			// remove a vertex
			
			// remove an edge
			
			// display graph
			
		/* write to serial file */
	
		
			return inGraph;
			
	}
	
	
}