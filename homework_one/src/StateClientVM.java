/**Name: Victor Malchikov
 * Class: CS211E
 * Prof: Abbas Moghtanei
 * Homework: 1
 * File: StateClientVM.java 
*/

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class StateClientVM
{
   public static void main(String[] args)
   {
      //Welcome user//
      System.out.println("Welcome...connecting to server...");
      
      try
      {
         //1. Create a socket 
         //hills local host info: hills.ccsf.cc.ca.us/147.144.1.2
         //connect to port 17008 
         Socket s = new Socket("147.144.1.2", 17050);
         //set timeout 
         s.setSoTimeout(10000);
         //connected message
         System.out.println("Connected to StateServerVM " + s.getInetAddress());
         
         //2. open input stream
         Scanner sc = new Scanner(s.getInputStream());
         //3. use Scanner to obtain input from user and PrintWriter to communicate
         Scanner userInput = new Scanner(System.in);
         PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
         
         //4. communicate with server 
         while(true)
         {
            System.out.println("Please enter State/Capital/exit:");
            System.out.flush();
            String input = userInput.nextLine();
            if(input.equalsIgnoreCase("exit"))
            {
               System.out.println("Good Bye.");
               break;
            }
            //send user request to server
            pw.println(input);
            //return server reply
            System.out.println(sc.nextLine());
         }
         //close communication
         sc.close();
         pw.close();
         s.close();
         userInput.close();
      }
      catch(Exception e)
      {
         e.printStackTrace();
         System.exit(1);
      }
    
   }
}
