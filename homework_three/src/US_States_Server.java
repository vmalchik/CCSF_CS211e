/**
 * Name: Victor Malchikov
 * Class: CS211E
 * Prof: Abbas Moghtanei
 * Homework: 3
 * File: US_States_Server.java 
 * Description: Program that creates an object that extends UnicastRemoveObject
 *              implements StatesGameInterface methods. This object is exported 
 *              into RMI registry and is associated with USStatesServer name. 
 *              Clients that contain the stub and know what methods to invoke can
 *              obtain names of States and Capitals remotely using regex.   
 */

import java.io.FileNotFoundException;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class US_States_Server
{
	public static void main(String[] args)
	{
		//check security 
		if(System.getSecurityManager() == null)
			System.getSecurityManager();
	   
	   //provide info about server
		System.out.println("Constructing server implementation...");
		try
		{
			//create StateServerMethodImpl object 
			StateServerMethodImpl ssmi = new StateServerMethodImpl();
			
			//make StateServerMethod's available
			try
			{
				UnicastRemoteObject.unexportObject(ssmi, true);
			}
			catch(NoSuchObjectException e) 
			{ /* do nothing if object was not in registry */ }  
			
			//obtain registry from port:4001 to avoid conflict with other students
			Registry reg = LocateRegistry.getRegistry(4001);
			//obtain stub after setting up object to be available for incoming calls
			StatesGameInterface stub;
			stub = (StatesGameInterface) UnicastRemoteObject.exportObject(ssmi, 0);
			//bind registered name with stub 
			reg.rebind("USStatesServer", stub);
			//prompt server is ready
			System.out.println("USStatesServer ready");
		}
		catch(RemoteException | FileNotFoundException  e)
		{
			e.printStackTrace();
		}
	}

}
