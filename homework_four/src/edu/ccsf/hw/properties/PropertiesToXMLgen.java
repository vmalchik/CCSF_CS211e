/**Name: Victor Malchikov
 * Class: CS211E
 * Prof: Abbas Moghtanei
 * Homework: 4
 * File: PropertiesToXML.java 
 * Description: This is a class that can be used to create XML files of either a 
 *              property file or System properties based on user's needs. If a
 *              file passed in with wrong extension then an exception is thrown.
 */

package edu.ccsf.hw.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class PropertiesToXMLgen
{
	protected String root; //to be used as root tag in XML file
	protected File properties; //can be used instead of default System properties
	
	//default constructor
	//******************************PropertiesToXML()*******************************
	public PropertiesToXMLgen()
	{
		this.root = "properties";
	}
	
	//constructor that sets the root based on user input
	//******************************PropertiesToXML()*******************************
	public PropertiesToXMLgen(String root)
	{ 
		this.root = root;
	}
	
	//constructor that accepts properties file to be used for XML
	//******************************PropertiesToXML()*******************************
	public PropertiesToXMLgen(File file) throws IOException
	{
		this();
		//check that file contains correct extension 
		if(!file.toString().endsWith(".properties"))
			throw new IOException("File must contain .properties extension");
		this.properties = file;
		
	}
	
	//constructor that sets the root and properties file to be used for XML
	//******************************PropertiesToXML()*******************************
	public PropertiesToXMLgen(File file, String root) throws IOException
	{
		this(root);
		//check that file contains correct extension 
		if(!file.toString().endsWith(".properties"))
			throw new IOException("File must contain .properties extension");
		this.properties = file;
	}
	
	//method to allow user set what the XML root tag will be
	//******************************setRoot()***************************************
	public void setRoot(String root)
	{
		this.root = root;
	}
	
	//method to set file to be used to for XML 
	//******************************setFile()***************************************
	public void setFile(File file)
	{
		properties = file;
	}
	
	//method that converts any properties file OR system properties to XML format
	//******************************toXML()*****************************************
	public void toXML(boolean printAll) throws IOException
	{
		
		Properties p;
		
		//If properties file exists then use it. Else use system properties. 
		if(properties != null)
		{
			p = new Properties();
			//read in file input 
			FileInputStream fis = new FileInputStream(properties);
			p.load(fis);
		}
		else
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
			if(key.startsWith(root))
			{
				key = key.substring(key.indexOf(".")+1);
				//call helper method to display key and value following XML format
				display(key, value, "  ");
			}
			else if(printAll)
				display(key, value, "  ");
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
		if(properties == null)
			str += "\nProperties: System Properties";
		else
			str += "\nProperties: "+properties.toString();
		
		return str;
	}
	
}
