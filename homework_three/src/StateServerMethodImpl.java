/**
 * Name: Victor Malchikov
 * Homework: 3
 * File: StateServerMethodImpl.java
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StateServerMethodImpl extends UnicastRemoteObject 
                                   implements StatesGameInterface
{
	//used to store States of US_States.txt file
	private ArrayList<String> states = new ArrayList<String>(); 
	//used to store Capitals of US_States.txt file
	private ArrayList<String> capitals = new ArrayList<String>(); 
	//array used to obtain and return results to user
	private ArrayList<String> tmp;
	private Pattern pat; //used to obtain regex from user
	private Matcher mat; //uses pat to obtain states / capitals based on regex
	
	//******************************StateServerMethodImpl()*************************
	//default constructor
	public StateServerMethodImpl() throws RemoteException, FileNotFoundException
	{ 
		super();
		//store file into map
		File file = new File("US_states.txt");
		//store file contents into Map
		Scanner sc = new Scanner(file);
		String[] tmp;
		int counter = 0;
		while(sc.hasNext())
		{
			//split the string into States and Capitals
			tmp = sc.nextLine().split("\\s{2,}");
			if(counter > 2)
			{
				//store them into Map
				states.add(tmp[0]);
				capitals.add(tmp[1]);
			}
			counter++;
		}
		sc.close(); //close scanner when done	
	}
	
	//*********************************getState()***********************************
	@Override
	public String[] getState(String regex) throws RemoteException
	{
		//tmp will contain any states that contain the given pattern 
		tmp = new ArrayList<String>();
		//pat will obtain pattern from user 
		pat = Pattern.compile(regex);
		//loop over states and find any that contain pattern
		for(int i = 0; i < states.size(); i++)
		{
			//mat will check if pattern is in the string 
			mat = pat.matcher(states.get(i));
			if(mat.find())
				tmp.add(states.get(i)); //store state 

		}
		//check if any states were found 
		if(tmp.isEmpty())
		{
			tmp.add("No matches found."); 
			return (String[])tmp.toArray(new String[tmp.size()]); //return info
		}
		else
		{
			return (String[])tmp.toArray(new String[tmp.size()]); //return info
		}
	}
	
	//***********************************getCapital()*******************************
	@Override
	public String[] getCapital(String regex) throws RemoteException
	{
		//tmp will contain any capitals that contain the given pattern 
		tmp = new ArrayList<String>();
		//pat will obtain pattern from user
		pat = Pattern.compile(regex);
		//loop over capitals and find any that contain pattern
		for(int i = 0; i < capitals.size(); i++)
		{
			//mat will check if pattern is in the string 
			mat = pat.matcher(capitals.get(i));
			if(mat.find())
				tmp.add(capitals.get(i)); //store capital
		}
		if(tmp.isEmpty())
		{
			tmp.add("No matches found.");
			return (String[])tmp.toArray(new String[tmp.size()]); //return found
		}
		else
		{
			return (String[])tmp.toArray(new String[tmp.size()]); //return found
		}
	}
	
	//**********************************toString()**********************************
	public String toString()
	{
		//obtain info from states and capitals container's 
		String str = "";
		for(int i = 0; i < states.size(); i++)
			str += states.get(i) + " - " + capitals.get(i) +"\n";
		return str;
	}
	
}
