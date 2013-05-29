import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;



public class URLDemo
{
   public static void main(String[] args) throws IOException 
   {
      URL hp = new  //throws MalformedURLException
               URL("http://hills.ccsf.edu/~vmalchik/homework/hw_javascript.html");
       
      URLConnection uc = hp.openConnection();
      
      System.out.println("protocol: " +hp.getProtocol());
      System.out.println("port: " +hp.getPort());
      System.out.println("host: " +hp.getHost());
      System.out.println("file: " +hp.getFile());
      System.out.println("path: " +hp.getPath());
      System.out.println("external form: " +hp.toExternalForm());
      
      System.out.println("***************");
      
      long d = uc.getDate();
      if(d == 0)
         System.out.println("no date");
      else
         System.out.println("date: " +new Date(d));
      
      long len = uc.getContentLengthLong();
      if(len == -1)
         System.out.println("content length not avail");
      else
         System.out.println("content length: " + len);
      
      System.out.println("content type: " + uc.getContentType());
      System.out.println("expiration: " + uc.getExpiration());
      
      System.out.println("***************");
      /*
      Map<String, List<String>> info = uc.getHeaderFields();
      Set<String> set = info.keySet();
      for(String k : set)
      {
         System.out.println("key: " +k);
         System.out.println("value: "+ info.get(k));
      }
    OR
      Set< Map.Entry<String, String> > set = (Set<Entry<String, String>>)
                                             uc.getHeaderFields();
      for(Map.Entry<String, String> me : set)
      {
         System.out.println("key: " + me.getKey());
         System.out.println("value: " + me.getValue());
      } */
   }
}
