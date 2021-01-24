import java.util.regex.*;
import java.util.*;
import java.io.*;

public class Regex{
	public static void main(String[] args)  throws IOException{
      while(true){
      	String str = getInput();
      	if (str.equalsIgnoreCase("q")) {
      		break;
      	}
      	String pattern = "^[a-z][a-zA-Z$0-9]*$";

	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	      // Now create matcher object.
	      Matcher m = r.matcher(str);
	      if (m.find( )) {
	         System.out.println("Found value: " + m.group(0) );
	      }else {
	         System.out.println("NO MATCH");
	      }
      }
      System.out.println("done.");
	}
	public static String getInput() throws IOException{
		Scanner kbd = new Scanner(System.in);
		String prompt = "Enter string: ";
		System.out.println(prompt);
		String result = kbd.nextLine();
		return result;
	}
}
