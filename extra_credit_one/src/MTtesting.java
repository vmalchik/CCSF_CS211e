import java.io.File;


public class MTtesting
{
	public static void main(String[] args)
	{
		System.out.println("Hello...");
		//get list of roots
		File[] root = File.listRoots();
		
		FileFinder[] ff = new FileFinder[root.length];
		
		for(int i = 0; i < ff.length; i++)
		{
			//System.out.println("Entering " + i);
			ff[i] = new FileFinder("rt.jar", root[i].getAbsolutePath());
			(new Thread(ff[i])).start();
			
		}
		
	}
	


}
