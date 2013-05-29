import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarFile;

public class JavaJARLocator 
{
	public static void main(String[] args) throws IOException
	{
		//hills: $ find / -name rt.jar 2> /dev/null
		//obtain list of root directories on system
		File[] r = File.listRoots();
		
		//create FileFinder object and ask it to find rt.jar file 
		FileFinder ff = new FileFinder("rt.jar");
		
		String tmp = "";
		//check each root directory for rt.jar file
		for(int i = 0; i < r.length; i++)
		{
			//System.out.println("CHECKING: " + r[i]);
			tmp = ff.getFileLocation(r[i].toString());
			//System.out.println("back from " + r[i]);
			if(tmp.contains("rt.jar"))
			{
				//System.err.println(tmp + " FOUND!");  PRINT STATMENT 
				break;
			}
			
		}
		
		
		JarFile jf = new JarFile(tmp);
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
		System.out.println(tmp + " class total: " +total);

		//I manually located the other jar file based on abs path of first jar file
		//You can test this on your OWN system if you comment out this 
		//last section here:
		/*
		String secondJar = "/etc/alternatives/jre_1.6.0/lib/rt.jar";
		JarFile jf2 = new JarFile(secondJar);
		Enumeration<?> e2 = jf2.entries();
		total = 0; //reset total to 0
		while(e2.hasMoreElements())
		{
			String t = e2.nextElement().toString();
			if(t.contains("interface")) //ignore interfaces 
				continue;
			else
				total++;                //count classes
		}
		System.out.println(secondJar + " class total: " +total);
		*/
	}
}
