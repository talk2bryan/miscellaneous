import java.util.Stack; // Alt: {inre/decre}-ment a counter.

public class BracketChecker {
  public static void main(String [] parms) {
    // extra tests....
    assert(( check("{") == false ) && ( check("a{b(c]d}e") == false ) 
              && ( check("{") == false ) && ( check("[a{b(cd)}e]") == true));
  }
  public static boolean check(String input) {
    Stack<Character> theStack = new Stack<Character>();
    // get chars in turn
    for(int j=0; j<input.length(); j++) {
      char ch = input.charAt(j);
      switch(ch) {
        // opening symbols
        case '{': case '[': case '(':
          theStack.push(ch); // push them
          break;
          // closing symbols
        case '}':  case ']': case ')':
          if( !theStack.isEmpty() ) {
          char chx = theStack.pop(); // pop and check
          if( (ch=='}' && chx!='{') || 
             (ch==']' && chx!='[') || (ch==')' && chx!='(') ){
            System.out.println("Error: "+ch+" at "+j);
            return false;
          }
        }
          else{ // prematurely empty, no opening for closing brace
            System.out.println("Error: "+ch+" at "+j);
            return false;
          }
          break;
        default: // no action on other characters
          break;
      } // end switch
    } // end for
// at this point, all characters have been processed
    if( !theStack.isEmpty() ){
      System.out.println("Error: missing right closing symbol");
      return false;
    }
    return true;
  }  // check()
}  // BracketChecker