import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class PortScanner
{
   public static void main(String[] args) throws UnknownHostException
   {
      //String host = "localhost";
      Socket s;
      //get local host 
      //InetAddress ia = InetAddress.getByName(host);
      //or get local host
      InetAddress ia = InetAddress.getLocalHost();
      //System.out.println(ia);
      for(int i = 17048; i < 17053; i++)
      {
         try
         {
            //s = new Socket(host, i);
            s = new Socket(ia, i);
            System.out.println("open port: " + i);
            s.close();
         }
         catch(Exception e)
         {
            System.out.println("closed: " + i);
         }
      }
   }
}
