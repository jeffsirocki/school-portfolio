// written by Joseph Kaiser
#include <iostream>
#include <string>
#include "prog4.h"
using namespace std;

// ListNode Constructor
ListNode::ListNode( void ) {
  item = Item();
  nextPtr = NULL;
} 

void ListNode::addItem( ListNode* *list, Item i)              // adds an inventory entry into the linked list for an item delivery 
{
  int value;

  value = i.count;                         // value is the quantity of the item in the delivery 
  ListNode* currentPtr;                         
  ListNode* tmpPtr;
  ListNode* newPtr;               
    
  newPtr = new ListNode();              // attempts to construct a new ListNode
  if( newPtr != NULL )                  // checks if memory was properly allocated
    {
      newPtr->item = i;                 // assigns member values for the new ListNode based on item argument passed to function
      newPtr->nextPtr = NULL;
      tmpPtr = NULL;
      currentPtr = *list;

      while( currentPtr != NULL && value < currentPtr->item.count )   // continues to walk while the current pointer is not null and the value is less than it
	{
	  tmpPtr = currentPtr;                                           // keeps track of the last pointer visited 
	  currentPtr = currentPtr->nextPtr;                              // walks forward to the next pointer
	}
      if( tmpPtr == NULL )
	{
	  newPtr->nextPtr = *list;               // if the list was empty, or if the value was greater than the count in the front of the list, inserts the new ListNode at the front
	 *list = newPtr;
	}
      else
	{
	  tmpPtr->nextPtr = newPtr;             // inserts the new ListNode between the last pointer visited and the current pointer
	  newPtr->nextPtr = currentPtr;
	}
    }
}

void ListNode::printList( )      // prints the information of each stores inventory of the item in ListNode one line at a time
{
  ListNode* currentPtr = this;
  
  while( currentPtr != NULL && currentPtr->item.count != 0 )                   // walks through the list until it reaches the end
    {
      cout << "      " << currentPtr->item.count << " -- (" << currentPtr->item.xpos << ", " << currentPtr->item.ypos << ", " << currentPtr->item.zpos << ")" << endl;
      currentPtr = currentPtr->nextPtr;
    }
}
