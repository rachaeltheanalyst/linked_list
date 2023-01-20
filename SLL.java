/**
 *  Class to implement a singly linked list
 */

public class SLL<T> implements Phase1SLL<T>, Phase2SLL<T>, Phase3SLL<T>{
  /** The head node of the list */
  private NodeSL<T> head;

  /** The tail node of the list */
  private NodeSL<T> tail;

  /** Constructor for an empty list */
  public SLL() {
    head = tail = null;
  }

  /**
   *  Determines whether a list is empty
   *  @return T/F is the list empty?
   */
  public boolean isEmpty() {
    return (head == null && tail==null);
  }

  /**
   *  Accessor for head node
   *  @return the head node
   */
  public NodeSL<T> getHead() {
    return this.head;
  }

  /**
   *  Accessor for tail node
   *  @return the tail node
   */
  public NodeSL<T> getTail() {
    return this.tail;
  }

  public String toString() {
    String result = "[";
    //If the list is empty, print '[]'
    if (this.isEmpty()) {
      result += "]";
    }
    else {
      NodeSL<T> pos = this.getHead();
      //Traverse the list, concatenates the data in all the list nodes
      while (pos != this.getTail()) {
        result += pos.getData().toString() + ", ";
        pos = pos.getNext();
      }
      result += pos.getData() + "]";
    }
    return result;
  }

  /**
   *  Inserts the given item at the head of the list
   *  @param v item to insert
   */
  public void addFirst(T v) {
    //Create a new node with head as its next reference
    //And make head reference point to it
    this.head = new NodeSL <T> (v, this.head);
    //If tail.next is null, make it reference the newly added element
    if (this.tail == null)
      this.tail = this.head;
  }

  /**
   *  Removes the given item from the head of the list
   *  @return v item removed
   */
  public T removeFirst() {
    if (!isEmpty()) {
      T result = this.head.getData();
      //Change head to reference head.next
      head = head.getNext();
      //Check for special case when head is now null
      if (head == null) {
        //If so, set tail also to null
        tail = null;
      }
      return result;
    }
    //Throw exception if list empty
    else {
      throw new MissingElementException();
    }
  }

  /**
   *  Inserts the given item at the tail of the list
   *  @param item to insert
   */
  public void addLast(T data) {
    if (tail == null) {
      NodeSL <T> node = new NodeSL <T> (data, null);
      this.head = node;
      this.tail = node;
    }
    else {
      NodeSL <T> node = new NodeSL <T> (data, null);
      //Make current tail.next point to the new node
      this.tail.setNext(node);
      //Change the tail to point to the new node
      tail = node;
    }
  }

  /**
   *  Removes the given item from the tail of the list
   *  @return item removed
   */
  public T removeLast(){
    if (!isEmpty()) {
      
      T result = this.tail.getData();
      // find the 2nd to last node
      NodeSL<T> second_last = this.head;
      if (second_last.getNext() == null) {
        this.head = null;
        this.tail = null;
        return result;
      }
      while (second_last.getNext().getNext() != null) {
        second_last = second_last.getNext();
      }
      //Set the tail to the 2nd to last node
      tail =  second_last;
      //Set the tail.next to null
      second_last.setNext(null);
      //Check for special case when head is now null
      if (head == null) {
        //If so, set tail also to null
        tail = null;
      }
      return result;
    }
    //Throw exception if list empty
    else {
      throw new MissingElementException();
    }
  }

  /**
   *  Inserts the given item after the specified node
   *  @param here node to insert after
   *  @param v item to insert
   */
  public void addAfter(NodeSL<T> here, T v) {
    NodeSL <T> nodeToAdd;
    if (isEmpty()) {
      nodeToAdd = new NodeSL <T> (v, null);
      head = tail = nodeToAdd;
    }
    else {
      //Create a new Node pointing to next element of pos
      nodeToAdd = new NodeSL<T>(v, here.getNext());
      //Make pos.next reference point to it
      here.setNext(nodeToAdd);
      //Special case if element added is at the tail
      if (this.tail == here) {
        tail = here.getNext();
      }
    }
  }

  /**
   *  Removes the node after the given position
   *  @param here marks position to remove after
   *  @return item removed
   */
  public T removeAfter(NodeSL<T> here) {
    //If here is null and head node is not null, remove the head node
    if (here == null && head != null ) {
      T result = head.getData();
      head = null;
      return result;
    }
    //If both here and head node are null, throw an exception
    else if (here == null && head == null) {
      throw new MissingElementException();
    }
    //If pos.next is null throw an exception
    if ( here.getNext() == null) {
      throw new MissingElementException();
    }
    else {
      //Special case when node to be removed is the tail
      if (tail == here.getNext())
        tail = here;
      //Retrieve the node to be removed
      T result = here.getNext().getData();
      //Update pos.next to the removed node’s next
      here.setNext(here.getNext().getNext());
      return result;
    }
  }

  /**
   *  Returns a count of the number of elements in the list
   *  @return current number of nodes
   */
  public int size() {
    if (isEmpty()) {
      return 0;
    }
    int count=0;
    for (NodeSL<T> item = this.head; item != this.tail; item = item.getNext()) {
      count++;
    }
    return count+1;
  }

