import java.io.File;
import java.util.Scanner;

public class FileFinder implements Runnable
{
	String fileName;
	String root;
	
	//default constructor
	public FileFinder(String fileName)
	{
		this.fileName = fileName;
	}
	
	public FileFinder(String fileName, String root)
	{
		this(fileName);
		this.root = root;
	}
	

	@Override
	public void run() 
	{
		System.out.println("Starting run() - searching: " + root);
		System.out.println(root + " -> " +getFileLocation(root));
	}
	
	//********************************getFileLocation()**************************/
	public String getFileLocation(String startingDir)
	{
		
		File file = new File(startingDir);
		//check if file
		if(file.isFile())
		{
			//if it is a file then check if it the file user is looking for
			if(file.getName().equals(fileName))
			{
				return file.getAbsolutePath();
			}
			else
				return "not found";
		}
		//obtain list of files in current directory 
		File[] d = file.listFiles();
		String tmp = "not found";
		//if directory is empty return 
		if(d == null)
			return "not found";
		
		//check for file in sub-directories of current directory 
		for(int i = 0; i < d.length; i++)
		{
			 //if directory is not empty recursively call getFileLocation()
			 if(d[i] != null)
				tmp = getFileLocation(d[i].getAbsolutePath());
			 //if recursive check for file found the file - stop the search
			 if(!tmp.equals("not found"))
			 {
				 //System.err.println(tmp + " tmp ");   PRINT STATEMENT
				 break;
			 }			      
		}
		return tmp;
	}
	
	public void setRoot(String root)
	{
		this.root = root;
	}
	
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
}
