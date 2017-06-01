// Created by Jeff Sirocki and Joseph Kaiser
// CS2303 - A02

// Header File

#include <iostream>
#include <string>
using namespace std;

// Prevent multiple includsions of the header??
#ifndef ITEM_H
#define ITEM_H

// Class for Item Data put into linked list
class Item {

 public:
  Item( string, int, int, int, int ); // Default constructor
  Item( void );

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
  ListNode( void ); // Default constructor

  void addItem( ListNode* *list, Item i);
  void printList( ListNode* currentPtr );
  void printList( );

  Item item;
  ListNode *nextPtr;
}; // end of ListNode class

#endif

// Prevent multiple includsions of the header??
#ifndef TREENODE_H
#define TREENODE_H

// Class for Tree
class TreeNode {

 public:
  TreeNode( void ); // Default Constructor

  // Functions
  void insert( Item I );
  void print_tree();

  // Fields
  string item;
  ListNode *list;
  TreeNode *leftPtr;
  TreeNode *rightPtr;
}; // end of class TreeNode

#endif
