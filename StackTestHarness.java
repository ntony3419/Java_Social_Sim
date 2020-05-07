import java.util.*;

class StackTestHarness
{
	public static void main(String[] args)
	{
		System.out.println("enter the size for the stack (integer required): ");
		Scanner sc = new Scanner(System.in);
		String sInputString = sc.next();		
		
		DSAStackClass stack = new DSAStackClass(); //create the stack		
		


			stack.push(1);
			
			stack.push(2);
			//		stack.pop();
			stack.push(3);
			//stack.pop();
		
			stack.push(4);
			stack.push(5);		
			stack.pop();
			stack.pop();
			stack.pop();
			stack.pop();
			
			Object top = stack.top();
		String result =  stack.toString();		
		System.out.println("The stack after the full test \n" + result);
		System.out.println("the top : " + top);
			System.out.println("the value of the pop "+ stack.getPopVal());		
		
		
		
	//	System.out.println("\n test by assert\n\n");
		//assert "[ 1 2 3 4 5 b ]".equals(result);
		
		
//		stack.pop();
		
	//	System.out.println("result for pop 1 time: " + stack.show());
		
	}
	
	
}