# Linked List Implementation 

> My own implementation of singly linked lists and doubly linked lists.

<a name="toc"/></a>
## Table of Contents

1. [Overview](#overview)

2. [Technologies](#technologies)

3. [Launch](#launch)

<a name="overview"/></a>
## 1. Overview
[Back to ToC](#toc)

In this project, I developed my own implementation of singly linked lists and doubly linked lists. The following methods are implemented for both singly linked lists:

* A constructor that creates an empty list with null head and tail references.
* A method to check if the list is empty.
* Accessors for getting the head and tail of the list.
* Accessors and manipulators in the Node class for getting and setting the data, as well as getting and setting the next node.
* The method addFirst to add a new element at the front of the list.
* The method toString to print the list.
* The method removeFirst to remove the element from the head of the list.
* The method addLast to insert an element at the tail of the list.
* The method removeLast to remove the element from the tail of the list.
* The method addAfter to insert an element after a specified node in the list.
* The method removeAfter to remove the node after a given position.
* The method size to return the number of elements in the list.
* The method subseqByCopy(here,n) returns a copy of a subsequence of the list, starting at 'here' and containing 'n' nodes. The original list remains unchanged.
* The method spliceByCopy(list,afterHere) copies the nodes of 'list' and adds them to the current list following the node 'afterHere'. If 'afterHere' is null, it adds them to the head of the list. The contents of the input 'list' remain unchanged.
* The method subseqByTransfer(afterHere,toHere) extracts a subsequence out of the original list and returns it as a new list, shortening the original list. The extracted sequence begins with the element following 'afterHere' and goes up to and including 'toHere'. The original list should skip from 'afterHere' to the element that originally followed 'toHere'. If 'afterHere' is null, it should extract a sequence from the head of the list onwards.
* The method spliceByTransfer(list,afterHere) moves all elements of 'list' into the current list just after the node 'afterHere'. The input argument 'list' should be left empty after the transfer.

<a name="technologies"/></a>
## 2. Technologies
[Back to ToC](#toc)

java version "1.8.0_181"<br />
Java(TM) SE Runtime Environment (build 1.8.0_181-b13)<br />
Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)<br />

<a name="launch"/></a>
## 3. Launch
[Back to ToC](#toc)
```bash
javac -classpath .:target/dependency/* -d . $(find . -type f -name '*.java')

To Test singly linked lists
java TestSLL1
java TestSLL2
java TestSLL3
java TestSLL4

To Test doubly linked lists
java TestDLL1
java TestDLL2
java TestDLL3
java TestDLL1
```