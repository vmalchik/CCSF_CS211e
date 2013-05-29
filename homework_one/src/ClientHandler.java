/**Name: Victor Malchikov
 * Class: CS211E
 * Prof: Abbas Moghtanei
 * Homework: 1
 * File: ClientHandler.java 
*/
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class ClientHandler implements Runnable
{
   protected Socket s; //used for communication between client/server 
   protected Map<String, String> states; //contains answers to client's requests 
   private final String SERVER_STOP_CODE = "hw1"; //secret word to shutdown server
   
   //********************************ClientHander()*********************************
   //constructor
   public ClientHandler(Socket s, Map<String, String> states)
   {
      this.s = s;
      this.states = states;
   }
   
   //*********************************run()*****************************************
   //implement run method 
   public void run()
   {
      
      try
      {
         
         //open stream to obtain info from client 
         Scanner sc = new Scanner(s.getInputStream());
         //use PrintWriter to write into Socket a response
         PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
         //loop over client's request
         while(sc.hasNext())
         {
            //obtain request
            String str = sc.nextLine();
            System.out.println("reqest: " + str);
            //handle request
            //1. check if server code entered to shut down server 
            if(str.equals(SERVER_STOP_CODE))
            {
               pw.println("Closing Sockets.");
               //s.close();
               StateServerVM.serverShutDown();
            }
            //2. check if valid request 
            if(states.containsKey(str))
               pw.println("response: "+ states.get(str));
            else
               pw.println("Error. Bad request.");
         }
         //close communication and socket 
         sc.close(); 
         pw.close();
         s.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
         System.exit(1);
      }
   }
}
