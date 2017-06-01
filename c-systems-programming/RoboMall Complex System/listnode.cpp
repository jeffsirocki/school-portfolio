// written by Joseph Kaiser and Jeff Sirocki
#include <iostream>
#include <string>
#include "tree.h"
using namespace std;

// ListNode Constructor
ListNode::ListNode( void ) {
  item = Item();
  nextPtr = NULL;
} 

void ListNode::addItem( ListNode* *list, Item i){
  int value;
  int duplicate = 1; // boolean for duplicate stores

  // value is the quantity of the item in the delivery
  value = i.count;                          
  ListNode* currentPtr;                         
  ListNode* tmpPtr;
  ListNode* newPtr;               
    
  // allocates memory
  newPtr = new ListNode();              
  if( newPtr != NULL ) {
    newPtr->item = i;
    newPtr->nextPtr = NULL;
    tmpPtr = NULL;
    currentPtr = *list;

    while( currentPtr != NULL ) {

      // Check for duplicate Items
      if ( i.zpos == currentPtr->item.zpos ) {
	if ( i.xpos == currentPtr->item.xpos ) {
	  if ( i.ypos == currentPtr->item.ypos ) {
	    if (i.ID[0] == currentPtr->item.ID[0] && i.ID[1] == currentPtr->item.ID[1] ) {
	      cout << " Item Count: " << currentPtr->item.count << endl;
	      currentPtr->item.count = currentPtr->item.count + i.count;
	      cout << " Item Count: " << currentPtr->item.count << endl;
	      
	      duplicate = 0;
	    }
	  }
	}
      }
      
      tmpPtr = currentPtr;
      currentPtr = currentPtr->nextPtr;

    } // End of Loop

    // RESET
    tmpPtr = NULL;
    currentPtr = *list;
    
    if ( duplicate ) {
      while( currentPtr != NULL && value < currentPtr->item.count ) {
	tmpPtr = currentPtr;                                  
	currentPtr = currentPtr->nextPtr;         
      }
      
      if( tmpPtr == NULL ) {
	newPtr->nextPtr = *list;
	
	*list = newPtr;
      } else {
	tmpPtr->nextPtr = newPtr;             
	newPtr->nextPtr = currentPtr;
      }
    }
  }
}

// Prints inventory
void ListNode::printList( )
{
  ListNode* currentPtr = this;
  
  while( currentPtr != NULL && currentPtr->item.count != 0 ) {
    cout << "      " << currentPtr->item.count << " -- (" << currentPtr->item.xpos << ", " << currentPtr->item.ypos << ", " << currentPtr->item.zpos << ")" << endl;
    currentPtr = currentPtr->nextPtr;
  }
}

// Prints inventory
void ListNode::printList( ListNode* currentPtr )
{
  while( currentPtr != NULL && currentPtr->item.count != 0 ) {
    cout << "      " << currentPtr->item.ID << " ("  
	 << currentPtr->item.count << ") -- (" 
	 << currentPtr->item.xpos << ", " 
	 << currentPtr->item.ypos << ", " 
	 << currentPtr->item.zpos << ")" << endl;
    currentPtr = currentPtr->nextPtr;
  }
}

