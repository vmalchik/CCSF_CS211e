/**Name: Victor Malchikov
 * Class: CS211E
 * Prof: Abbas Moghtanei
 * Extra-Credit: 2
 * File: DBConnector.java
 * Description: This program connects with the database and retrieves appropriate 
 *              information based on user's request. 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class DBConnector 
{
	private Statement stmt;
	private ResultSet rs;
	
	//	// java -cp jdbc.jar:.
	//**********************************DBConnector()*******************************
	//setup connection with database
	public DBConnector() throws SQLException, FileNotFoundException, 
	                            ClassNotFoundException
	{
		//load class 
		Class.forName("oracle.jdbc.OracleDriver");

		//setup connection via DriverManager - db url, user name, pw
		Connection con = DriverManager.getConnection(
				         "jdbc:oracle:thin:@hills.ccsf.edu:1521:cs11",
				         "vmalchik", "sep2887");
		//create statement using connection
		stmt = con.createStatement();
		//check if table exists
		String query = "select * from v_states";
		
		boolean exists;
		try
		{
			rs = stmt.executeQuery(query);
			exists = true;
		}
		catch(Exception e) 
		{
			exists = false;
		}
		
		//create table 
		if(!exists)
		{
			createTable();
			populateTable();
		}
		else
			System.out.println("Name of table: v_states");
		
	}
	
	//*********************************createTable()********************************
	private void createTable() throws SQLException
	{
		System.out.println("Creating table...");
		//query table
		String query_table = "create table v_states (";
		//query fields
		String query_fields = "id integer not null, "
				   +"state varchar2(25) not null, "
				   +"capital varchar2(25) not null, "  
				   +"constraint id_pk primary key (id) )";
		System.out.println(query_table + query_fields);
		stmt.executeUpdate(query_table + query_fields);
		
	}
	
	//***********************************populateTable()****************************
	private void populateTable() throws FileNotFoundException, SQLException
	{
		System.out.println("Populating table..");
		//populate data base with states and capitals from US_States file
		Scanner sc = new Scanner(new File("US_states.txt"));
		//query to populate the table
		String query = "insert into v_states (id, state, capital) values (";
		//use count to create table id 
		int count = 0;
		//obtain info from US_States file
		while(sc.hasNext())
		{
			String[] tmp = sc.nextLine().split(" {2,}");
			//populate table with states and capitals
			if(count >= 2)
			{
				//populate table with data from US_States.txt file
				String tmpS = (count-1) + ", "
				            +"\'"+tmp[0]+"\' ," 
						    +"\'"+tmp[1]+ "\') ";
				System.out.println(query + tmpS);
				stmt.executeUpdate(query + tmpS);
			}
			count++;
		}

	}
	
	//*************************************getCapital()*****************************
	public String getCapital(String state)
	{
		//given user input find the capital 
		String query = "select capital from v_states where state = "
		             + "\'" +  state + "\'";
		
		try 
		{
			rs = stmt.executeQuery(query); //get capital OR throw exception
			rs.next();//move cursor to first item
			return rs.getNString("capital"); 
		} catch (SQLException e) 
		{
			return "result not found for: "+state;
		}

	}
	
	//***********************************getState()*********************************
	public String getState(String capital)
	{
		//given user input find the state
		String query = "select state from v_states where capital = "
		             + "\'" + capital + "\'";
		try 
		{
			rs = stmt.executeQuery(query); //get state OR throw exception
			rs.next(); //move cursor to obtain state
			return rs.getString("state");
		} catch (SQLException e) 
		{
			return "result not found for: "+capital;
		}

	}
}
