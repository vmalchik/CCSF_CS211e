/**Name: Victor Malchikov
 * Class: CS211E
 * Prof: Abbas Moghtanei
 * Extra-Credit: 2
 * File: StatesServlet.java
 * Description: This program access SQL Database and retrieves information for 
 *              the user based on the request. Response generated comes back to
 *              the user and contains the name of State/Capital or no match for 
 *              request. 
 * NOTE: There is a jar dependency. 
 *       1. To connect with database you must have ojdbc6.jar
 *       2. To use the servlet api you must have servlet-api.jar
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class StatesServlet extends GenericServlet
{
	//*****************************init()*******************************************
	public void init(ServletConfig sc)
	{
		//setup default initialization 
		try 
		{
			super.init(sc);
		} 
		catch (ServletException e)
		{
			e.printStackTrace();
		}
	}
	
	//****************************service()*****************************************
	@Override
	public void service(ServletRequest req, ServletResponse res)
	throws ServletException, IOException 
	{
		try 
		{
			//establish DB connection
			DBConnector d = new DBConnector();
			
			//set MIME type
			res.setContentType("text/html");	
			PrintWriter pw = res.getWriter();
			//get input from user
			//String state = req.getParameter("state");
			Enumeration<String> e = req.getParameterNames(); //"state" and "capital"
			//process elements
			while(e.hasMoreElements())
			{
				String pname = e.nextElement();
				String pvalue = req.getParameter(pname);
				if(pname.equals("state"))
				{
					pw.println(d.getCapital(pvalue) + ", ");
				}
				else
					pw.println(d.getState(pvalue));
			}
			//close and send response
			pw.close();
		} 
		catch (ClassNotFoundException | SQLException e1) 
		{
			e1.printStackTrace();
		}
		
		
	}

}
