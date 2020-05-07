import java.util.*;

class QueueTestHarness
{
	public static void main(String[] arg)
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("choose type of queue to create \n 1. Shuffle Queue 2.Circular Queue: ");
		int inputType = sc.nextInt();
		
		switch (inputType)
		{
			case 1 : 
				System.out.println("enter size for the Queue: ");
				int iSize = sc.nextInt();	
				
				DSAQueue oQueue = new DSAQueue();
				
				// check if the queue is empty	
				
				
				
				String result;
			if (iSize == 6)
			{
				oQueue.enqueue("a");
				oQueue.enqueue("b");
				oQueue.enqueue("c");
				oQueue.enqueue(4);
				oQueue.dequeue();					
				
				oQueue.enqueue(5);
				oQueue.dequeue();
				oQueue.enqueue(6);								
				//   c 4 5 6
				result = oQueue.toString();	
				System.out.println(" result 1 is " + result);		
				assert "[ c 4 5 6 ]".equals(result): "Size 6: value remove out: b , front item: c" ;
			}
			if (iSize == 4)
			{
				oQueue.enqueue(1);
				oQueue.enqueue(2);
				oQueue.enqueue(3);
				oQueue.enqueue(4);
				oQueue.dequeue();					
				
				oQueue.enqueue(5);
				oQueue.dequeue();
				oQueue.enqueue(6);								
				oQueue.enqueue(7);
				oQueue.dequeue();
				oQueue.enqueue(8);								
				//   4 5 6 8 
				 result = oQueue.toString();	
				System.out.println(" result 2 is " + result);				
				assert "[ 4 5 6 8 ]".equals(result): "Size 4: value remove out: 1 , front item: 2" ;
			}
			if (iSize == 1)
			{
				oQueue.enqueue(1);
				oQueue.enqueue(2);
				oQueue.enqueue(3);
				oQueue.enqueue(4);
				oQueue.dequeue();					
				
				oQueue.enqueue(5);
				oQueue.dequeue();
				oQueue.enqueue(6);								
				oQueue.enqueue(7);
				oQueue.dequeue();				
				oQueue.dequeue();
				oQueue.enqueue(8);								
				oQueue.dequeue();
				     
				//         8
				 
				 result = oQueue.toString();	
				System.out.println(" result 3 is " + result);				
				assert "".equals(result): "size 3: value remove out : b, front item: c Full message" ;
			}
				
			break; // end case 1
			case 2:		
				System.out.println("enter size for the Queue: ");
				int cirSize = sc.nextInt();	
				
				DSAQueue oCirQueue = new DSAQueue();
				
				
				if (cirSize == 4)
				{
					oCirQueue.enqueue(1);
					oCirQueue.enqueue(2);
					oCirQueue.enqueue(3);
					oCirQueue.enqueue(4);
					oCirQueue.dequeue();
					oCirQueue.enqueue(5);
					oCirQueue.enqueue(6);
					oCirQueue.enqueue(7);
					oCirQueue.dequeue();
					oCirQueue.enqueue(8);
					oCirQueue.dequeue();
					oCirQueue.enqueue(9);
					oCirQueue.dequeue();
						
					String cirRes = oCirQueue.toString();
					System.out.println(" result test by system.out.print: " + cirRes);					
					assert "589".equals(cirRes):"error";
				}
				
				if (cirSize == 6)
				{
					oCirQueue.enqueue(1);
					oCirQueue.enqueue(2);
					oCirQueue.enqueue(3);
					oCirQueue.enqueue(4);
					oCirQueue.dequeue();
					oCirQueue.enqueue(5);
					oCirQueue.enqueue(6);
					oCirQueue.enqueue(7);
					oCirQueue.dequeue();
					oCirQueue.enqueue(8);
					oCirQueue.dequeue();
					oCirQueue.enqueue(9);
					oCirQueue.dequeue();
								oCirQueue.dequeue();
								oCirQueue.dequeue();
								oCirQueue.dequeue();
									oCirQueue.dequeue();
								oCirQueue.dequeue();
								oCirQueue.dequeue();
					String cirRes = oCirQueue.toString();
					System.out.println(" result test by system.out.print: " + cirRes);					
					assert "[ 5 8 9 ]".equals(cirRes):"error";
				}
	
			break; //end case 2
			default:
			break;
		}
		
		
	}
}