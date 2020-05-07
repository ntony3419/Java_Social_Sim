import java.util.*;
public class SocialSimHarness 
{
	public static void main(String[] argv)
	{
		
		System.setProperty("gs.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		
		//this class will run the socialSim
		//create new network
		System.out.println("\nChoose run type '1' for NORMAL run  ||  '2' for ASSERT run");
		Scanner sc = new Scanner(System.in);
		String choice = sc.next();
		if (choice.equals("1"))
		{
			SSUserInterface userInterface = new SSUserInterface();
			userInterface.run(argv);			
		}
		if (choice.equals("2"))
		{
					//create FileIO object to use method in fileIo of the graph
			FileIO fileIO = new FileIO(); 
			SocialSimClass socialSim = new SocialSimClass(); //create the socialSIm
			//DSAGraph network = socialSim.getNetwork(); //get the network out of the socialSim for other operation
			//a do while loop to loop the menu until an exit is imported
			System.out.println("\t ----- Loading network ----- \n");
				//use loadFromFile() from FileIO to load the network 		
			socialSim = fileIO.loadFromFile("network.txt");
			System.out.println("\t ----- Network Loaded ----- \n");
			System.out.println("\t ----- Load probabilities ----- \n");
			System.out.println("Closer to 0.0 the lower chance of connection. Closer to 1.0 : more chance to like and follow");
			double like_pos = 0.5;
			double fol_pos = 0.5;
			//set the pos to the socialSim
			socialSim.setLikePos(like_pos);
			socialSim.setFoPos(fol_pos);
			assert "0.5".equals(socialSim.getLikePos());
			assert "0.5".equals(socialSim.getFoPos());
			System.out.println("\t ----- Probabilities Loaded ----- \n");
			System.out.println("\t ----- Start Node operation ----- \n");
			System.out.println("implementing ");
			//call method nodeOperation to perform task on network for node
			if (socialSim.isEmpty()==true)
			{
				System.out.println("empty load network first ");
			}
			else
			{
				System.out.println("test find Node");
				assert "Kira".equals(socialSim.getNetwork().findVertex("Kira").getLabel());
				System.out.println("test deleteNode");
				assert "Kira".equals(socialSim.getNetwork().delete("Kira").getLabel());
				System.out.println("test insert node");
				socialSim = fileIO.loadFromFile("network.txt");
				assert "Kira".equals(socialSim.getNetwork().findVertex("Kira").getLabel());
			}
			System.out.println("\n test load post ");
			String fileName = "post.txt";
			socialSim = fileIO.loadPost(socialSim, fileName);
			DSAGraphVertex owner = socialSim.getNetwork().findVertex("Master");
			System.out.println("the post  " +((DSAPost) owner.hasNewPost()).getPost());
			System.out.println("\n end test load post ");
		}
	}
}