/**Name: Victor Malchikov
 * Class: CS211E
 * Prof: Abbas Moghtanei
 * Homework: 2
 * File: Student.java
 */

import java.text.DecimalFormat;

public class Student implements Comparable<Object> 
{
	private int id; //student id
	private String firstName; //student's name
	private String lastName; //student's last name
	private int quizScore; //student's quiz score
	private int midtermScore; //student's midterm score
	private int finalScore; //student's final test score
	
	//************************************Student()**********************************
	//constructor - note: no default constructor exits because id field 
	//                    MUST be unique for each student 
	public Student(int id, String firstName, String lastName)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		quizScore = 0;
		midtermScore = 0;
		finalScore = 0;
	}
	
	//**********************************Student()************************************
	//constructor 
	public Student(int id, String first, String last, int quiz, int mid, int f)
	{
		this.id = id;
		this.firstName = first;
		this.lastName = last;
		quizScore = quiz;
		midtermScore = mid;
		this.finalScore = f;
	}
	
	//**********************************setScores()**********************************
	//set scores - set quiz, midterm, and final test scores 
	public void setScores(int qScore, int mScore, int fScore)
	{
		quizScore = qScore;
		midtermScore = mScore;
		finalScore = fScore;
	}
	
	//**********************************getAvg()*************************************
	//get average - calculate average grade based on quiz, midterm and final test 
	public double getAvg()
	{
		double avg = (quizScore + midtermScore + finalScore)/3.0;
		//use DecimalFormat to dictate number of decimals in double 
		DecimalFormat df = new DecimalFormat("###.##"); 
		avg = Double.valueOf(df.format(avg));
		return avg;
	}
	
	//**********************************getGrade()***********************************
	//get final grade - Return Final Grade based on average score of student
	public String getGrade()
	{
	   //get average 
		int avg = (int)this.getAvg();
		if(avg >= 90)                        //90 to 100 A
			return "A";
		else if (80 <= avg && avg < 90)      //80 to 89 B
			return "B";
		else if (70 <= avg && avg < 80)      //70 to 79 C
			return "C";
		else if (60 <= avg && avg < 70)      //60 to 69 D
			return "D";
		else
			return "F";                       //59 or less F
	}
	
	//**********************************toString()***********************************
	//toString - info
	public String toString()
	{
		String info = id+"      "
	                  +lastName+"      "
				      +firstName+"         "
	                  +quizScore+"         "
				      + midtermScore+ "         "
	                  +finalScore;
		return info;
	}
	
	//*********************************compareTo()***********************************
	//allow students to be compared
	@Override
	public int compareTo(Object s)
	{
		Student student = (Student)s; 
		if(getAvg() == student.getAvg())
			return 0;
		else if(getAvg() > student.getAvg())
			return 1;
		else
			return -1;
	}
	
	//*********************************getStudentId()********************************
	//allow user to obtain student id
	public int getStudentId()
	{
		return id;
	}
	
	//*********************************getFirstName()********************************
	public String getFirstName()
	{
		return firstName;
	}
	
	//*******************************getLastName()***********************************
	public String getLastName()
	{
		return lastName;
	}
}
