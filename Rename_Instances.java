//Bryan Wodi
//Nov. 20, 2016

import java.io.*;

public class Rename_Instances{
  public static void main(String[] args) throws IOException{
    if(args.length == 1 ){
      
      String new_instance, old_instance;
      
      old_instance = "$";
      // old_instance = "https://raw.githubusercontent.com/talk2bryan/Cravings/rs/images";

//      new_instance = "https://github.com/talk2bryan/Cravings/tree/master/milestones/Milestone3/resources/images";
      new_instance = "";
      //load file
      try{
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        PrintWriter out = new PrintWriter(new FileWriter("data_corrected.csv"));
        String line = in.readLine();
        
        
        while(line != null ){
          line = line.trim();
          if (line.length()>0) {
            int start = line.indexOf(old_instance);
            if(start >= 0){
//              System.out.println("BEFORE:\t"+ line);
              int max = line.lastIndexOf(old_instance) - start +1;
              new_instance=""+max;

              line = line.substring(0,start) + new_instance + line.substring(line.lastIndexOf(old_instance)+1,line.length());
              out.println(line);
              System.out.println(/*"AFTER:\t" +*/ line);
            }
          }
          line = in.readLine();
        }
        out.close();
        in.close();
      }
      catch(Exception e){
        System.out.println(e);
      }
    }
    else{
      System.out.println("Enter the file in command line");
    }
  }
}