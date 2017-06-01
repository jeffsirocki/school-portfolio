// Joseph Kaiser
// CS2303 - A02

// Header File

#include <iostream>
#include <string>
#include "prog4.h"
using namespace std;

// Prevent redefinitions of the header
#ifndef ITEM_H
#define ITEM_H

// Class for Item Data put into linked list
class Item {

 public:
  Item( string = "A0", int = 0, int = 0, int = 0, int = 0 ); // Default constructor

  string ID;
  int count;
  int xpos;
  int ypos;
  int zpos;
}; // end of Item class

#endif

// Prevent redefinition of the header
#ifndef LISTNODE_H
#define LISTNODE_H

// Class for Linked List of Item information
class ListNode {
  
 public:
  ListNode( Item = NULL, ListNode = NULL ); // Default constructor

  void addItem(Item i);
  void printList(ListNode listPtr);

  Item item;
  ListNode = nextPtr;
}; // end of ListNode class

#endif

