/**Name: Victor Malchikov
 * Class: CS211E
 * Prof: Abbas Moghtanei
 * Homework: 1
 * File: StateServerVM.java 
 * Description: Setup a server that accepts more than one client connection. 
 *              The server contains information on States and Capitals. Clients
 *              can connect to the server and type in either State or Capital 
 *              names to receive back the correct response of either State or 
 *              Capital. 
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class StateServerVM 
{
   //ss is the server socket that accepts connections from clients 
   private static ServerSocket ss;
   
   public static void main(String[] args)
   {
      //1: obtain game file and store it in a container
     
      //file that contains States/Capitals
      File states = new File("US_states.txt"); 
      Scanner sc; 
      //answerKey - container that will contain answers 
      Map<String, String> answerKey = new HashMap<String, String>();
      try
      {
         sc = new Scanner(states);
         while(sc.hasNext())
         {
            String tmpLine = sc.nextLine();
            //split string on 2 or more white spaces 
            String[] tmpLineArray = tmpLine.split("\\s{2,}");
            //add key, value to Map
            answerKey.put(tmpLineArray[0], tmpLineArray[1]);
            answerKey.put(tmpLineArray[1], tmpLineArray[0]);
         }
      } 
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      
      //2: Setup Server - port: 17050 (note: my assigned port 17008 was taken) 
      
      try
      {
         //Create Server Socket 
         ss = new ServerSocket(17050);
         //setup infinite-loop to listen/handle clients 
         while(true)
         {
            //Accept client's Connection 
            Socket s = ss.accept();
            System.out.println("New Client Connected");
            //handle client's request via ClientHandler 
            ClientHandler ch = new ClientHandler(s, answerKey);
            //start handling request 
            Thread t = new Thread(ch);
            t.start();
         }

      } catch (IOException e)
      {
         e.printStackTrace();
         System.exit(1);
      }
   }
   
   //**************************************serverShutDown()*************************
   //static method that is invoked by ClientHandler if secret word is gived to 
   //StateClientVM that requests server shutdown. 
   public static void serverShutDown()
   {
      try
      {
         System.out.println("Closing Server Socket.");
         ss.close(); //close server socket
         System.exit(0); //exit program 
      } 
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }
}
