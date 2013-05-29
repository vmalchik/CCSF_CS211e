/**Name: Victor Malchikov
 * Class: CS211E
 * Prof: Abbas Moghtanei
 * Homework: 5
 * File: JCommandLineDemo.java 
 */
package edu.ccsf.hw;

import java.io.IOException;
import java.util.Scanner;

public class JCommandLineDemo
{
	public static void main(String[] args) throws IOException
	{
		//create object to process command line commands 
		JCommandLineProcessor jcl = new JCommandLineProcessor();
		
		//use scanner to read in user commands
		Scanner sc = new Scanner(System.in);
		
		//obtain system shell
		String shell = jcl.getShell();
		
		//use stop boolean to allow user to stop the program
		boolean stop = false;
		
		//prompt the user 
		System.out.println("Loading shell. Type shell commands. \n"
                +"When done type \"stop\".");
		
		//start command line interaction 
		while(!stop)
		{
			//prompt the user after each command
			System.out.println("Type shell command or \"stop\" to exit.");
			
			//show the user what shell they are using
			System.out.print(shell+": ");
			
			//obtain user command
			String tmp = sc.nextLine();
			
			//check if command was to stop the application
			if(tmp.equals("stop"))
			{
				stop = true;
				continue;
			}
			
			//obtain process output based on executed command
			String[] out = jcl.system(tmp);
			
			//if output does not contain any data continue 
			if(out == null)
				continue;
			//print output from the process 
			for(int i = 0; i < out.length; i++)
				System.out.println(out[i]);
		}
		
		
	}
}
