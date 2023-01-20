/**
 *  Class to implement a doubly linked list
 */

public class DLL<T> {
  /** The head node of the list */
  private NodeDL<T> head;

  /** The tail node of the list */
  private NodeDL<T> tail;

  /** Constructor for an empty list */
  public DLL() {
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
  public NodeDL<T> getHead() {
    return this.head;
  }

  /**
   *  Accessor for tail node
   *  @return the tail node
   */
  public NodeDL<T> getTail() {
    return this.tail;
  }

  public String toString() {
    String result = "[";
    //If the list is empty, print '[]'
    if (this.isEmpty()) {
      result += "]";
    }
    else {
      NodeDL<T> pos = this.getHead();
      //Traverse the list, concatenates the data in all the list nodes
      while (pos != this.getTail()) {
        //System.out.println(pos.getData());
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
    //Create a new node with head as its next reference, prev reference to null
    NodeDL<T> newNode = new NodeDL <T> (v, null, this.head);
    if (!isEmpty()) {
      //Make previous reference for the head to point to the new node
      head.setPrevious(newNode);
    } else {
      //Make the tail to point to new node
      tail = newNode;
    }
    //Make the head to point to new node
    head = newNode;
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
      } else {
        head.setPrevious(null);
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
    //Create a new Node pointing with previous reference to tail and next reference to null
    if (tail == null) {
      NodeDL <T> node = new NodeDL <T> (data, null, null);
      this.head = node;
      this.tail = node;
    }
    else {
      NodeDL <T> node = new NodeDL <T> (data, this.tail, null);
      //Make current tail.next point to the new node
      this.tail.setNext(node);
      //Change the tail to point to the new node
      tail = node;
      //If this is the first element in the list, make head point to it
    }
  }

  /**
   *  Removes the given item from the tail of the list
   *  @return item removed
   */
  public T removeLast(){
    if (!isEmpty()) {
      T result = this.tail.getData();
      //Set the tail to the 2nd to last node
      tail =  tail.getPrevious();
      //Set the tail.next to null
      if (tail == null) {
        //tail.getNext().setData(null);
        this.head = null;
      }
      else {
        tail.setNext(null);
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
  public void addAfter(NodeDL<T> here, T v) {
    //Create a new Node with prev point to here and next point to here.getNext()
    NodeDL <T> nodeToAdd = new NodeDL <T> (v, here, here.getNext());
    if (this.tail == here) {
      //Special case if here is the tail, set tail point to the new node
      this.tail = nodeToAdd;
    } else {
      //Make here.next's prev reference point to the new node
      here.getNext().setPrevious(nodeToAdd);
    }
    //Make here's next reference point to the new node
    here.setNext(nodeToAdd);
  }

  /**
   *  Inserts the given item before the specified node
   *  @param here node to insert before
   *  @param v item to insert
   */
  public void addBefore(NodeDL<T> here, T v) {
    //Create a new Node with prev point to here.getPrevious and next point to here
    NodeDL <T> nodeToAdd = new NodeDL <T> (v, here.getPrevious(), here);
    if (this.head == here) {
      //Special case if here is the head, set head point to the new node
      this.head = nodeToAdd;
    } else {
      //Make here.prev's next reference point to the new node
      here.getPrevious().setNext(nodeToAdd);
    }
    //Make here's prev reference point to the new node
    here.setPrevious(nodeToAdd);
  }

  /**
   *  Removes the node at the given position
   *  @param here marks position to remove
   *  @return item removed
   */
   public T remove(NodeDL<T> here) {
    //If here is null throw an exception
    if (here == null)
      throw new MissingElementException();
    else {
      //Retrieve the node to be removed
      T result = here.getData();
      //Special case when node to be removed is the tail or head
      if (tail == here) {
        tail = tail.getPrevious();
        if (tail == null) {
          //tail.getNext().setData(null);
          this.head = null;
        }
        else {
          tail.setNext(null);
        }
      } else if (head == here) {
        head = head.getNext();
        if (head == null) {
          //tail.getNext().setData(null);
          this.tail = null;
        }
        else {
          head.setPrevious(null);
        }
      } else {
        //Update here.prev's next to the here.next
        here.getPrevious().setNext(here.getNext());
        //Update here.next's prev to the here.prev
        here.getNext().setPrevious(here.getPrevious());
      }
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
    for (NodeDL<T> item = this.head; item != this.tail; item = item.getNext()) {
      count++;
    }
    return count+1;
  }

  /**
   * Copy constructor that makes a deep copy of the list
   * @param orig a linked list object to be copied
   * @return deep copy of the list structure
   */
   public DLL(DLL<T> orig) {
    if (orig.head == null) {
      // handle special case of an empty list
      head = tail = null;
    } else {
      // make copies of each node in list
      NodeDL<T> pos = orig.head;
      head = new NodeDL<T>(pos.getData(),null, null);
      tail = head;
      //For each node in the orig list
      for (pos = orig.head.getNext(); pos != null; pos = pos.getNext()) {
        //Create a new node and set prev point to tail
        tail.setNext(new NodeDL<T>(pos.getData(),tail,null));
        //Set tail.next's prev point to tail
        tail.getNext().setPrevious(tail);
        //Advance the tail
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
   public DLL<T> subseqByCopy(NodeDL<T> here, int n) {
    //Create a new list
    DLL<T> copiedList=new DLL<T>();
    NodeDL<T> pos = here;
    //Create a new node with data in the starting node
    //Make the new list head and tail both point to it
    copiedList.head = new NodeDL<T>(pos.getData(),null,null);
    copiedList.tail = copiedList.head;
    int nodeCount=1;
    // make copies of n node in list from starting point
    while (nodeCount<n) {
      pos = pos.getNext();
      //If the original list is too short to provide the requested number of nodes, throw an exception
      if (pos == null) {
        throw new MissingElementException();
      }
      //Create a new node and set prev point to tail
      copiedList.tail.setNext(new NodeDL<T>(pos.getData(),copiedList.tail,null));
      //Set tail.next's prev point to tail
      copiedList.tail.getNext().setPrevious(copiedList.tail);
      //Advance the tail
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
  public void spliceByCopy(DLL<T> list, NodeDL<T> afterHere) {
    //Throw exception if list is this
    if (list.equals(this)) {
      throw new SelfInsertException();
    }
    //Special case when insert position is null, add them at the head of this
    if (afterHere == null) {
      //Save the head
      NodeDL<T> origHead = this.head;
      this.head = new NodeDL<T>(list.getHead().getData(), null, null);
      NodeDL<T> posAtFront = this.head;
      for (NodeDL<T> pos = list.getHead().getNext(); pos != null; pos = pos.getNext()) {
        posAtFront.setNext(new NodeDL<T>(pos.getData(), null, null));
        posAtFront.getNext().setPrevious(posAtFront);
        posAtFront = posAtFront.getNext();
      }
      //Set last copied node to the original head
      if (origHead != null) {
        posAtFront.setNext(origHead);
        posAtFront.getNext().setPrevious(posAtFront);
      } else {
        tail = posAtFront;
      }
    } else {
      //Save the node in afterHere.next
      NodeDL<T> posAfterInsert = afterHere.getNext();
      NodeDL<T> pos = list.getHead();
      //Create a node for the head node in the copied list
      //Set the next reference for the node at the insert position to point to the head of the new list
      if (pos != null) {
        afterHere.setNext(new NodeDL<T>(pos.getData(), null, pos.getNext()));
        afterHere.getNext().setPrevious(afterHere);
        afterHere = afterHere.getNext();
        for (pos = list.getHead().getNext(); pos != null; pos = pos.getNext()) {
          afterHere.setNext(new NodeDL<T>(pos.getData(), null, null));
          afterHere.getNext().setPrevious(afterHere);
          afterHere = afterHere.getNext();
        }
        if (posAfterInsert != null) {
          //Set the next reference for the last node of the list to the posAfterInsert
          afterHere.setNext(posAfterInsert);
          posAfterInsert.setPrevious(afterHere);
        } else {
          //If afterHere is tail, set the tail to the last node in the list
          tail = afterHere;
        }
      }
    }
  }

  /**
   *  Extracts a subsequence of nodes and returns them as a new list
   *  @param fromHere  marks the node where the extraction begins
   *  @param toHere  marks the node where the extraction ends
   *  @return  the new list
   */
   public DLL<T> subseqByTransfer(NodeDL<T> fromHere, NodeDL<T> toHere) {
    //Create an empty list
    DLL<T> subSeq = new DLL<T>();
    if (fromHere != null && fromHere.getPrevious()!=null) {
      //Make subSeq list's head point to fromHere
      subSeq.head = fromHere;
      //Make subSeq list's tail point to the node toHere
      subSeq.tail = toHere;
      //Skip the nodes from fromHere to toHere
      fromHere.getPrevious().setNext(toHere.getNext());

      //If toHere is not tail
      if (toHere.getNext() != null) {
        toHere.getNext().setPrevious(fromHere.getPrevious());
      }
      //If toHere is tail, set tail of this to the node before fromHere
      else {
        this.tail = fromHere.getPrevious();
      }
      //Set next reference for toHere to null
      fromHere.setPrevious(null);
      toHere.setNext(null);
    }
    // if fromHere is null, extract a sequence from the head of the list onwards.
    else {
      //Make subSeq list's head point to the head of this
      subSeq.head = this.head;
      //Make subSeq list's tail point to the node toHere
      subSeq.tail = toHere;
      //If only one node in this
      if (toHere.getNext() == null) {
        this.head = null;
        this.tail = null;
      }
      //Set the head of this to the next node after toHere
      else {
        this.head = toHere.getNext();
        this.head.setPrevious(null);
        //Set next reference for toHere to null
        subSeq.tail.setNext(null);
      }
    }
    return subSeq;
  }

  /**
   *  Takes the provided list and inserts its elements into this
   *  after the specified node.  The inserted list ends up empty.
   *  @param list  the list to splice in.  Becomes empty after the call
   *  @param afterHere  Marks the place where the new elements are inserted
   */
   public void spliceByTransfer(DLL<T> list, NodeDL<T> afterHere) {
    //Throw exception if list is this
    if (list.equals(this)) {
      throw new SelfInsertException();
    }
    //Save the node after afterHere
    NodeDL<T> posNext;
    //If afterHere is null, add in front of head
    if (afterHere == null) {
      posNext = head;
      head = list.getHead();
    } else {
      posNext = afterHere.getNext();
      //Set the node next to afterHere to head of the inserted list
      afterHere.setNext(list.getHead());
      afterHere.getNext().setPrevious(afterHere);
    }
    //Set tail of the inserted list point to the node after afterHere
    list.getTail().setNext(posNext);

    if (posNext != null) {
      posNext.setPrevious(list.getTail());
    }
    else {
      tail = list.getTail();
    }
    //Set the input list to empty
    list.head = list.tail = null;
  }
}

