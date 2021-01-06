//COMP 1006A/1406A Assignment 4 Problem 3
//By Alexander Kuhn, ID# 101023154, August 2nd, 2016
//Purpose: This class allows you to insert and remove string nodes from anywhere in a linked list.
public class LinkedList extends ALinkedList {
  
  @Override
  public void insert(ALinkedList list, int pos) {
    //Purpose: Inserts a linked list into another linked list.
    //Preconditions: Requires a linked list to insert and a place in the current object to insert it.
    //Postconditions: The linked list this is called from will be updated with all the values from the list given as argument,
    //and the argument list will be empty.
    Node insertionPoint = new Node();
    insertionPoint.setNext(this.head);
    int currentPosition = pos;
    int skipTheRest = 0;
    
    if (pos > this.size) {
      System.out.println("Position exceeds size of list.");
      return;
    }
    
    if (pos < 0) {
      System.out.println("Position does not make rational sense.");
      return;
    }
    
    if (pos == 0) {
      this.addFront(list.head.get());
      list.removeFront();
      skipTheRest = 1;
    }
    //Adding to the front is easy - we just call addFront and don't execute the remainder of the code
    
    
    if (skipTheRest != 1) {
    
    for (int i = 0; i < currentPosition; i += 1) {
      insertionPoint = insertionPoint.getNext();
    }
    
    this.size += 1;
    Node insertedNode = new Node();
    insertedNode.set(list.head.get());
    insertedNode.setNext(insertionPoint.getNext());
    insertionPoint.setNext(insertedNode);
    list.removeFront();
    currentPosition += 1;
    }
    //This will go through the main list until it hits the place it's supposed to put something, take the first node
    //from the argument list, and put it where it belongs, removing it from the argument list in the process.
    
    if (list.size > 0) {
      this.insert(list, pos + 1);
      //As long as there are elements from the argument list left to add, we recursively call this method
    }
    else {
      return;
    }
  
  }
  
  @Override
  public LinkedList extract(int start, int end){
    //Purpose: Removes a fixed amount of nodes from a linked list and returns all of them as another linked list.
    //Preconditions: Requires a place in the list to start and a place in the list to stop.
    //Postconditions: The linked list this is called from will lose all the nodes from the position at start to the position at end.
    LinkedList extractedList = new LinkedList();
    
    while (end >= start) {
      Node removalNode = this.head;
      Node removedNode = this.head.getNext();
      int skipTheRest = 0;
      
      if (start == 0) {
        extractedList.add(this.removeFront());
        skipTheRest = 1;
      }
      //If we need to remove from the first point in the linked list, we already have a method to do that
      //Might as well just call it
      
      if (skipTheRest != 1) {
        for (int i = 0; i < start - 1; i += 1) {
          removalNode = removedNode;
          removedNode = removalNode.getNext();
        }
        
        removalNode.setNext(removedNode.getNext());
        removedNode.setNext(null);
        extractedList.add(removedNode.get());
        size -= 1;
      }
      end -= 1;
      //This code is the main loop; it removes the nodes after the starting point and places them in extractedList until it's hit the specified endpoint
    }
    
    return extractedList;
  }
  
  //public static void main (String[] args) {
    //LinkedList l = new LinkedList();
    //l.add("x");
    //l.add("y");
    //l.add("z");
    
    //LinkedList m = new LinkedList();
    //m.add("1");
    //m.add("2");
    //m.add("3");
    //m.add("4");
    
    //System.out.println(l);
    //System.out.println(m);
    //l.insert(m, 2);
    //System.out.println(l);
    //System.out.println(m);
    
  //}
  //A testing method
  
}
