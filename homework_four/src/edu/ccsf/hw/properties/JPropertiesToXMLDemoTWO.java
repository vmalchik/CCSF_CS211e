/**Name: Victor Malchikov
 * Class: CS211E
 * HW: 4
 * File: JPropertiesToXMLDemoTWO
 * Description: This demo uses example.properties file. 
 */

package edu.ccsf.hw.properties;

import java.io.File;
import java.io.IOException;

public class JPropertiesToXMLDemoTWO 
{
	public static void main(String[] args) throws IOException
	{
		//use properties file to show PropertiesToXML class functionality 
		File f = new File("example.properties");
		//create properties objcet 
		PropertiesToXMLgen p = new PropertiesToXMLgen(f);
		//check if any command line arguments were passed
		if(args.length == 1)
		{
			p.setRoot(args[0]);
			p.toXML(false);
		}
		else
			p.toXML(true);

	}
}