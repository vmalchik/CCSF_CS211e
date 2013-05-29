/**Name: Victor Malchikov
 * Class: CS211E
 * Prof: Abbas Moghtanei
 * Homework: 4
 * File: PropertiesToXML.java 
 * Description: This is a class that can be used to create XML files of System
 *              properties based on user's needs. 
 */

package edu.ccsf.hw.properties;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class PropertiesToXML
{
	protected String root; //to be used as root tag in XML file
	
	//default constructor
	//******************************PropertiesToXML()*******************************
	public PropertiesToXML()
	{
		this.root = "properties";
	}
	
	//method that converts system properties to XML format
	//******************************toXML()*****************************************
	public void toXML() throws IOException
	{
		
		Properties p;
		
		//If properties file exists then use it. Else use system properties. 
		p = System.getProperties();
		
		//store keys in a Set container
		Set<String> keys = p.stringPropertyNames();
		//print out standard xml version 
		System.out.println("<?xml version = \"1.0\" ?>");
		
		//display root
		System.out.print("<"+root+">");
		//parse and print each key/value
		Iterator<String> elements = keys.iterator();
		while(elements.hasNext())
		{
			//get key
			String key = elements.next();
			
			//get value associated with key
			String value = p.getProperty(key);
			
			//create XML tag with proper nesting and tab spacing
			if(key.startsWith("java"))
			{
				//call helper method to display key and value following XML format
				display(key, value, "  ");
			}
			else
				continue; //used when user wants specific elements in file displayed
			
		}
		//close root
		System.out.print("\n</"+root+">\n");
	}
	
	//private helper method that recursively prints out the elements in XML format
	//******************************display()***************************************
	private void display(String key, String value, String indent)
	{
		//create tag via key parsing
		String tag =key;
		if(key.contains("."))
			tag= key.substring(0, key.indexOf("."));
		
		//print opening tag
		System.out.print("\n"+indent+"<"+tag+">");
		
		//recursive call to continue to display in xml format
		if(key.contains("."))
		{	
			//print next tag
			display(key.substring(key.indexOf(".")+1), value, indent+"  ");
		}
		else
		{   //print value associated with key
			System.out.print(" "+value+" ");
			//print closing tag
			System.out.print("</"+tag+">");
			return;
		}
		
		//print closing tag
		System.out.printf("\n"+indent+"</"+tag+">");
	}
	
	//******************************toString()**************************************
	public String toString()
	{
		//print root tag and what properties file is being used
		String str = "XML root tag: <"+root+">";
		str += "\nProperties: System Properties";

		return str;
	}
	
}
