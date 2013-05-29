import java.util.Enumeration;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;

public class RegisteredRMIObjects
{
	public static void main(String[] args)
	{
		Context namingContext;
		try
		{
			System.out.println("naming");
			namingContext = new InitialContext();
			Enumeration<NameClassPair> e = namingContext.list("rmi://hills.ccsf.edu/");
			while(e.hasMoreElements())
				System.out.println(e.nextElement().getName());
		}
		catch (NamingException e1) 
		{
			e1.printStackTrace();
		}
	}
}
