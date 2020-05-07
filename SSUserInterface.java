import java.util.*;
public class SSUserInterface 
{
	//this class will hold all the menu operation call
	//this class will hold the menu and usage
	public void menu()
	{
		System.out.println("\nChoose a menu : \n1. Load Network.\n" +
							"2. Set probabilities \n" + 
							"3. Node operations (find, delete, insert)\n" +
							"4. Edge operations (like / follow - add , remove)\n"+
							"5. New Post\n"+ 
							"6. Display network\n"+
							"7. Display statistics\n"+
							"8. Update (run a timestep)\n"+
							"9. Save Network\n\n"+
							"-- extra functions --\n\n"+
							"10. Print graph Edge\n"+
							"11. Print specific vertex Edge\n"+	
							"12. Display the SocialSim data\n"+										
							"15. Exit\n");
	}
	public void usage()
	{
		System.out.println("not enough argument. \n\n" +
								"Please run the program in the format: \n"+
								"For Iteractive mode: \n"+
								"\t\tSocialSim -i\n"+
								"For simulation mode:\n"+
								"\t\tSocialSim -s netfile eventfile probabilities_like probabilities_foll \n"+
								"\te.g.    SocialSim -s test.txt eventfile 0.5 0.6\n");
	}
	//main method to operate the socialsim
	public void run(String[] argv)
	{
		//create a userInterface to get menu and usage from UserInterfaceClass
		//create FileIO object to use method in fileIo of the graph
		FileIO fileIO = new FileIO(); 
		SocialSimClass socialSim = new SocialSimClass(); //create the socialSIm
		Scanner sc = new Scanner(System.in);
		String input = "";
		//a do while loop to loop the menu until an exit is imported
		if (argv.length < 1)
		{
			//show the usage befor shutdown
			usage();
			throw new IllegalArgumentException("not enough argument. read the usage above");
		}
		if (argv[0].equals("-i") == true) //iteration mode
		{
			do
			{
				menu();
				System.out.print("Selection : ");
				input = sc.next();
				switch (input)
				{
					case "1": //load network from file
						System.out.println("enter vertex");
						DSALinkedList data = fileIO.terminalImport();
						
						socialSim.getNetwork().addVertex(data);
						/*
						System.out.println("\t ----- Loading network ----- \n");
						//use loadFromFile() from FileIO to load the network 		
						String file = fileIO.getFileName();						
						socialSim = fileIO.loadFromFile(file);
						System.out.println("\t ----- Network Loaded ----- \n");
						*/
					break;
					case "2":
						System.out.println("\t ----- Load probabilities ----- \n");
						System.out.println("Closer to 0.0 the lower chance of connection. Closer to 1.0 : more chance to like and follow");
						System.out.println("enter Like posibility: ");
						double like_pos = sc.nextDouble();						
						System.out.println("enter follow posibility: ");
						double fol_pos = sc.nextDouble();
						//set the pos to the socialSim
						socialSim.setLikePos(like_pos);
						socialSim.setFoPos(fol_pos);
						System.out.println("the like pos is : "+socialSim.getLikePos());
						System.out.println("the follow pos is : " + socialSim.getFoPos());
						System.out.println("\t ----- Probabilities Loaded ----- \n");
					break;
					case "3":
						System.out.println("\t ----- Start Node operation ----- \n");
						
						
						
						System.out.println("implementing ");
						//call method nodeOperation to perform task on network for node
						if (socialSim.isEmpty()==true)
						{
							System.out.println("empty load network first ");
						}
						else
						{
							socialSim.nodeOperation();
						}
						System.out.println("\t ----- End Node Operation ----- \n");
					break;
					case "4":
						System.out.println("\t ----- Start Edge operation ----- \n");
						if (socialSim.isEmpty()==true)
						{
							System.out.println("empty load network first ");
						}
						else
						{
							socialSim.edgeOperation();	
						}
						System.out.println("\t ----- End Edge Operation ----- \n");					
					break;
					case "5":
						if (socialSim.isEmpty() == true)
						{
							System.out.println("the network is empty . run option 1 ");
						}
						else
						{	
							System.out.println("- Manually input only accept data for post. \n-Automatically input will accept an event file\nDo you want to manually input the data (Y OR N)? ");
							String choice = sc.next();
							if ((choice.toUpperCase()).equals("Y"))
							{
								//input the data
								DSALinkedList lineData = fileIO.terminalImport();;
								// check the data
								Object extract;
								extract = lineData.removeFirst();
								if (String.valueOf(extract).equals("P") == false) 
								{
									throw new IllegalArgumentException("Must use P for post.");
								}
								else //fist item is a P
								{
									socialSim.addPost(lineData); //pass the other data into the methods
									
								}
							}
							else if  ((choice.toUpperCase()).equals("N"))
							{
								//get from file
								String fileName = fileIO.getFileName();
								
								socialSim = fileIO.loadEventFile(fileName, socialSim);								
							}
							else
							{
								System.out.println("incorrect choice - return to main menu");
							}
						}
					break;
					case "6":
						//network.displayAsList();
						if (socialSim.isEmpty()==true)
						{
							System.out.println("empty load network first ");
						}
						else
						{
							System.out.println("person : following list \n");
							System.out.println(socialSim.toString());
						}
					break;
					case "7":
						System.out.println("Choose a thing to show : \n1. Post in order of popularity\n"+
											"2. People in order of popularity (have most follower)\n"+
											"3. A person's records");
						System.out.println("choose an option: ");
						int get;
						get = sc.nextInt();
						switch(get)
						{
							case 1: //dis play post in order of popurarity
								System.out.println(socialSim.showPopularPost());
							break;
							case 2://display people in order of popularity
								System.out.println(socialSim.getNetwork().orderFollowByCount());
							break;
							case 3: // display personal records
								System.out.println("enter the data to find and display");
								String label = sc.next();
								socialSim.getNetwork().displaySpecific(label);
							break;
							default:
							break;
						}
					break;
					case "8"://run time step
						if (socialSim.getLikePos() < 0)
						{
							System.out.println("set posibility first. run menu 2");
						}
						else
						{
							socialSim.incTimeStep();						
							System.out.println("time Step " + socialSim.getTimeStep());
							socialSim.update(); //update time step base oin the new post
							
							
						}
					break;
					case "9":
						System.out.println("Saving the network to file csv");
						System.out.println("enter file name ");
						String fileName = sc.next();						
						fileIO.saveToCsv(fileName, socialSim, argv[0]);//argv[1] is the running mode -i or -s
					break;
					case "10"://print the graph edge list
						DSALinkedList eList = (socialSim.getNetwork()).getGraphEdges();
						if (eList.isEmpty() == false)
						{
							Iterator eListIt = eList.iterator();
							DSAGraphEdge cursor;
							while (eListIt.hasNext() == true)
							{
								cursor = (DSAGraphEdge) eListIt.next();
								System.out.println("the edge list : "+cursor);
							}
						}
						else 
						{
							System.out.println("there is no edge in the graph");
						}
					break;
					case "11":
						System.out.println("enter the data to find and display");
						String label = sc.next();
						socialSim.getNetwork().displaySpecific(label);
					break;
					case "12":						
						System.out.println(socialSim.displayAllData());
					break;
					case "15":
						System.out.println("exit");
					break;
				}
			}		
			while (!input.equals("15"));
		}
		else if (argv[0].equals("-s")==true && argv.length == 5) //simulation mode
		{
			socialSim = fileIO.simulation(argv);	
			System.out.println("\n-------- Popular Post : \n" + socialSim.showPopularPost());	
			System.out.println(socialSim.getNetwork().orderFollowByCount());
			System.out.println("\nA file save.txt is saved \n" );
			fileIO.saveToCsv(socialSim,argv[0]);
			
		}
		else //no argument
		{
			System.out.println("please read the usage and run the program again\n");
			usage();
			System.out.println();
		}
	}
}