  /**
   * Copy constructor that makes a deep copy of the list
   * @param orig a linked list object to be copied
   * @return deep copy of the list structure
   */
  public SLL (SLL<T> orig) {
    if (orig.head == null) {
      // handle special case of an empty list
      head = tail = null;
    } else {
      // make copies of each node in list
      NodeSL<T> pos = orig.head;
      head = new NodeSL<T>(pos.getData(),null);
      tail = head;
      for (pos = orig.head.getNext(); pos != null; pos = pos.getNext()) {
        tail.setNext(new NodeSL<T>(pos.getData(),null));
        tail = tail.getNext();
      }
    }
  }

  /**
   *  Makes a copy of elements from the original list
   *  @param here  starting point of copy
   *  @param n  number of items to copy
   *  @return the copied list
   */
  public SLL<T> subseqByCopy(NodeSL<T> here, int n) {
    //Create a new list
    SLL<T> copiedList=new SLL<T>();
    NodeSL<T> pos = here;
    //Create a new node with data in the starting node
    //Make the new list head and tail both point to it
    copiedList.head = new NodeSL<T>(pos.getData(),null);
    copiedList.tail = copiedList.head;
    int nodeCount=1;
    // make copies of n node in list from starting point
    while (nodeCount<n) {
      pos = pos.getNext();
      //If the original list is too short to provide the requested number of nodes, throw an exception
      if (pos == null) {
        throw new MissingElementException();
      }
      copiedList.tail.setNext(new NodeSL<T>(pos.getData(),null));
      copiedList.tail = copiedList.tail.getNext();
      nodeCount++;
    }
    return copiedList;
  }

  /**
   *  Places copy of the provided list into this after the specified node.
   *  @param list  the list to splice in a copy of
   *  @param afterHere  marks the position in this where the new list should go
   */
  public void spliceByCopy(SLL<T> list, NodeSL<T> afterHere) {
    //Throw exception if list is this
    if (list.equals(this)) {
      throw new SelfInsertException();
    }
    //Special case when insert position is null, add them at the head of this
    if (afterHere == null) {
      //Save the head
      NodeSL<T> origHead = this.head;
      this.head = new NodeSL<T>(list.getHead().getData(), null);
      NodeSL<T> posAtFront = this.head;
      for (NodeSL<T> pos = list.getHead().getNext(); pos != null; pos = pos.getNext()) {
        posAtFront.setNext(new NodeSL<T>(pos.getData(), null));
        posAtFront = posAtFront.getNext();
      }
      //Set last copied node to the original head
      if (origHead != null)
        posAtFront.setNext(origHead);
      else
        tail = posAtFront;
    } else {
      //Save the node in afterHere.next
      NodeSL<T> posAfterInsert = afterHere.getNext();
      NodeSL<T> pos = list.getHead();
      //Create a node for the head node in the copied list
      //Set the next reference for the node at the insert position to point to the head of the new list
      if (pos != null ) {
        afterHere.setNext(new NodeSL<T>(pos.getData(), null));
        afterHere = afterHere.getNext();
        for (pos = list.getHead().getNext(); pos != null; pos = pos.getNext()) {
          afterHere.setNext(new NodeSL<T>(pos.getData(), null));
          afterHere = afterHere.getNext();
        }
        //Set the next reference for the last node of the list to the posAfterInsert
        afterHere.setNext(posAfterInsert);
        //If afterHere is tail, set the tail to the last node in the list
        if (posAfterInsert == null)
          tail = afterHere;
      }
    }
  }

  /**
   *  Extracts a subsequence of nodes and returns them as a new list
   *  @param afterHere  marks the node just before the extraction
   *  @param toHere  marks the node where the extraction ends
   *  @return  the new list
   */
  public SLL<T> subseqByTransfer(NodeSL<T> afterHere, NodeSL<T> toHere) {
    //Create an empty list
    SLL<T> subSeq = new SLL<T>();
    if (afterHere != null) {
      //Make subSeq list's head point to the node after afterHere
      subSeq.head = afterHere.getNext();
      //Make subSeq list's tail point to the node toHere
      subSeq.tail = toHere;
      //Skip the nodes from next node after afterHere to toHere
      afterHere.setNext(toHere.getNext());
      //Set next reference for toHere to null
      toHere.setNext(null);
    }
    // if afterHere is null, extract a sequence from the head of the list onwards.
    else {
      //Make subSeq list's head point to the head of this
      subSeq.head = this.head;
      //Make subSeq list's tail point to the node toHere
      subSeq.tail = toHere;
      //Set the head of this to the next node after toHere
      this.head = toHere.getNext();
      //Set next reference for toHere to null
      toHere.setNext(null);
    }
    return subSeq;
  }

  /**
   *  Takes the provided list and inserts its elements into this
   *  after the specified node.  The inserted list ends up empty.
   *  @param list  the list to splice in.  Becomes empty after the call
   *  @param afterHere  Marks the place where the new elements are inserted
   */
  public void spliceByTransfer(SLL<T> list, NodeSL<T> afterHere) {
    //Throw exception if list is this
    if (list.equals(this)) {
      throw new SelfInsertException();
    }
    //Save the node after afterHere
    NodeSL<T> posNext;
    if (afterHere == null) {
      posNext = head;
      head = list.getHead();
    } else {
      posNext = afterHere.getNext();
      //Set the node next to afterHere to head of the inserted list
      afterHere.setNext(list.getHead());
    }
    //Set tail of the inserted list point to the node after afterHere
    list.getTail().setNext(posNext);

    if (posNext == null)
      tail = list.getTail();
    //Set the input list to empty
    list.head = list.tail = null;
  }
}
