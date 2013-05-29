/**
 * Name: Victor Malchikov
 * Homework: 3
 * File: StatesGameInterface.java 
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StatesGameInterface extends Remote
{
	/**Method that can be used to obtain all States based on regular expression
	 *        user passed into parameter.
	 * @param regex is a regular expression that will be used to obtain States 
	 * @return a String array that contains results based on user's request
	 */
	public String[] getState(String regex) throws RemoteException;
	
	/**Method that can be used to obtain all Capitals based on regular expression
	 *        user passed into parameter.
	 * @param regex is a regular expression that will be used to obtain Capitals 
	 * @return a String array that contains results based on user's request
	 */
	public String[] getCapital(String regex) throws RemoteException;
}
