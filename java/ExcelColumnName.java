import java.io.*; 

public class ExcelColumnName {
  public static void main(String[] args) throws IOException {
    // extra tests...
    assert ("AAC".equals(column_name(705))) : "Expecting AAC";
    assert ("AY".equals(column_name(51))) : "Expecting AY";
    assert ("AZ".equals(column_name(52))) : "Expecting AZ";
    assert ("A".equals(column_name(1))) : "Expecting A";
    assert ("Z".equals(column_name(26))) : "Expecting Z";

    // for user...
    while( true ){
      String s = getString();
      if(s.equalsIgnoreCase("q"))
        break;
      int num = Integer.parseInt(s);
      System.out.println(column_name(num));
    }
  } //  main()
  
  public static String column_name(int num){
    assert (num > 0) : "Invalid column number";
    
    String result = "";
    int num_alpha = 26;
    while(num > 0){
      num--; // using 0-25 for for cleaner math
      // method 1: append to front of string
      result = ("" + (char)(num % num_alpha + 'A') ) + result;
      num /= num_alpha; 
    }
    return result; // or method 2: reverse string before rtn
  }  // column_name()
  
  public static String getString() throws IOException{
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);
    String s = br.readLine();
    return s;
  }  // getString()
}  // ExcelColumnName