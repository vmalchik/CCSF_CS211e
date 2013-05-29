import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class SanityCheck 
{
	public static void main(String args[]) throws RemoteException, FileNotFoundException
	{
		StateServerMethodImpl obj = new StateServerMethodImpl();
	
		System.out.println(obj);
		String[] t = obj.getCapital("California");
		for(int i = 0; i < t.length; i++)
			System.out.println(t[i]);
		UnicastRemoteObject.unexportObject(obj, true); //otherwise app keeps running
		
		//System.exit(0);
	}
}
