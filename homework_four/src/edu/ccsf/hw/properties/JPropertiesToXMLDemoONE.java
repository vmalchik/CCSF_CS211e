/**Name: Victor Malchikov
 * Class: CS211E
 * HW: 4
 * File: JPropertiesToXMLDemoONE
 */

package edu.ccsf.hw.properties;

import java.io.IOException;

public class JPropertiesToXMLDemoONE 
{
	public static void main(String[] args) throws IOException
	{
		//create properties objcet 
		PropertiesToXMLgen p = new PropertiesToXMLgen();
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
