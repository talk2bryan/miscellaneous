// My solution to Hackerrank's Contact Question (http://hr.gs/abcbda)
// using Tries.
// Credits to Hackerrank's intro Video to Tries.

// Nov. 11, 2017.


import java.util.HashMap;
import java.util.Scanner;

public class HackerrankContacts {
	public static void main(String [] parms){
        Scanner in = new Scanner(System.in);
        System.out.println("How many operations are going to be performed in total? ");

        int num_ops = in.nextInt();

        TrieClass trie = new TrieClass();
        String op, contact;

        for (int i = 0; i < num_ops; i++) {
            op = in.next();
            contact = in.next();

            if (op.equalsIgnoreCase("add"))
                trie.add(contact);
            else if (op.equalsIgnoreCase("find"))
                System.out.println( trie.find(contact) );
            else
                System.out.println("Bad command: use \"add\" or \"find\" followed by" +
                        " a space, and contact_name");

        }
    }
}

class TrieClass {
    private TrieNode root;

    public TrieClass() {
        this.root = new TrieNode();
    }

    public void add(String string){
        assert (string != null);

        TrieNode curr = root;

        for (char ch_ :
             string.toCharArray()) {
            Character ch = new Character(ch_);
            curr = curr.add_child(ch);
            curr.increase_occurrence();
        }
    }
    public int find(String text){
        assert (text != null);

        TrieNode curr = root;

        for (char ch_ :
                text.toCharArray()) {
            Character ch = new Character(ch_);
            curr = curr.find(ch);
            if (curr == null)
                return 0;
        }
        return  curr.getNum_occurrence();
    }
}


class TrieNode {
    private int num_occurrence;
    private HashMap<Character, TrieNode> children;
    private boolean end_of_word;

    public  TrieNode(){
        num_occurrence = 0;
        end_of_word = true;
        children = new HashMap<>();
    }

    public boolean isEnd_of_word() {
        return end_of_word;
    }

    public int getNum_occurrence() {
        return num_occurrence;
    }
    public void increase_occurrence() {
        num_occurrence++;
    }
    /*
    *  Return thr added child node if inserted
    *  to maintain state.
    * */
    public TrieNode add_child(Character ch){
        if ( !children.containsKey(ch) )
            children.put(ch, new TrieNode());

        end_of_word = false; // I have a new dependant letter.
        return children.get(ch);
    }
    public TrieNode find(Character ch){
        return children.get(ch);
    }

}
