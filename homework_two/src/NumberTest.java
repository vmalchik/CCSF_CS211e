import java.util.Properties;


public class NumberTest 
{
	public static void main(String[] args)
	{
		Student a = new Student(1, "l", "f", 47, 57, 69);
		double avg = a.getAvg();
		System.out.println(avg);
		Properties p = System.getProperties();
		System.out.println(p.getProperty("java.class.path"));
		 System.out.println(System.getProperty("java.class.path"));
	}
}
