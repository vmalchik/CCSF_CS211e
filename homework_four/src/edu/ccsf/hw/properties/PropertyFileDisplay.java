package edu.ccsf.hw.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

public class PropertyFileDisplay
{
	public static void main(String[] args) throws IOException
	{
		System.out.println("Enter file: ");
		Scanner sc = new Scanner(System.in);
		String file = sc.nextLine();
		File f = new File(file);
		FileInputStream fis = new FileInputStream(file);
		Properties p = new Properties();
		p.load(fis);
		fis.close();
		//C:\Program Files (x86)\Java\jre6\lib\calendars.properties
		Set<String> keys = p.stringPropertyNames();
		Iterator<String> k = keys.iterator();
		while(k.hasNext())
		{
			String propertyKey = k.next();
			System.out.print(propertyKey + " = ");
			System.out.println(p.getProperty(propertyKey));
		}
		//File tmp = new File("prop");
		//OutputStream os = new FileOutputStream(tmp);
		//p.storeToXML(os, "test");
		
		Properties sys = System.getProperties();
		System.out.println("**System Properties**");
		Set<String> keysS = sys.stringPropertyNames();
	
		Iterator<String> sysK = keysS.iterator();
		while(sysK.hasNext())
		{
			String propertyKey = sysK.next();
			System.out.print(propertyKey + " = ");
			System.out.println(sys.getProperty(propertyKey));
		}
		
	}
}
