//------------------------------------------------------
//  Created by Henry Bryan on 2015-07-27. 11:31:30PM MDT
//  Copyright (c) 2015 Henry Bryan. All rights reserved.
//------------------------------------------------------

//---------------------------------------------
// To run:
//run RunTestData
//type that in the interactions pane after ">"
//---------------------------------------------


//======= EVERYTHING YOU NEED TO KNOW ABOUT LINKED LISTS, I THINK 

//a linked list is a list where each content is
//connected to the other
//like a chain
//the thing that connects them is called a NODE
//the data contained by each element in the 
//linked list is contained in the NODE

//--There are 2 ways to design a linked list
//1 - to include the Node class in the LinkedList class
//2 - to have them in separate classes, with the LinkedList 
//    class containing just the head Node Pointer


//Assuming your linked list holds a strings
//i use strings because it is an object, and 
//you might have an assignment to create a linked list 
//of objects so they might be similar




//---------------1--------------------------

//FirstLinkedList.java
class FirstLinkedList
{
  
  private class Node
  {
    private Node next;
    private String data;
    
    public Node( String data,Node next)
    {
      this.next = next;
      this.data = data;
    }
    
    public void setNext(Node next)
    {
      this.next = next;
    }
    
    public Node getNext()
    {
      return next;
    }
    
    public String getData()
    {
      return data;
    }
  }//end class Node
  
  private Node head;
  private int size;
  
  public FirstLinkedList() { head = null; size = 0;}
  
  public int getSize()
  {
    return size;
  }
  
  public Node getHead()
  {
    return head;
  }
  
  public void add(String data)
  {
    Node curr;
    
    if(head == null)//or you can say if ( isEmpty() )
    {               //where isEmpty() is a boolean method that returns (head == null)
      // edge case: first item to insert
      //so linkedlist will look like [head]->null
      head = new Node(data, null);
    }
    else //linked list has n elements aleady
    {//so linkedlist will look like 
      //[head]->item1->...->item10->null
      //the new item we wanna add, we intend to put it at the 
      //end of the list, so it will be item11
      curr = head; //curr points to the node that contains item1
      //so to get item1 us say curr.getData() which returns the value item1
      
      // traverse to the end of the list, which
      // is denoted by a node with a next pointer
      // set to null
      while(curr.getNext() != null) //while we have not reached the last item on the list (item10)
      {
        curr = curr.getNext(); //set curr Node to point to the next tem on the list (item2,item3, etc)
      }
      // re-direct the last item's next pointer
      // to point to our new data
      curr.setNext(new Node(data, null));//as curr is at item10, we wanna set
      //item10 to point to the NEW last item on the list. Hence 
      //curr (which is still item10).setNext(item11).
      //since each item is kept in a node, we encapsulate item11 in a 
      //Node and set its next to null as Node receives the data and the next Node
      //setting its next to null indicates that it is the last item on the list
    }
    //we increase size as a new wlwmwnt has been added successfully
    size ++;
  }
  
  //EXACTLY THE SAME as add but this puts the string in
  //alphabetical order in the list
  //so if the list contains head->apple->dog->null and you wanna add "bob"
  //at the end of the insertion the list will be:
  //head->apple->bob->dog->null
  
  public void orderedInsert(String data)
  {
    Node curr, prev;
    
    if(size == 0)
    {  // if our list is empty, we don't need to traverse
      head = new Node(data, null);
    }
    else
    {
      curr = head;
      prev = null;
      
      // keep going forwards until we find an item that is greater than
      // what we're trying to insert
      while(curr != null && curr.getData().compareTo(data) < 0)
      {
        prev = curr;
        curr = curr.getNext();
      }
      
      if(prev == null)
      {   // in this case, we insert at the beginning
        head = new Node(data, head);
      }
      else
      {   // we need to insert in the middle of the list
        prev.setNext(new Node(data, curr));
      }
    }
    // don't forget to increment the size
    size ++;
  }
  
  //A BOOLEAN THAT is passed another linked list and is compared to know if they
  //are the same
  public boolean equals(FirstLinkedList list)
  {
    Node curr, listCurr;
    String data, listData;
    boolean result = true;
    
    if(size == list.getSize())
    {//if their sizes are different they are obviously not equl so skip 
      // each list starts at its own beginning position
      curr = head;
      listCurr = list.getHead();
      
      while(result && curr != null)
      {
        // get the data at the same position for both lists
        data = curr.getData();
        listData = listCurr.getData();
        
        // compare the data to ensure they are equal
        result = data.equals(listData);
        
        // advance both pointers across both lists
        curr = curr.getNext();
        listCurr = listCurr.getNext();
      }
    }
    else
    {
      result = false;
    }
    return result;
  }
  
  public void delete(String str)
  {
    Node curr = head;// curr is the item we want to delete
    Node prev = null;// the item before the item we want to delete
    String currStr;// the string we want to remove
    
    if(size > 0)
    {
      currStr = curr.getData();
      
      while(!currStr.equals(str) && curr != null)
      {
        prev = curr;
        curr = curr.getNext();
        currStr = curr.getData();
      }
      
      if(prev == null)
      {
        // edge case: deleting first item
        head = head.getNext();
      }
      else
      {
        // deleting in middle of the list means
        // we redirect prev's next pointer to skip
        // over curr, which we want to remove
        prev.setNext(curr.getNext());
      }
      
      size --;//reduce the size 
    }
  }
  public String toString()
  {//return a list of the strings in the list as a list
    //one item per line. hence the result + "\n"
    Node curr = head;
    String result ="";
    
    while(curr != null)
    {
      result += curr.getData() + "\n";//if you wanna print the content of
      //the list, it is at that line you do
      //System.out.println( curr.getData() ); //one string per line
      curr = curr.getNext();
    }
    return result;
  }
}


//---------------2--------------------------

//cut Node class out of the Linked List class and remove 
//the private from the Node class 




/////////////////////TEST DATA//////////////////////////
class RunTestData
{
  public static void main(String [] parms)
  {
    FirstLinkedList list = new FirstLinkedList();
    
    list.orderedInsert("fido");
    list.orderedInsert("fluffy");
    list.orderedInsert("zac");
    list.orderedInsert("zzzz");
    list.orderedInsert("zam");
    list.orderedInsert("pinko");
    list.orderedInsert("aaa");
    list.orderedInsert("petey");
    list.orderedInsert("albert");
    list.orderedInsert("zed");
    System.out.println(list.toString()); //should print the list
    //in alphabetical order

    list.add("apple"); //should add apple to the last item on the list

    System.out.println(list.toString());//show list to prove
    
    list.delete("pinko");
    
    System.out.println(list.toString());//show updated list w/o pinko
  }
}