/**Name: Victor Malchikov
 * Class: CS211E
 * Prof: Abbas Moghtanei
 * Quiz: 1
 * File: FileLoader.java
 * Description: This is a class that has a static method loadfile() that can be
 *              used to obtain contents of a file that resides on a local system
 *              or remotely. If the file resides remotely then the full URL must
 *              be provided. If the file resides locally then an absolute path
 *              should be provided.
 */

package edu.ccsf.quiz;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileLoader 
{
	//*****************************FileLoader()*************************************
	public FileLoader()
	{ /*default constructor */ }
	
	//*****************************loadfile()***************************************
	public static String[] loadfile(String filePath) throws IOException
	{
	
		URL url; //used to check if string is a url
		File file; //used to check if string is a local file 
		Scanner sc; //used to read contents of the file
		ArrayList<String> text = new ArrayList<>();
		
		//check if URL
		try 
		{
			 //create URL object - Throws MalformedURLException
			 url = new URL(filePath);
			 //establish connection - Throws UnknownHostException  
			 URLConnection uc = url.openConnection();
			 uc.connect();
			 //check data type - Throws IOException if not text file
			 String dataType = uc.getContentType();
			 if(!dataType.contains("text/plain"))
				 throw new IOException(url.getFile()+" "+"Unnable to access file or"
			                                            +" not a text file." );
			 //obtain data from text file 
			 sc = new Scanner(url.openStream());
			 while(sc.hasNextLine())
			 {
				 text.add(sc.nextLine());
			 }
			 //return data 
			 String[] tmp = new String[text.size()];
			 return text.toArray(tmp);
		}
		catch (MalformedURLException | UnknownHostException e ) 
		{ /* do nothing --> check if local file*/ }
		
		//create file object
		file = new File(filePath);
		
		//check if file and if it is readable 
		if(file.exists() && file.isFile() && file.canRead())
		{
			//load file into container 
			sc = new Scanner(file);
			while(sc.hasNextLine())
			{
				text.add(sc.nextLine());
			}
			//return data
			String[] tmp = new String[text.size()];
			return text.toArray(tmp);
		}
		
		//default return null
		return null;
	}
}
