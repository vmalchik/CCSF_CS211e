/**Name: Victor Malchikov
 * Homework: 3
 * File: US_States_Client.java
 */
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Enumeration;
import java.util.Scanner;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;

public class US_States_Client
{
	public static void main(String[] args)
	{
	   //use port 4001 to avoid conflict with other students 
		String url = "rmi://hills.ccsf.edu:4001/USStatesServer";
		try 
		{
			//List all registry bindings 
			Context namingContext;
			try
			{
				System.out.println("List of registered objects:");
				namingContext = new InitialContext();
				//obtain list of bindings 
				Enumeration<NameClassPair> e;
				e = namingContext.list("rmi://hills.ccsf.edu:4001/");
				//print name list of bindings 
				while(e.hasMoreElements())
					System.out.println(e.nextElement().getName());
			}
			catch (NamingException e1) 
			{
				e1.printStackTrace();
			}
				
			//obtain stub
			StatesGameInterface stub = (StatesGameInterface) Naming.lookup(url);
			Scanner sc = new Scanner(System.in);
			//obtain user request 
			while(true)
			{
				//prompt user of options 	
				System.out.println("Enter type one: state/capital/quit");
				String input = sc.nextLine();
				//exit program 
				if(input.equals("quit"))
					System.exit(0);
				else if(input.equals("state")) //get states 
				{
					System.out.println("Enter State regex: ");
					String tmp = sc.nextLine();
					String[] states = stub.getState(tmp);  //send request 
					for(int i = 0; i < states.length; i++) //print results 
						System.out.println(states[i]);
				}
				else if(input.equals("capital")) //get capitals 
				{
					System.out.println("Enter Capital regex: "); 
					String tmp = sc.nextLine();
					String[] capitals = stub.getCapital(tmp); //send request
					for(int i = 0; i < capitals.length; i++)  //print results 
						System.out.println(capitals[i]);
				}
				else
				   System.out.println("Invalid entry.");
			}
		}
		catch (RemoteException | NotBoundException | MalformedURLException e)
		{
			e.printStackTrace();
		}

	}
}
