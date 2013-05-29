/**Name: Victor Malchikov
 * Class: CS211E
 * Prof: Abbas Moghtanei
 * Extra-Credit: 1 
 * File: JarClassCount.java 
 * Description: This program will check each jar file and count number of classes 
 *              that each contains. After each file is checked it will display the
 *              jar file that contained the most classes along with total. 
 */

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarFile;

public class JarClassCount 
{
	public static void main(String[] args) throws IOException
	{
	  //Where is the file located on hills?
	  //use following cmd: $find / -name rt.jar 2> /dev/null
	  String jar1 = "/usr/lib/jvm/java-1.6.0-openjdk-1.6.0.0.x86_64/jre/lib/rt.jar";
	  String jar2 = "/usr/lib/jvm/java-1.5.0-gcj-1.5.0.0/jre/lib/rt.jar";
	  String jar3 = "/opt/jdk1.7.0/jre/lib/rt.jar";
	  String jar4 = "/opt/jdk1.7.0_10/jre/lib/rt.jar";
		
	  //put jar file strings into an array
	  File[] files = { new File(jar1), new File(jar2),
				       new File(jar3), new File (jar4) };
		
	  int largest = 0;
	  String largestJar = "";
	  //obtain class count of each jar file
	  for(int i = 0; i < files.length; i++)
	  {
			JarFile jf = new JarFile(files[i]);
			Enumeration<?> e = jf.entries();
			//int to count class total
			int total = 0;
			while(e.hasMoreElements())
			{
				String t = e.nextElement().toString();
				if(t.contains("interface")) //ignore interfaces 
					continue;
				else
					total++;                //count classes
         }
			System.out.println(files[i] + " class total: " +total);
			//obtain largest jar file 
			if(total > largest)
			{
				largest = total;
				largestJar = files[i].getAbsolutePath();
			}
	  }
		   System.out.println("Most classes found in: " +largestJar 
				                              + " Total: " +largest);
	}  
}
