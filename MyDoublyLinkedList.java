/**
 * A class Index object is used used to keep track of the front 
 * of the link list, the end of the link list, and the cursor location 
 * in the link list.
 * 
 * @creator John Cody
 * @created 02019.03.27
 */
class Index {
   // instance data should be private...
   public Node head;        // points to the front of the list
   public Node tail;        // points to the rear of the list
   public Node cursor;      // points to "current" node
}

/*
 * class Node objects are elements of a doubly linked-list
 */

class Node {
   // instance data should be private...
   public int data;
   public Node next;
   public Node prev;
   public Node(int i) { data = i; }
}

public class MyDoublyLinkedList {
   private Index list; 

   /**
    * Adds a Node containing n to the end of the list.
    *
    * @param int to add
    */
   public void add(int n) {
      Node newNode = new Node(n);
      if (list == null) {
         list = new Index();
         list.head = newNode;
         list.tail = newNode;
         list.cursor = newNode;
         return;
      }
      list.tail.next = newNode;
      newNode.prev = list.tail;
      newNode.next = null; 
      list.tail = newNode;
      list.cursor = newNode;
   }

   /**
    * Finds first occurrence of n in list and assigns
    * it's node to list.cursor. Sets list.cursor to
    * null if n not found.
    *
    * @param int value to find in list
    */
   public void find(int n) {
      if (list == null || list.head == null) return;
      list.cursor = list.head;
      while (list.cursor.data != n) {
         if (list.cursor.next == null) {
            list.cursor = null;
            return; 
         }
         list.cursor = list.cursor.next;
      }
   }

   /**
    * Removes the first occurrence of n from the list.
    * The method does nothing if n not found.
    */
   public void remove(int n) {
      if (list == null) 
         return;  // there is no list
      if (list.head == list.tail) {   // list of length one
         list.head = list.tail = list.cursor = null;
         return;
      }
      find(n);
      if (list.cursor == null) 
         return;  // n not found
      if (list.cursor == list.tail) {
         list.tail = list.cursor.prev;
         list.tail.next = null;
         list.cursor = null;
      } else if (list.cursor == list.head) {
         list.head = list.cursor.next;
         list.head.prev = null;
         list.cursor = list.head;
      } else {
         list.cursor.prev.next  = list.cursor.next;
         list.cursor.next.prev = list.cursor.prev;
         list.cursor = list.cursor.next;
      }
   }


   /**
    * Print this list from head-to-tail.
    */
   public void printForwards() {
      if (list == null) return;     // empty link-list
      System.out.print("head-to-tail: ");
      list.cursor = list.head;
      while (list.cursor != null) {
         System.out.print(list.cursor.data + " "); 
         list.cursor = list.cursor.next;
      }
      System.out.println();
   }

   /**
    * Print this list from tail-to-head.
    */
   public void printBackwards() {
      if (list == null) return;  // there is no list
      System.out.print("tail-to-head: ");
      list.cursor = list.tail; 
      while (list.cursor != null) {
         System.out.print(list.cursor.data + " "); 
         list.cursor = list.cursor.prev;
      }
      System.out.println();
   }

   /**
    * Clears this list.
    */
   public void freeList() { list = null; }  // rely on gc()

   /**
    * TBI (To Be Implemented)
    *
    * Instantiates a Node for n and makes it the new head
    * for this list.
    *
    * @param int that becomes the list's new head
    */
   public void insert(int n) {
       Node m = new Node(n);
       m.next = list.head;
       list.head.prev = m;
       list.head = m;
   }

   /**
    * TBI (To Be Implemented)
    *
    * Instantiates a Node for n and inserts it into this list
    * at (previous) the Node for 'spot'.  This method does nothing 
    * if 'spot' doesn't exist.  If 'spot' is head, then this method 
    * calls insert(n) to perform the insert.
    *
    * This method uses find(spot) to get a pointer (list.cursor)
    * to spot's Node. There is no spot if list.cursor is null.
    *
    * @param int to insert into list
    * @param spot (int value) to insert at
    */
   public void insert(int n, int spot) {
       find(spot);
       if(list.cursor == null)
           return;
       if(list.cursor == list.head){
           insert(n);
           return;
       }
       Node j = new Node(n);
       j.next = list.cursor;
       j.prev = list.cursor.prev;
       list.cursor.prev.next = j;
       list.cursor.prev = j;
   }

   /**
    * Used to test the doubly linked-list.
    */
   public static void main(String[] argv) {

      MyDoublyLinkedList dll = new MyDoublyLinkedList();

      System.out.println("add 20 40 50...");
      dll.add(20);
      dll.add(40);
      dll.add(50);
      dll.printForwards();
      dll.printBackwards();

      System.out.println("\ninsert 30 at 40...");
      dll.insert(30, 40);
      dll.printForwards();
      dll.printBackwards();

      System.out.println("\ninsert 10 as new head...");
      dll.insert(10);
      dll.printForwards();
      dll.printBackwards();

      System.out.println("\ninsert 99 at 10...");
      dll.insert(99, 10);
      dll.printForwards();
      dll.printBackwards();

      System.out.println("\ninsert 42 at 59 (doesn't exist)...");
      dll.insert(42, 59);
      dll.printForwards();
      dll.printBackwards();
   }
}

/*
 *

add 20 40 50...
head-to-tail: 20 40 50 
tail-to-head: 50 40 20 

insert 30 at 40...
head-to-tail: 20 30 40 50 
tail-to-head: 50 40 30 20 

insert 10 as new head...
head-to-tail: 10 20 30 40 50 
tail-to-head: 50 40 30 20 10 

insert 99 at 10...
head-to-tail: 99 10 20 30 40 50 
tail-to-head: 50 40 30 20 10 99 

insert 42 at 59 (doesn't exist)...
head-to-tail: 99 10 20 30 40 50 
tail-to-head: 50 40 30 20 10 99 

 *
 */