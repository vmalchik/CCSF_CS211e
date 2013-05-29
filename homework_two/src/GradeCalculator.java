/**Name: Victor Malchikov
 * Class: CS211E
 * Prof: Abbas Moghtanei
 * Homework: 2
 * File: GradeCalculator.java 
 * Description: This is an application that will do the following: connect to a 
 *              database, obtain table information from students_cs table, create
 *              a new table that will contain final grades of students. 
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class GradeCalculator
{
	public static void main(String[] args)
	{
		//connect to database - using url, user, password 
		//url: "jdbc:oracle:thin:@hills.ccsf.edu:1521:cs11";
		try 
		{
			//obtain info for sqlplus login
		   Properties p = new Properties();
		   //use properties file to obtain user + password info
			FileInputStream fis = new FileInputStream("sqlplus_database.properties");
			p.load(fis);
			fis.close();
		
			Connection conn = DriverManager.getConnection(p.getProperty("jdbc.url"), p);
			//**************Part 1: Display students_cs table***************
			
			Statement stmt = conn.createStatement();
		
			//NOTE: do not include semi-colon in String query
			String query = "select * from students_cs";
			ResultSet rs = stmt.executeQuery(query);
			
			//Store table data type in HashMap<Integer, String>
			HashMap<Integer, String> fieldInfo = new HashMap<Integer, String>();
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfFields = rsmd.getColumnCount();
			//Store Field Name and Data Type 
			for(int i = 1; i <= numberOfFields; i++)
			{
				fieldInfo.put(i, rsmd.getColumnLabel(i).toLowerCase() +" | " );
			}
			
			//store students into Array
			ArrayList<Student> students = new ArrayList<Student>();
			getTableInfo(rs, students);
			//print Student Info by Row 
			for(int i = 1; i <= numberOfFields; i++)
			{
				System.out.print(fieldInfo.get(i) + " ");
			}
			//display students_cs table
			System.out.println();
			for(int i = 0; i < students.size(); i++)
				System.out.println(students.get(i));
			System.out.println("Number of records: " + students.size());
			
			//****************Part 2: Create Table - FinalGrades*****************
			
			//check if FinalGrades table exists
			DatabaseMetaData dmd = conn.getMetaData();
			String tables[] = {"TABLE"};
			rs =dmd.getTables(null, null,"FINALGRADES", tables);
			boolean check = false;
			while(rs.next())
			{
				if("FINALGRADES".equals(rs.getString("TABLE_NAME")))
					check = true;		
			}
			//create FinalGrades table if it did not exist
			if(!check)
			{
				//create table statement	
				String query_table = "create table FinalGrades (";
				//create table fields statement 
				String query_fields = "id integer not null, " 
							           +"last_name varchar2(25) not null, "
							           +"first_name varchar2(25) not null, "
							           +"average_score decimal(5,2) null, "
							           +"final_grade char(1) null," 
							           +"constraint fk_students_cs" 
							           +" FOREIGN KEY (id)"
							           +" REFERENCES students_cs(id)," 
							           +"constraint id_fg_pk primary key (id) )";
				stmt.executeUpdate(query_table + query_fields); 
			}		
			//populate the table using following statement 
			query = "insert into FinalGrades (id, last_name, first_name,"
						+" average_score, final_grade) values (";
			//table values 
			int id;
			String fName;
			String lName;
			double avg;
			String grade;
			//obtain info from student objects and populate table 
			for(int i = 0; i < students.size(); i++)
			{
			    try
			    {	
			    	id = students.get(i).getStudentId();
					fName = students.get(i).getFirstName();
					lName = students.get(i).getLastName();
					avg = students.get(i).getAvg();
					grade = students.get(i).getGrade();
					stmt.executeUpdate(query+id+", '"+lName+"', '"+fName+"', "
										+avg+", "+"'"+grade+"'"+")");
			    //if student id already in the list and has been calculated 
			    //exception is thrown and caught. Continue adding students. 
			    }catch(SQLIntegrityConstraintViolationException uniqueConstraint) {}
			}
			
			//display FinalGrades table
			query = "select * from FinalGrades order by final_grade";
			rs = stmt.executeQuery(query);
			getFinalGrades(rs, rsmd);
			
			//close ResultSet
			if(!rs.isClosed())
			   rs.close();
	
		} 
		catch (SQLException | IOException e) 
		{
			e.printStackTrace();
		}
	
		
	}
	
	//********************************getFinalGrades()*******************************
	//method used to display data from FinalGrades table
	private static void getFinalGrades(ResultSet rs, ResultSetMetaData rsmd) 
	throws SQLException
	{
		//field data 
		int id;
		String first;
		String last;
		double avg;
		String grade;
		
		rsmd = rs.getMetaData();
		int numberOfFields = rsmd.getColumnCount();
		//Store Field Name and Data Type 
		for(int i = 1; i <= numberOfFields; i++)
		{
			System.out.print(rsmd.getColumnLabel(i).toLowerCase() +" | " );
		}
		System.out.println();
		//obtain field data
		while(rs.next())
		{
			id = rs.getInt(1); 
			last = rs.getString(2);
			first = rs.getString(3);
			avg = rs.getInt(4);
			grade = rs.getString(5);
			//print info obtained from table
			System.out.println(id+ " " +last+ " " +first+ " " +avg+ " " +grade);
		}
	}
	
	//************************************getTableInfo()*****************************
	//method to obtain and store table info 
	private static void getTableInfo(ResultSet rs, ArrayList<Student> s) 
	throws SQLException
	{	
		//field data
		int id;
		String last;
		String first;
		int quiz;
		int mid;
		int finalScore;
		
		while(rs.next())
		{
			//obtain field data 
			id = rs.getInt(1); 
			last = rs.getString(2);
			first = rs.getString(3);
			quiz = rs.getInt(4);
			mid = rs.getInt(5);
			finalScore = rs.getInt(6);
			//create and store a new student based on data 
			s.add(new Student(id, last, first, quiz, mid, finalScore));
		
		}
	}
}
