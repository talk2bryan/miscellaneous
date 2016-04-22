import java.io.*;
public class BinaryNameSearch
{
  public static void main (String [] parms)
  {
    processing();
    
    System.out.println("Program completed successfully");
  }
  public static void processing ()
  {
    String [] tokens = getTokens(); 
    
    
    for(int i = 0; i<tokens.length;i++)
    {
      
      System.out.println(tokens[i]);
    }
    
    
  }
  
  public static String[] getTokens()
  {
    String [] result = null;
    FileReader r;
    BufferedReader in;
    String line;
   
    try
    {
      r = new FileReader("phoneBook.txt");
      in = new BufferedReader(r);
      
      line = in.readLine();
      result = line.trim().split("\\s+");
      while(line != null)
      {
        result = line.trim().split("\\s+");
        line = in.readLine(); 
      }
      in.close();
    }
    catch(Exception e)
    {
      System.out.println(e.getMessage());
    }
    return result;
  }
}